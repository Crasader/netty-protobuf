package me.trinopoty.nettyprotobuf.codec;

import com.google.protobuf.AbstractMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;
import me.trinopoty.nettyprotobuf.ProtobufMessageRegistry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@ChannelHandler.Sharable
public class NettyProtobufDecoder extends ByteToMessageDecoder {

    private static HashMap<Class<? extends AbstractMessage>, Method> PROTOBUF_PARSER = new HashMap<>();

    private ProtobufMessageRegistry mMessageRegistry;

    public NettyProtobufDecoder(ProtobufMessageRegistry messageRegistry) {
        mMessageRegistry = messageRegistry;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        AbstractMessage msg = decode(ctx, byteBuf);
        if(msg != null) {
            list.add(msg);
        }
    }

    @Override
    public boolean isSharable() {
        return true;
    }

    private AbstractMessage decode(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        int readerIdx = byteBuf.readerIndex();
        do {
            if(byteBuf.readableBytes() < NettyProtobufConstants.MIN_LENGTH) {
                break;
            }

            final int packetSignature = byteBuf.readInt();
            if(packetSignature != NettyProtobufConstants.SIGNATURE) {
                readerIdx += 1;
                break;
            }

            final int payloadLength = byteBuf.readInt();
            final int payloadTypeIdentifier = byteBuf.readInt();

            if(payloadLength > NettyProtobufConstants.MAX_LENGTH) {
                throw new TooLongFrameException("frame size (" + payloadLength + ") larger than maximum size (" + NettyProtobufConstants.MAX_LENGTH + ")");
            }

            if(byteBuf.readableBytes() >= payloadLength) {
                final byte[] payloadBuffer = new byte[payloadLength];
                byteBuf.readBytes(payloadBuffer);

                Class<? extends AbstractMessage> messageClass = mMessageRegistry.getMessageClassFromIdentifier(payloadTypeIdentifier);
                return parseBytesToProtobuf(payloadBuffer, messageClass);
            }
        } while (false);
        byteBuf.readerIndex(readerIdx);
        return null;
    }

    private AbstractMessage parseBytesToProtobuf(byte[] protobufData, Class<? extends AbstractMessage> messageClass) {
        AbstractMessage msg = null;
        try {
            msg = (AbstractMessage) getProtobufParserMethod(messageClass).invoke(null, protobufData);
        } catch (InvocationTargetException | IllegalAccessException ignore) {
        }
        return msg;
    }

    private Method getProtobufParserMethod(Class<? extends AbstractMessage> messageClass) {
        if(PROTOBUF_PARSER.get(messageClass) == null) {
            try {
                Method parserMethod = messageClass.getMethod("parseFrom", byte[].class);
                PROTOBUF_PARSER.put(messageClass, parserMethod);
            } catch (NoSuchMethodException ignore) {
            }
        }

        return PROTOBUF_PARSER.get(messageClass);
    }
}