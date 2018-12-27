package com.hosmos.learning.common.utils;

import java.security.MessageDigest;

public class SecurityUtil {
    /**
     * 消息摘要
     */
    public static class MessageDigestUtil {
        public static byte[] digest(String content, boolean isMd5) throws Exception {
            MessageDigest messageDigest = null;
            String algorithm = isMd5 ? "MD5" : "SHA";
            messageDigest = MessageDigest.getInstance(algorithm);
            return messageDigest.digest(content.getBytes());
        }

        public static byte[] digest1(String content, boolean isMd5) throws Exception {
            MessageDigest messageDigest = null;
            String algorithm = isMd5 ? "MD5" : "SHA";
            messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(content.getBytes());
            return messageDigest.digest();
        }
    }
}