package org.pegcode.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64工具类
 */
public class Base64Util {

    /**
     * base64加密-字符串转base64字符串
     * @param str
     * @return
     */
    public String string2Base64String(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64解密-base64字符串转字符串
     * @param base64Str
     * @return
     */
    public String base64String2String(String base64Str) {
        byte[] decodeBytes = Base64.getDecoder().decode(base64Str);
        return new String(decodeBytes);
    }

    /**
     * base64加密-字节数组转base64字节数组
     * @param bytes
     * @return
     */
    public byte[] bytes2Base64Bytes(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    /**
     * base64解密-base64字节数组转字节数组
     * @param base64Bytes
     * @return
     */
    public byte[] base64Bytes2Bytes(byte[] base64Bytes) {
        return Base64.getDecoder().decode(base64Bytes);
    }
}
