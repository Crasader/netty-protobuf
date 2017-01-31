package me.trinopoty.nettyprotobuf.server;

import com.google.protobuf.AbstractMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.trinopoty.nettyprotobuf.ProtobufMessageRegistry;

import java.util.concurrent.ExecutorService;

@ChannelHandler.Sharable
final class ProtobufChannelHandler extends ChannelInboundHandlerAdapter {

    private static final class MessageHandlerWorker implements Runnable {

        private ProtobufMessageHandler mMessageHandler;
        private ChannelHandlerContext mContext;
        private AbstractMessage mMessage;

        MessageHandlerWorker(ProtobufMessageHandler pMessageHandler, ChannelHandlerContext pContext, AbstractMessage pMessage) {
            mMessageHandler = pMessageHandler;
            mContext = pContext;
            mMessage = pMessage;
        }

        @Override
        public void run() {
            try {
                mMessageHandler.handleMessage(mContext, mMessage);
            } catch(Exception ex) {
                throw new RuntimeException("Handler threw an exception.", ex);
            }
        }
    }

    private ProtobufMessageRegistry mMessageRegistry;
    private ExecutorService mExecutorService;

    ProtobufChannelHandler(ProtobufMessageRegistry pMessageRegistry, ExecutorService pExecutorService) {
        mMessageRegistry = pMessageRegistry;
        mExecutorService = pExecutorService;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbstractMessage message = (AbstractMessage) msg;
        mExecutorService.execute(new MessageHandlerWorker(mMessageRegistry.getMessageHandlerFromClass(message.getClass()), ctx, message));
    }
}