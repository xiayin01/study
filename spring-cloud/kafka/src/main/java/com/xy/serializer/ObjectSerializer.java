package com.xy.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class ObjectSerializer implements Serializer {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object data) {
        System.out.println("topic : " + topic + " , ObjectSerializer :" + data);
        byte[] dataArray = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
            dataArray = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataArray;
    }

    @Override
    public void close() {

    }
}
