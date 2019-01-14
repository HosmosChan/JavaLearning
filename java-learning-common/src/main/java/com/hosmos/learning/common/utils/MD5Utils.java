package com.hosmos.learning.common.utils;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 */
public class MD5Utils {
    // 对字符串进行MD5编码
    // 16进制下数字到字符的映射数组
    private static String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
            "D", "E", "F"};

    public static String encodeByMD5(String originstr) {
        if (originstr != null) {
            try {
                // 创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 使用指定的字节数组对摘要进行最后的更新，然后完成摘要计算
                byte[] results = md.digest(originstr.getBytes("utf-8"));
                for (int i : results) {
                    System.err.print(i + " ");
                }
                System.err.println("   ");
                // 将得到的字节数组编程字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    // 转换字节数组为十六进制字符串
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (byte b1 : b) {
            builder.append(byteToHexString(b1));
        }
        return builder.toString();
    }

    // 将字节转化成十六进制的字符串
    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String md5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes("utf-8")));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultString;
    }
}