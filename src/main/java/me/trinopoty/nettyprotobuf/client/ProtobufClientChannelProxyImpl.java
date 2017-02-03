package me.trinopoty.nettyprotobuf.client;

import com.google.protobuf.AbstractMessage;

class ProtobufClientChannelProxyImpl extends ProtobufClientChannel {

    private ProtobufClientChannel mClientChannel;
    private ProtobufClientChannelPool mClientChannelPool;

    ProtobufClientChannelProxyImpl(ProtobufClientChannel clientChannel, ProtobufClientChannelPool clientChannelPool) {
        mClientChannel = clientChannel;
        mClientChannelPool = clientChannelPool;
    }

    @Override
    public AbstractMessage sendMessageSync(AbstractMessage message) throws Exception {
        return mClientChannel.sendMessageSync(message);
    }

    @Override
    public void sync() throws InterruptedException {
        mClientChannel.sync();
    }

    @Override
    public void close() throws Exception {
        // Return to the pool
        mClientChannelPool.returnResource(this);
    }

    @Override
    public boolean getIsActive() {
        return mClientChannel.getIsActive();
    }

    void realClose() throws Exception {
        mClientChannel.close();
    }
}