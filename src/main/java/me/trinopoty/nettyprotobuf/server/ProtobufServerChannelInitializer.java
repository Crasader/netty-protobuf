package me.trinopoty.nettyprotobuf.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import me.trinopoty.nettyprotobuf.ProtobufMessageRegistry;
import me.trinopoty.nettyprotobuf.codec.NettyProtobufDecoder;
import me.trinopoty.nettyprotobuf.codec.NettyProtobufEncoder;

import java.util.concurrent.ExecutorService;

final class ProtobufServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static NettyProtobufEncoder PROTOBUF_ENCODER;
    private static ProtobufChannelHandler PROTOBUF_HANDLER;

    private ProtobufMessageRegistry mMessageRegistry;

    ProtobufServerChannelInitializer(ProtobufMessageRegistry pMessageRegistry, ExecutorService pExecutorService) {
        if(pMessageRegistry == null) {
            throw new IllegalArgumentException("messageRegistry must not be null.");
        }

        mMessageRegistry = pMessageRegistry;

        if(PROTOBUF_ENCODER == null) {
            PROTOBUF_ENCODER = new NettyProtobufEncoder(pMessageRegistry);
            PROTOBUF_HANDLER = new ProtobufChannelHandler(pMessageRegistry, pExecutorService);
        }
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(PROTOBUF_ENCODER);
        pipeline.addLast(new NettyProtobufDecoder(mMessageRegistry));
        pipeline.addLast(PROTOBUF_HANDLER);
    }
}