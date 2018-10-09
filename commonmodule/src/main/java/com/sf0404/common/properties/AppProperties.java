package com.sf0404.common.properties;

public interface AppProperties {
    void reloadProperties();

    String getProperty(String key, String defValue);
}
