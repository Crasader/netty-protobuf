// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EchoMessage.proto

package me.trinopoty.nettyprotobuf.test.message;

public final class EchoMessageOuterClass {
  private EchoMessageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface EchoMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:me.trinopoty.nettyprotobuf.test.message.EchoMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string msg = 1;</code>
     */
    java.lang.String getMsg();
    /**
     * <code>optional string msg = 1;</code>
     */
    com.google.protobuf.ByteString
        getMsgBytes();
  }
  /**
   * Protobuf type {@code me.trinopoty.nettyprotobuf.test.message.EchoMessage}
   */
  public  static final class EchoMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:me.trinopoty.nettyprotobuf.test.message.EchoMessage)
      EchoMessageOrBuilder {
    // Use EchoMessage.newBuilder() to construct.
    private EchoMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private EchoMessage() {
      msg_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private EchoMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              msg_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage.class, me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage.Builder.class);
    }

    public static final int MSG_FIELD_NUMBER = 1;
    private volatile java.lang.Object msg_;
    /**
     * <code>optional string msg = 1;</code>
     */
    public java.lang.String getMsg() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msg_ = s;
        return s;
      }
    }
    /**
     * <code>optional string msg = 1;</code>
     */
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getMsgBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, msg_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getMsgBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, msg_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage)) {
        return super.equals(obj);
      }
      me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage other = (me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage) obj;

      boolean result = true;
      result = result && getMsg()
          .equals(other.getMsg());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + MSG_FIELD_NUMBER;
      hash = (53 * hash) + getMsg().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code me.trinopoty.nettyprotobuf.test.message.EchoMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:me.trinopoty.nettyprotobuf.test.message.EchoMessage)
        me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage.class, me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage.Builder.class);
      }

      // Construct using me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        msg_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_descriptor;
      }

      public me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage getDefaultInstanceForType() {
        return me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage.getDefaultInstance();
      }

      public me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage build() {
        me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage buildPartial() {
        me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage result = new me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage(this);
        result.msg_ = msg_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage) {
          return mergeFrom((me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage other) {
        if (other == me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage.getDefaultInstance()) return this;
        if (!other.getMsg().isEmpty()) {
          msg_ = other.msg_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object msg_ = "";
      /**
       * <code>optional string msg = 1;</code>
       */
      public java.lang.String getMsg() {
        java.lang.Object ref = msg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          msg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string msg = 1;</code>
       */
      public com.google.protobuf.ByteString
          getMsgBytes() {
        java.lang.Object ref = msg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          msg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string msg = 1;</code>
       */
      public Builder setMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        msg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string msg = 1;</code>
       */
      public Builder clearMsg() {
        
        msg_ = getDefaultInstance().getMsg();
        onChanged();
        return this;
      }
      /**
       * <code>optional string msg = 1;</code>
       */
      public Builder setMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        msg_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:me.trinopoty.nettyprotobuf.test.message.EchoMessage)
    }

    // @@protoc_insertion_point(class_scope:me.trinopoty.nettyprotobuf.test.message.EchoMessage)
    private static final me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage();
    }

    public static me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<EchoMessage>
        PARSER = new com.google.protobuf.AbstractParser<EchoMessage>() {
      public EchoMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new EchoMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<EchoMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<EchoMessage> getParserForType() {
      return PARSER;
    }

    public me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass.EchoMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021EchoMessage.proto\022\'me.trinopoty.nettyp" +
      "rotobuf.test.message\"\032\n\013EchoMessage\022\013\n\003m" +
      "sg\030\001 \001(\tb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_me_trinopoty_nettyprotobuf_test_message_EchoMessage_descriptor,
        new java.lang.String[] { "Msg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
