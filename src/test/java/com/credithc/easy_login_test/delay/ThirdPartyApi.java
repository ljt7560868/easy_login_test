package com.credithc.easy_login_test.delay;


public class ThirdPartyApi {

    public static String callThirdPartyApi(String input) {
        // 模拟调用三方接口
        if (Math.random() < 0.5) {
            return "Processed " + input;
        } else {
            return null;
        }
    }
}
