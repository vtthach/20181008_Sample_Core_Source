package com.rain.carddispenser;


public class DispenserConstants {
    static final byte[] RESET = new byte[]{0x02, 0x53, 0x54, 0x03, 0x06};
    static final byte[] EXECUTE = new byte[]{0x05};
    static final byte[] GET_STATUS = new byte[]{0x02, 0x52, 0x46, 0x03, 0x15};
    static final byte[] ISSUE = new byte[]{0x02, 0x44, 0x43, 0x03, 0x06};
    static final byte[] RECALL = new byte[]{0x02, 0x43, 0x50, 0x03, 0x12};

    public static final String ACK_RESPONSE = "06";

    private DispenserConstants() {
        // Private constructor
    }
}
