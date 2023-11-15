package org.example.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * SHA-256加密工具
 *
 * @author zzl
 * @since 2022/3/26
 */
public class StringEncryptUtil {
//    private final static Logger log = LoggerFactory.getLogger(StringUtil.class);

    /**
     * SHA-256加密
     *
     * @param string 明文
     * @return 密文
     */
    public static String sha256Encrypt(String string) {
        MessageDigest md;
        String strDes;
        byte[] bt = string.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println();
//            log.error("sha-256加密失败,请重试", e);
            return "";
        }
        return strDes + "==";
    }

    /**
     * 生成随机uuid字符串
     *
     * @return uuid字符串
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成验证码
     *
     * @param length 长度
     * @return 验证码字符串
     */
    public static String generateCode(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    private static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp;
        for (byte bt : bts) {
            tmp = (Integer.toHexString(bt & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }
}
