package com.hosmos.learning.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class PayUtil {
    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param key           密钥
     * @param inputCharset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String inputCharset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, inputCharset));
    }

    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param sign          签名结果
     * @param key           密钥
     * @param inputCharset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String inputCharset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, inputCharset));
        return mysign.equals(sign);
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            try {
                return content.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前未指定编码");
            }
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * 生成6位或10位随机数 param codeLength(多少位)
     *
     * @return
     */
    public static String createCode(int codeLength) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            code.append((int) (Math.random() * 9));
        }
        return code.toString();
    }

    private static boolean isValidChar(char ch) {
        if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
            return true;
        }
        // 简体中文汉字编码
        return (ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f);
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map paraFilter(Map sArray) {
        Map result = new HashMap();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (Object key : sArray.keySet()) {
            Object value = sArray.get(key);
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key.toString())
                    || "sign_type".equalsIgnoreCase(key.toString())) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map params) {
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i).toString();
            String value = params.get(key).toString();
            // 拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append("&");
            }
        }
        return prestr.toString();
    }

    /**
     * @param requestUrl    请求地址
     * @param requestMethod 请求方法
     * @param outputStr     参数 encoding从服务器读取数据用的编码方式 默认为UTF-8
     */
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr, String encoding) {
        // 创建SSLContext
        StringBuilder builder = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //往服务器端写内容
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            // 读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, encoding);
            BufferedReader br = new BufferedReader(isr);
            builder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (builder == null) {
            throw new NullPointerException("服务端返回空");
        }
        return builder.toString();
    }

    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    //生成随机字符串
    public static String getNonceStr() {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static synchronized String createOrderNo() {
        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String code = createCode(8);
        return today + code;
    }

    public static void main(String[] args) {
        String sb = "<?xml version=\"1.0\" encoding=\"GB18030\" ?><Root><Head>sdfsadf</Head><Req>sdfsadfsadf</Req></Root>";
        String str = httpRequest("http://localhost:8080/ccb/usps/signcontract", "POST", sb, "UTF-8");
        System.out.println(str);
        //return sb.toString();
    }
}