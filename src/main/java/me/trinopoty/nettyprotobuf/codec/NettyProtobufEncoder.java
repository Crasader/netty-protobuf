package me.trinopoty.nettyprotobuf.codec;

import com.google.protobuf.AbstractMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.trinopoty.nettyprotobuf.ProtobufMessageRegistry;

public class NettyProtobufEncoder extends MessageToByteEncoder<AbstractMessage> {

    private ProtobufMessageRegistry mMessageRegistry;

    public NettyProtobufEncoder(ProtobufMessageRegistry messageRegistry) {
        mMessageRegistry = messageRegistry;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractMessage abstractMessage, ByteBuf byteBuf) throws Exception {
        byte[] buffer = abstractMessage.toByteArray();

        byteBuf.writeInt(NettyProtobufConstants.SIGNATURE);
        byteBuf.writeInt(buffer.length);
        byteBuf.writeInt(mMessageRegistry.getMessageIdentifierFromClass(abstractMessage.getClass()));
        byteBuf.writeBytes(buffer);
    }
}