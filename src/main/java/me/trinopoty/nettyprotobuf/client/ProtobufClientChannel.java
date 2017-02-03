package me.trinopoty.nettyprotobuf.client;

import com.google.protobuf.AbstractMessage;
import io.netty.channel.ChannelFuture;

public final class ProtobufClientChannel extends ProtobufClientChannelAbstract {

    private ChannelFuture mChannelFuture;

    ProtobufClientChannel(ChannelFuture pChannelFuture) {
        mChannelFuture = pChannelFuture;
    }

    public synchronized AbstractMessage sendMessageSync(AbstractMessage pMessage) throws Exception {
        sync();
        mChannelFuture.channel().writeAndFlush(pMessage).sync();
        if(mChannelFuture.isSuccess()) {
            ProtobufChannelHandler clientHandler = (ProtobufChannelHandler) mChannelFuture.channel().pipeline().last();
            return clientHandler.getMessage();
        } else {
            return null;
        }
    }

    public synchronized void sync() throws InterruptedException {
        mChannelFuture.sync();
    }

    public synchronized void close() {
        try {
            mChannelFuture.channel().close().sync();
        } catch(InterruptedException ignore) {
        }
    }

    public synchronized boolean getIsActive() {
        return ((ProtobufChannelHandler) mChannelFuture.channel().pipeline().last()).getIsActive();
    }
}