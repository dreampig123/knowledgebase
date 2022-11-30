package org.pegcode.common.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * http工具类 org.apache.httpcomponents
 */
public class HttpUtil {


    private static HttpClient buildClient() {

        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom().setSSLSocketFactory(
                            new SSLConnectionSocketFactory(
                                    //忽略掉对服务器端证书的校验
                                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build()
                                    , NoopHostnameVerifier.INSTANCE))
                    .build();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
        return httpClient;
    }



}
