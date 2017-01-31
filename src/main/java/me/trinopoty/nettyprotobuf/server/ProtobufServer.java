package me.trinopoty.nettyprotobuf.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.trinopoty.nettyprotobuf.ProtobufMessageRegistry;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.*;

public final class ProtobufServer {

    public static final class ProtobufServerBuilder {

        private InetSocketAddress mLocalAddress = null;
        private int mBacklogCount = 5;
        private boolean mKeepAlive = true;
        private ProtobufMessageRegistry mProtobufMessageRegistry = null;

        public ProtobufServerBuilder() {
        }

        public ProtobufServerBuilder setLocalPort(int pPort) {
            mLocalAddress = new InetSocketAddress(InetAddress.getLoopbackAddress(), pPort);
            return this;
        }

        public ProtobufServerBuilder setLocalAddress(InetSocketAddress pLocalAddress) {
            mLocalAddress = pLocalAddress;
            return this;
        }

        public ProtobufServerBuilder setBacklogCount(int pBacklogCount) {
            mBacklogCount = pBacklogCount;
            return this;
        }

        public ProtobufServerBuilder setKeepAlive(boolean pKeepAlive) {
            mKeepAlive = pKeepAlive;
            return this;
        }

        public ProtobufServerBuilder setProtobufMessageRegistry(ProtobufMessageRegistry pProtobufMessageRegistry) {
            mProtobufMessageRegistry = pProtobufMessageRegistry;
            return this;
        }

        public ProtobufServer build() {
            if(mLocalAddress == null) {
                throw new IllegalArgumentException("localAddress must be provided.");
            }
            if(mProtobufMessageRegistry == null) {
                throw new IllegalArgumentException("Instance of ProtobufMessageRegistry must be provided.");
            }

            ProtobufServer protobufServer = new ProtobufServer();

            protobufServer.mProtobufMessageRegistry = mProtobufMessageRegistry;
            protobufServer.mLocalAddress = mLocalAddress;
            protobufServer.mRequestExecutor = Executors.newFixedThreadPool(8);

            protobufServer.mServerGroup = new NioEventLoopGroup();
            protobufServer.mClientGroup = new NioEventLoopGroup();
            protobufServer.mServerBootstrap = new ServerBootstrap();
            protobufServer.mServerBootstrap.group(protobufServer.mServerGroup, protobufServer.mClientGroup);
            protobufServer.mServerBootstrap.channel(NioServerSocketChannel.class);
            protobufServer.mServerBootstrap.childHandler(new ProtobufServerChannelInitializer(mProtobufMessageRegistry, protobufServer.mRequestExecutor));
            protobufServer.mServerBootstrap.option(ChannelOption.SO_BACKLOG, mBacklogCount);
            protobufServer.mServerBootstrap.option(ChannelOption.SO_KEEPALIVE, mKeepAlive);

            return protobufServer;
        }
    }

    private ProtobufMessageRegistry mProtobufMessageRegistry;
    private InetSocketAddress mLocalAddress;
    private ExecutorService mRequestExecutor;

    private EventLoopGroup mServerGroup;
    private EventLoopGroup mClientGroup;
    private ServerBootstrap mServerBootstrap;
    private ChannelFuture mServerChannelFuture;

    private ProtobufServer() {
    }

    public void startServer() {
        try {
            mServerChannelFuture = mServerBootstrap.bind(mLocalAddress).sync();
        } catch(InterruptedException ex) {
            throw new RuntimeException("Unable to start protobuf server.", ex);
        }
    }

    public void stopServer() {
        try {
            mServerChannelFuture.channel().close().sync();
            mServerGroup.shutdownGracefully();
            mClientGroup.shutdownGracefully();
            mRequestExecutor.shutdown();
        } catch(InterruptedException ignore) {
        }
    }
}