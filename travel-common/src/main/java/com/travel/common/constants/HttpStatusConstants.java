package com.travel.common.constants;

public enum  HttpStatusConstants {

    BAD_GATEWAY(502,"无效响应");
    private int key;
    private String value;

    private HttpStatusConstants(int key,String value){
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
