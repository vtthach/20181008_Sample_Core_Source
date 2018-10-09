package com.sf0404.common.enums;

public final class EnumHelper {
    private EnumHelper() {
        throw new IllegalAccessError("EnumHelper class");
    }

    public static <T1, T extends Enum & IEnum<T1>> T getEnumTypeFromId(Class<T> cls, T1 id, T defaultValue) {
        Enum[] arrays = cls.getEnumConstants();
        int length = arrays.length;

        for(int index = 0; index < length; ++index) {
            T option = (T) arrays[index];
            if (id instanceof String && ((String)((IEnum)option).getId()).equalsIgnoreCase((String)id)) {
                return option;
            }

            if (((IEnum)option).getId().equals(id)) {
                return option;
            }
        }

        return defaultValue;
    }
}