package com.example.demo.domain;

/**
 * Created by linweili on 2018/8/24/0024.
 */
public class RedisCode {
    private String type;
    private String hash;
    private String key;
    private String zset;
    private String score;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getZset() {
        return zset;
    }

    public void setZset(String zset) {
        this.zset = zset;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
