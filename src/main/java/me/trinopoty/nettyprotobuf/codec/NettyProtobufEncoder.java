package me.trinopoty.nettyprotobuf.codec;

import com.google.protobuf.AbstractMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.trinopoty.nettyprotobuf.client.ProtobufClientMessageRegistry;

@ChannelHandler.Sharable
public final class NettyProtobufEncoder extends MessageToByteEncoder<AbstractMessage> {

    private ProtobufClientMessageRegistry mMessageRegistry;

    public NettyProtobufEncoder(ProtobufClientMessageRegistry messageRegistry) {
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

    @Override
    public boolean isSharable() {
        return true;
    }
}