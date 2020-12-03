package com.hntyy.common;

import org.apache.tomcat.util.codec.binary.Base64;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RAS工具类
 *
 * http://web.chacuo.net/netrsakeypair 在线生成非对称加密公钥私钥对、在线生成公私钥对、RSA Key pair create、生成RSA密钥对
 */
public class RSAUtil {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "SHA1WithRSA";

    // 私钥
    public static final String PRIVATE_KEY_DATA = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMujEYJiP4BYgX4L\n" +
            "szI9vJJKYII/RXJQbmlaZ/EZfpEqjjd+Xe4UpAp6acyb5hEIdi+BD6Sj/JAM7S2i\n" +
            "90iaudjZrcA6NaI/dSfa2kClDQnPJGlAvoNhVOw/+rhZwPjE9nkacrCcXWU6ZNz4\n" +
            "kzyvHili6KHx2pmzPR90S2ACTFlRAgMBAAECgYAH8SgxqZtIaitCuzoV74p3Oidh\n" +
            "bQ5MUw67VSdQo+nnx30cdPU839k1/2g47queZnIVkDcH+M1U4OwvJ3f1gPNWi/X0\n" +
            "SowKEwE42oLp3Bf7iMUqX39EslRjhF+g3nxDMUwMTJd9ho+pxOUmTRUPQDkTx6nY\n" +
            "0VbXrg+YVlUcsamuQQJBAOpmybhjhb2DDxBHni937WNtvmERRkII1xQbRqI/T9dm\n" +
            "+iuSMDOvLOn/VmrotyOft1kAgMWtoZ2ic0VdSXhNU/0CQQDeZpZOgeyHOWwl8FgK\n" +
            "1NkKSpWS/iVTRRZMAiyY8hFVzKG5NtMiDSKf1C3KoBJv0V/vtAAAip0NQ38ewoRu\n" +
            "6pjlAkEAqhjpLVtbRAkFN2UhT4URyWNsEfh9KZJMsZSRj0+uWrups1Cklfz69NuM\n" +
            "FIzHyZwvavDIgarcaw3l5GIr3JcQ6QJBANnfsEIq39e9dmhYYzgBOrrtREjjdxdz\n" +
            "aBuAScZv5nGibZCv1/jU22WujEmjtUwb1ptSQGpHYQIcbI1qzCiI5M0CQGIBKHjE\n" +
            "c8ueJEaxr6JrHUwa352Uh+abR1jpH/zmmVW6tmOrdrxfEIy0YWNr5Iy/2cbZ4YAP\n" +
            "lEEY5JFobH+H34M=";
    // 公钥
    public static final String PUBLIC_KEY_DATA = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLoxGCYj+AWIF+C7MyPbySSmCC\n" +
            "P0VyUG5pWmfxGX6RKo43fl3uFKQKemnMm+YRCHYvgQ+ko/yQDO0tovdImrnY2a3A\n" +
            "OjWiP3Un2tpApQ0JzyRpQL6DYVTsP/q4WcD4xPZ5GnKwnF1lOmTc+JM8rx4pYuih\n" +
            "8dqZsz0fdEtgAkxZUQIDAQAB";

    /**
     * 通过字符串生成私钥
     * @param privateKeyData 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey  getPrivateKey(String privateKeyData) throws Exception {
        PrivateKey privatekey = null;
        try {
            //将字符串Base64解码
            byte[] decode = Base64.decodeBase64(privateKeyData);
            // 创建x509证书封装类, 密钥格式:PKCS#8
            PKCS8EncodedKeySpec x509 = new PKCS8EncodedKeySpec(decode);
            // 指定RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 生成私钥
            privatekey =  keyFactory.generatePrivate(x509);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privatekey;
    }


    /**
     * 通过字符串生成公钥
     */
    public static PublicKey getPublicKey(String publicKeyData) {
        PublicKey publicKey = null;
        try {
            byte[] decodeKey = Base64.decodeBase64(publicKeyData);
            X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodeKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * RSA签名
     * @param privateKey
     * @param content
     * @return
     * @throws Exception
     */
    public static String rsaSign(PrivateKey privateKey, String content,String charset) throws Exception {
        // SHA1WithRSA：用SHA1安全哈希算法进行签名，用RSA算法进行加密。
        Signature signet = Signature.getInstance(RSA_ALGORITHM);
        signet.initSign(privateKey);
        signet.update(content.getBytes(charset));
        byte[] signed = signet.sign();
        return new String(Base64.encodeBase64(signed),charset);
    }

    /**
     * 验证签名
     * @param pubKey
     * @param content
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean rsaCheckContent(PublicKey pubKey, String content, String sign) throws Exception {
        System.out.println("进入验证签名方法: content[" + content + "], sign[" + sign + "], charset[" + CHARSET + "]");
        boolean bFlag = false;
        try {
            Signature signetcheck = Signature.getInstance(RSA_ALGORITHM);
            signetcheck.initVerify(pubKey);
            signetcheck.update(content.getBytes(CHARSET));
            if (signetcheck.verify(Base64.decodeBase64(sign.getBytes(CHARSET)))) {
                bFlag = true;
            }
            System.out.println("进入验证签名方法: content[" + content + "], sign[" + sign + "], charset[" + CHARSET + "], result[" + bFlag + "]");
        } catch (Exception e) {
            System.out.println("验证签名异常" + ": content[" + content + "], sign[" + sign + "], charset[" + CHARSET + "]");
            throw new Exception("验证签名异常");
        }
        return bFlag;
    }

    // 测试RSA加密和验证
    public static void main(String[] args) {
        String biz_content="biz_content={\"out_trade_no\":\"2018041813504013504037403740\",\"business_c\n" +
                "ode\":\"01000009\",\"currency\":\"CNY\",\"total_amount\":\"100\",\"subject\":\"银盛 demo\n" +
                "代付测试\",\"bank_name\":\"招商银行威盛支行\",\"bank_city\":\"深圳市\",\"bank_account_\n" +
                "no\":\"900000782233747700\",\"bank_account_name\":\"姓名\",\"bank_account_type\n" +
                "\":\"personal\",\"bank_card_type\":\"debit\"}&charset=UTF-8&method=ysepay.df.sin\n" +
                "gle.quick.accept&notify_url=http://javatest.ngrok.xiaomiqiu.cn/AsyncNotifyAc\n" +
                "tion&partner_id=shanghu_test&sign_type=RSA&timestamp=2018-04-18 13:\n" +
                "50:40&version=3.0";
        PrivateKey privateKey = null;
        try {
            privateKey = RSAUtil.getPrivateKey(PRIVATE_KEY_DATA);
        } catch (Exception e) {
            throw new RuntimeException("RSAUtil.getPrivateKey出错，传参privateData："+PRIVATE_KEY_DATA, e);
        }
        String sign = null;
        try {
            sign = RSAUtil.rsaSign(privateKey, biz_content, CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("RSAUtil.rsaSign出错，传参biz_content："+biz_content, e);
        }
        System.out.println("签名数据sign："+sign);

        // 验证签名 （接收方使用对方公钥验签）
        PublicKey publicKey = RSAUtil.getPublicKey(PUBLIC_KEY_DATA);
        boolean b = false;
        try {
            b = RSAUtil.rsaCheckContent(publicKey, biz_content, sign);
        } catch (Exception e) {
            throw new RuntimeException("RSAUtil.rsaCheckContent出错，传参biz_content："+biz_content+",sign:"+sign, e);
        }
        System.out.println("验证签名结果："+b);
    }

}
