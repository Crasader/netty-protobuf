package me.trinopoty.nettyprotobuf.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public final class ProtobufClient {

    public static final class ProtobufClientBuilder {

        private ProtobufClientMessageRegistry mProtobufMessageRegistry = null;

        public ProtobufClientBuilder() {

        }

        public ProtobufClient.ProtobufClientBuilder setProtobufMessageRegistry(ProtobufClientMessageRegistry pProtobufMessageRegistry) {
            mProtobufMessageRegistry = pProtobufMessageRegistry;
            return this;
        }

        public ProtobufClient build() {
            ProtobufClient protobufClient = new ProtobufClient();

            protobufClient.mProtobufMessageRegistry = mProtobufMessageRegistry;

            protobufClient.mClientGroup = new NioEventLoopGroup();
            protobufClient.mBootstrap = new Bootstrap();
            protobufClient.mBootstrap.group(protobufClient.mClientGroup);
            protobufClient.mBootstrap.channel(NioSocketChannel.class);
            protobufClient.mBootstrap.handler(new ProtobufClientChannelInitializer(mProtobufMessageRegistry));

            return protobufClient;
        }
    }

    private ProtobufClientMessageRegistry mProtobufMessageRegistry;

    private EventLoopGroup mClientGroup;
    private Bootstrap mBootstrap;

    private ProtobufClient() {
    }

    public ProtobufClientChannel getClientChannel(String pHost, int pPort) {
        return getClientChannel(new InetSocketAddress(pHost, pPort));
    }

    public ProtobufClientChannel getClientChannel(InetSocketAddress pRemoteAddress) {
        return new ProtobufClientChannelImpl(mBootstrap.connect(pRemoteAddress));
    }

    public void close() {
        mClientGroup.shutdownGracefully();
    }
}