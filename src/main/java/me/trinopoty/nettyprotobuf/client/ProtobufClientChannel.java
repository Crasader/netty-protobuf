package me.trinopoty.nettyprotobuf.client;

import com.google.protobuf.AbstractMessage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.GenericFutureListener;

public final class ProtobufClientChannel {

    public interface MessageResponseHandler {
        void responseReceived(AbstractMessage pMessage);
    }

    private ChannelFuture mChannelFuture;
    private MessageResponseHandler mAsyncMessageResponseHandler;

    /*private GenericFutureListener<ChannelFuture> mChannelFutureListener = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture pChannelFuture) throws Exception {
            if(mAsyncMessageResponseHandler != null) {
                mAsyncMessageResponseHandler.responseReceived(pChannelFuture.isSuccess()? ((ProtobufChannelHandler) mChannelFuture.channel().pipeline().last()).getMessage() : null);
                mAsyncMessageResponseHandler = null;
            }
        }
    };*/

    ProtobufClientChannel(ChannelFuture pChannelFuture) {
        mChannelFuture = pChannelFuture;
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

    public synchronized AbstractMessage sendMessageSync(AbstractMessage pMessage) throws Exception {
        mChannelFuture.channel().writeAndFlush(pMessage).sync();
        if(mChannelFuture.isSuccess()) {
            ProtobufChannelHandler clientHandler = (ProtobufChannelHandler) mChannelFuture.channel().pipeline().last();
            return clientHandler.getMessage();
        } else {
            return null;
        }
    }

    /*public synchronized void sendMessageAsync(AbstractMessage pMessage, MessageResponseHandler pHandler) throws Exception {
        if(mAsyncMessageResponseHandler != null) {
            throw new IllegalAccessException("An async call is already pending.");
        }
        mAsyncMessageResponseHandler = pHandler;
        mChannelFuture.channel().writeAndFlush(pMessage).addListener(mChannelFutureListener);
    }*/
}