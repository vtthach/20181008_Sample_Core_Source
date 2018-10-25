package com.sf0404.core.application.business.usecase;

import java.util.HashMap;

public class BaseParam {
    private HashMap<String, String> header;

    public HashMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(HashMap<String, String> header) {
        this.header = header;
    }
}
