package me.trinopoty.nettyprotobuf.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import me.trinopoty.nettyprotobuf.codec.NettyProtobufDecoder;
import me.trinopoty.nettyprotobuf.codec.NettyProtobufEncoder;

final class ProtobufClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static NettyProtobufEncoder PROTOBUF_ENCODER;

    private ProtobufClientMessageRegistry mMessageRegistry;

    public ProtobufClientChannelInitializer(ProtobufClientMessageRegistry messageRegistry) {
        if(messageRegistry == null) {
            throw new IllegalArgumentException("messageRegistry must not be null.");
        }

        mMessageRegistry = messageRegistry;

        if(PROTOBUF_ENCODER == null) {
            PROTOBUF_ENCODER = new NettyProtobufEncoder(messageRegistry);
        }
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(PROTOBUF_ENCODER);
        pipeline.addLast(new NettyProtobufDecoder(mMessageRegistry));
        pipeline.addLast(new ProtobufChannelHandler());
    }
}