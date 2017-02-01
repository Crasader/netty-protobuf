package me.trinopoty.nettyprotobuf.server;

import io.netty.channel.ChannelHandlerContext;

public interface ProtobufServerExceptionHandler {

    void handleException(ChannelHandlerContext ctx, Throwable cause);
}