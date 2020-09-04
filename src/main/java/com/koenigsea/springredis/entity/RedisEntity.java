package com.koenigsea.springredis.entity;

/**
 * @author KoenigSEA
 */
public class RedisEntity {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RedisEntity{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
