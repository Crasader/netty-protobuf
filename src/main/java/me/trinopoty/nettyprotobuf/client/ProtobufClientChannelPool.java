package me.trinopoty.nettyprotobuf.client;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.Closeable;
import java.net.InetSocketAddress;

public final class ProtobufClientChannelPool implements Closeable {

    private final class ProtobufClientChannelFactory implements PooledObjectFactory<ProtobufClientChannel> {

        private ProtobufClient mClient;
        private InetSocketAddress mRemoteAddress;

        ProtobufClientChannelFactory(ProtobufClient client, InetSocketAddress remoteAddress) {
            mClient = client;
            mRemoteAddress = remoteAddress;
        }

        @Override
        public PooledObject<ProtobufClientChannel> makeObject() throws Exception {
            ProtobufClientChannel clientChannel = new ProtobufClientChannelProxyImpl(mClient.getClientChannel(mRemoteAddress), ProtobufClientChannelPool.this);
            return new DefaultPooledObject<ProtobufClientChannel>(clientChannel);
        }

        @Override
        public void destroyObject(PooledObject<ProtobufClientChannel> pooledObject) throws Exception {
            if(validateObject(pooledObject)) {
                ((ProtobufClientChannelProxyImpl) pooledObject.getObject()).realClose();
            }
        }

        @Override
        public boolean validateObject(PooledObject<ProtobufClientChannel> pooledObject) {
            return pooledObject.getObject().getIsActive();
        }

        @Override
        public void activateObject(PooledObject<ProtobufClientChannel> pooledObject) throws Exception {
        }

        @Override
        public void passivateObject(PooledObject<ProtobufClientChannel> pooledObject) throws Exception {
        }
    }

    private GenericObjectPool<ProtobufClientChannel> mClientChannelPool;

    ProtobufClientChannelPool(ProtobufClient client, ProtobufClientChannelPoolConfig poolConfig, InetSocketAddress remoteAddress) {
        mClientChannelPool = new GenericObjectPool<ProtobufClientChannel>(new ProtobufClientChannelFactory(client, remoteAddress), poolConfig);
    }

    public ProtobufClientChannel getResource() {
        try {
            return mClientChannelPool.borrowObject();
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void returnBrokenResource(ProtobufClientChannel clientChannel) {
        try {
            mClientChannelPool.invalidateObject(clientChannel);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void returnResource(ProtobufClientChannel clientChannel) {
        mClientChannelPool.returnObject(clientChannel);
    }

    @Override
    public void close() {
        mClientChannelPool.close();
    }
}