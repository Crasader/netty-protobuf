package me.trinopoty.nettyprotobuf.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import me.trinopoty.nettyprotobuf.ProtobufMessageRegistry;
import me.trinopoty.nettyprotobuf.codec.NettyProtobufDecoder;
import me.trinopoty.nettyprotobuf.codec.NettyProtobufEncoder;

final class ProtobufClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static NettyProtobufEncoder PROTOBUF_ENCODER;
    private static NettyProtobufDecoder PROTOBUF_DECODER;

    public ProtobufClientChannelInitializer(ProtobufMessageRegistry messageRegistry) {
        if(messageRegistry == null) {
            throw new IllegalArgumentException("messageRegistry must not be null.");
        }

        if(PROTOBUF_ENCODER == null) {
            PROTOBUF_ENCODER = new NettyProtobufEncoder(messageRegistry);
            PROTOBUF_DECODER = new NettyProtobufDecoder(messageRegistry);
        }
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(PROTOBUF_ENCODER);
        pipeline.addLast(PROTOBUF_DECODER);
        pipeline.addLast(new ProtobufChannelHandler());
    }
}