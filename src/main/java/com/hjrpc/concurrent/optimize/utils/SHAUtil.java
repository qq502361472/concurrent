package com.hjrpc.concurrent.optimize.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
import org.apache.commons.codec.binary.Hex;
 
/**
 * @模块名：taf
 * @包名：pers.cc.taf.encryption
 * @类名称： SHAUtil
 * @类描述：【类描述】
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月27日上午11:01:39
 */
public class SHAUtil {
    public static final String CHARSET = "UTF-8";
 
    /**
     * 
     * @方法名：eccrypt
     * @方法描述【方法功能描述】
     * @param info
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午11:10:46
     * @修改人：cc
     * @修改时间：2018年8月27日 上午11:10:46
     */
    public static String eccrypt(String info, String type) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest messageDigest;
        String encdeStr = "";
        messageDigest = MessageDigest.getInstance(type);
        byte[] hash = messageDigest.digest(info.getBytes("UTF-8"));
        encdeStr = Hex.encodeHexString(hash);
        return encdeStr;
    }
 
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try {
            System.out.println("密文是：" + SHAUtil.eccrypt("cc", "SHA"));
            System.out.println("密文是：" + SHAUtil.eccrypt("cc", "SHA-256"));
            System.out.println("密文是：" + SHAUtil.eccrypt("cc", "SHA-512"));
        }
        catch (UnsupportedEncodingException e) {
        }
    }
 
}