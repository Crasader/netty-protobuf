package me.trinopoty.nettyprotobuf.client;

import com.google.protobuf.AbstractMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

final class ProtobufChannelHandler extends ChannelInboundHandlerAdapter {

    private BlockingQueue<AbstractMessage> mMessageQueue = new LinkedBlockingQueue<>();

    ProtobufChannelHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof AbstractMessage) {
            mMessageQueue.add((AbstractMessage) msg);
        }
    }

    AbstractMessage getMessage() {
        try {
            return mMessageQueue.poll(1, TimeUnit.SECONDS);
        } catch(InterruptedException ex) {
            return null;
        }
    }
}