package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSON;
import com.hntyy.entity.yeepay.MerchantSubjectInfo;
import com.hntyy.entity.yeepay.RegisterSaasMerchantQuery;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import com.yeepay.g3.sdk.yop.utils.UUIDUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class YeepayControllerTest {

    // 商户私钥
    private static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3YppE52DR/KuOTNlTwpctiLYbRfjMJf2xdb8pf2rwiKkO1B+7zvVb1IaswfoL68kfHP8NXPyElZ1BOO7PJ/j8Xo9Klu/V69oy/nWLtDdz667b581mYspOSCN5ZR1gw1+H7M+G7ZySqeygGhUPc9z03p1z1CDJRW4FAi26uAn2ZtRTkYsMg+D9iVU3NPScLy4WPuX2ZIunXrf7it6WvMwl7OP1q1NKQ/gVPKuIoBb3ra5ypeJ9bJVD0euTPIlTZVn+Rdh7Um6ePspgbdRFkc/JG8QGipJRrCa/YIcm2xvxY3jgpeQmNke7UnSVbz3hqNmA9BnL0D305XGK8jAvmt+7AgMBAAECggEANy7jr0JU/ztiQHLdnvfaWChgbDqVEJGKEsGU4Z7nBjSJct3gIrq3WIfOcuP4I2gzYDpwgYvurNL8vCiurAWiRgcKZW6gAyZyxY+OvZhga3CLcL8DLwEhUYchraEAwyJnd9aJ6FdOG1Ao1VCj7790yPbIOJenugk7fyRVsG87CDsMzM5CPYAftzA6wyGWto+UxY/Dx/Ug6rY5QBZI6uWDvsDRlHzLJ2KmCULj7B1NQ8kKLzplF5SWTrvEVXcIWsP+DGntHmn/3rd5VxduSMzutlEYMvc5nj6RpF7HBuCiaSldZ6xYRDzzDwKOWC6D7kWniNlf3FeJF1NwuMRRvF5uoQKBgQDelCEduyrcNrqgSxAMN6yzpq4GjtiIctGgGel2cZSl8SNCTb4MYr8DiZh7M/MVauO522ly7CvvwJmqY56pylrdKmtgudR6YBCemP6PJTeCMFPj8EFboeBpfi2gXjcRRrUlE86QSIAqUYoSxWKtzNFCyn7GDoOC0CxPAkOt677jiwKBgQDS6+K5kmDUmYe2vC/+4wulJEgYpEYLzQMeKQGsq4FmG84Ac+7JlSgsiLi49OYARtRxzYdH1SXUPYX4taeV23djaxVX1LcodN1+KaO4wkan9HSo7fy4svydUm6pSKom6o0CsZ9/KdRlWtwH5WefqVaSfMIxH2EyUxkDOIkx1pu6kQKBgAdZdM56g6vJ0tfAIsOEgxtbgZuN0/CNegaDVIYosfPYxoVF8+SMzinbvUE0Me6fHO3iJNU6nyjHf0t1BqQsnlt3Lxx+hlmUGnhiLOWlIPQXjG2WXVIdQj+5fuAwvDjB0PFsegGhoznCf4CnK975SF+gOBdqG0WSgiQJuxpfEmqJAoGBAKjiHREPhp7UK9mCRz/klf9t1Jh+eGOcjOGKXf/e92ZF3yV3rnwUBS3bb2URGlSgYhyZP7ehkH+nn2zsLrqMFsUxCc7g0KMBKBSLzL70N9TlpL9ah19wWVqylU7QkwVECxJcHOSaHqnlHYbpBZbO5TW31Vm10YKVDNMKYrfYKasxAoGBANMkVl5Ahwmu/jgL2ETQGa1+n4kCfA6LyeVLoGoAAhseOC3z79PZ6HhF8DltKaNFEaN5gttWlHV1yhRm6Ik05zwAQWdDUWVkU0URiCdg7053BSh2y4WCFbgWg/ALJsqz7Gonbx12mG1Mg2wIeqMG8wubIkzFpsuVpiM6FGAD9uM+";

    // 商户编号
    private static String parentMerchantNo = "10085834246";

    private static String appKey = "app_10085834246";

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main1(String[] args) throws IOException {
        /* 上传文件*/
        //上传文件目前支持文件流，远程文件，本地文件3个模式，下面例子为本地模式

        //请求URI
        String apiUri = "/yos/v1.0/sys/merchant/qual/upload";

        //step1  生成yop请求对象
        //arg0:appkey（举例授权扣款是SQKK+商户编号，亿企通是OPR:+商户编号，具体是什么请参考自己开通产品的手册。
        //arg1:商户私钥，不传这个参数的时候，默认使用配置文件里的isv_private_key
        YopRequest request = new YopRequest(appKey,privateKey);

        //step2 配置参数
        //arg0:固定用merQual
        //arg1:文件路径

        // 本地文件模式（可选）
         request.addFile("merQual",new File("D:/入网资料/法人证件反面照片.PNG"));

        // 远程文件模式（可选）
//        request.addFile("merQual",new URL("https://imgs.yeepay.com/img/1533693637515_shouye0808.jpg").openStream());

        // 流模式（可选）
        // InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("privatekey")));
        // request.addFile("merQual", inputStream);


        //step3 发起请求
        //arg0:接口的uri（参见手册）
        //arg1:配置好参数的请求对象
        YopResponse response = YopRsaClient.upload(apiUri, request);
        System.out.println(response.toString());
    }

    public static void main(String[] args) throws IOException {
//        RegisterSaasMerchantQuery registerSaasMerchantQuery = new RegisterSaasMerchantQuery();
        // ......

//        MerchantSubjectInfo merchantSubjectInfo = new MerchantSubjectInfo();
//        merchantSubjectInfo.setHandLicenceUrl("hello");
//        merchantSubjectInfo.setLicenceNo("3232323");
//        merchantSubjectInfo.setSignName("你好");
//        System.out.println(JSON.toJSONString(merchantSubjectInfo));

        String apiUri = "/rest/v2.0/mer/register/saas/merchant";

        YopRequest request = new YopRequest(appKey,privateKey);
        //step2 配置业务参数（根据对接业务参考相应手册的参数列表）
        // requestNo入网请求号（自定义）
        String requestNo = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(requestNo);
        request.addParam("requestNo", requestNo);
        request.addParam("parentMerchantNo", parentMerchantNo);
        // 入网商户业务角色:  ORDINARY_MERCHANT:标准商户  PLATFORM_MERCHANT:平台商  SETTLED_MERCHANT:入驻商户
        request.addParam("businessRole", "ORDINARY_MERCHANT");
        request.addParam("merchantSubjectInfo", "{ \"signType\":\"ENTERPRISE\", \"licenceNo\":\"91430111MA4L3L082K\", \"licenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608171070120-FbsP9xxiSkKPKSz9cRCJeg-yblBnBoDjFGeIUBBENad.JPG\", \"signName\":\"武汉东方龙餐饮管理有限公司湖南分公司\", \"shortName\":\"东方龙餐饮\",\"openAccountLicenceNo\":\"5510-01475423\",\"openAccountLicenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608171515228-TOsIhNT2S9-zRWqJ5QvLEw-lhJMkAqHaqAjBgEjPNVo.JPG\",\"handLicenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608171821259-XoalHLmkTjSjRdtePK9sKA-BiKKBllpSnGDUrFetUiN.JPG\" }");
        request.addParam("merchantCorporationInfo", "{ \"legalName\":\"马中华\", \"legalLicenceType\":\"ID_CARD\", \"legalLicenceNo\":\"411321197612220723\", \"legalLicenceFrontUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608172320236-kQD9VUzVQR-9Ur57OWZuGQ-EGnBwFrwccevQCdjCUiS.PNG\", \"legalLicenceBackUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608172399569-WwHyWIclS1Sf6RreI-wsjQ-COpEglcveXNlwGnldoMM.PNG\" }");
        request.addParam("merchantContactInfo", "{ \"contactName\":\"陈强\", \"contactMobile\":\"18874149131\", \"contactEmail\":\"xyh@mjjzxyh.com\", \"contactLicenceNo\":\"4301241987071086551\" }");
//        request.addParam("industryCategoryInfo", "");
        request.addParam("businessAddressInfo", "{ \"province\":\"430000\", \"city\":\"430100\", \"district\":\"430111\", \"address\":\"迎新路868号德思勤城市广场A-1项目第A3栋2113号\" }");
//        request.addParam("settlementAccountInfo", "");
        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/registerSaasMerchant");
        request.addParam("productInfo", "[{\"productCode\":\"MINI_PROGRAM_WECHAT_ONLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.1\"},{\"productCode\":\"D1\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.1\"}]");


        //step3 发起请求
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());


        return ;
    }

}
