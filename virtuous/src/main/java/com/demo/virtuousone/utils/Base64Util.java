package com.demo.virtuousone.utils;

import org.bouncycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: Base64工具类
 */
public class Base64Util {
    public static final String DEFAULT_ENCODING_STRING = "utf-8";

    public static byte[] encode(byte[] byteData) {
        return Base64.encode(byteData);
    }

    public static byte[] decode(byte[] byteData) {
        return Base64.decode(byteData);
    }

    public static String encode(String data) throws UnsupportedEncodingException {
        return new String(encode(data.getBytes()));
    }

    public static String decode(String data) throws UnsupportedEncodingException {
        return new String(decode(data.getBytes()));
    }

    public static byte[] encode2Bytes(String data) throws UnsupportedEncodingException {
        return encode(data.getBytes());
    }

    public static byte[] decode2Bytes(String data) throws UnsupportedEncodingException {
        return decode(data.getBytes());
    }

    public static String encode2Str(byte[] byteData) throws UnsupportedEncodingException {
        return new String(encode(byteData));
    }

    public static String decode2Str(byte[] byteData) throws UnsupportedEncodingException {
        return new String(decode(byteData));
    }


    public static String encode(String data, String encoding) throws UnsupportedEncodingException {
        return new String(encode(data.getBytes(encoding)), getEncoding(encoding));
    }

    public static String decode(String data, String encoding) throws UnsupportedEncodingException {
        return new String(decode(data.getBytes(encoding)), getEncoding(encoding));
    }

    public static byte[] encode2Bytes(String data, String encoding) throws UnsupportedEncodingException {
        return encode(data.getBytes(getEncoding(encoding)));
    }

    public static byte[] decode2Bytes(String data, String encoding) throws UnsupportedEncodingException {
        return decode(data.getBytes(getEncoding(encoding)));
    }

    public static String encode2Str(byte[] byteData, String encoding) throws UnsupportedEncodingException {
        return new String(encode(byteData), getEncoding(encoding));
    }

    public static String decode2Str(byte[] byteData, String encoding) throws UnsupportedEncodingException {
        return new String(decode(byteData), getEncoding(encoding));
    }

    public static String getEncoding(String encoding) {
        if (encoding == null || "".equals(encoding)) {
            return DEFAULT_ENCODING_STRING;
        }
        return encoding;
    }

    public static void main(String[] args) throws Exception {
        String dataString = "ZGL";
        System.out.println("Base64编码前数据：" + dataString);
        String e = encode(dataString, "utf-8");
        System.out.println("Base64编码后数据：" + e);
        System.out.println("Base64解码后数据：" + decode(e, "utf-8"));
    }

}
