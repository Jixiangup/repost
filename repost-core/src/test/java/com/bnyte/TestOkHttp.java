package com.bnyte;

import okhttp3.*;
import org.junit.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @auther bnyte
 * @date 2021-09-06 16:11
 * @email bnytezz@163.com
 */
public class TestOkHttp {
    @Test
    public void testResponseType() throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        String bodyStr = "{\"userid\": \"123\",\"username\": \"猪猪侠\"}";
        RequestBody body = RequestBody.create(bodyStr, mediaType);
        Request request = new Request.Builder()
                .url("http://localhost:5001/test?url=localhost:8081/test&param=刘吉祥")
                .post(body)
                .build();
        Response execute = okHttpClient.newCall(request).execute();
        byte[] bytes = execute.body().bytes();
        Object s = bytes;
        String s1 = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s1);
    }
}
