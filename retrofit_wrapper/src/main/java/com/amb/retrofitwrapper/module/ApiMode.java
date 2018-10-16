package com.amb.retrofitwrapper.module;

/**
 * Api mode for the app
 */
public enum ApiMode {
    SD_CARD("sdcard"),
    ASSET("asset"),
    REAL("disable"), // Will using real api
    DEFAULT(REAL.getId());

    private final String mId;

    ApiMode(String id) {
        this.mId = id;
    }

    public String getId() {
        return mId;
    }

    public static ApiMode getTypeFromId(String id) {
        Enum[] arrays = ApiMode.class.getEnumConstants();
        int length = arrays.length;

        for (int index = 0; index < length; ++index) {
            ApiMode option = (ApiMode) arrays[index];
            if (option.getId().equals(id)) {
                return option;
            }
        }
        return DEFAULT;
    }
}