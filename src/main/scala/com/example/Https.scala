package com.example

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.nio.file._



object Https {

  def main(args: Array[String]) {
    val trustStore  = KeyStore.getInstance(KeyStore.getDefaultType())

    val instream = new FileInputStream(new File("/Users/redarqas/projects/resto-app/certs/encryptAndTrust/exampletrust.jks"))
    try {
      trustStore.load(instream, "changeit".toCharArray())
    } finally {
      instream.close()
    }

    val keyStore  = KeyStore.getInstance(KeyStore.getDefaultType())

    val kstream = new FileInputStream(new File("/Users/redarqas/projects/resto-app/certs/clientAuthOwnCA/client.jks"))
    try {
      keyStore.load(kstream, "lnooqzW8cv".toCharArray())
    } finally {
      kstream.close()
    }

    val sslcontext = SSLContexts.custom()
      .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
      .loadKeyMaterial(keyStore,"lnooqzW8cv".toCharArray())
      .build()
    val sslsf = new SSLConnectionSocketFactory(
      sslcontext,
      Array("TLSv1", "TLSv1.1", "TLSv1.2" ),
      null,
      SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER)

    val httpclient = HttpClients.custom()
      .setSSLSocketFactory(sslsf)
      .build()

    val httpget = new HttpGet("https://example.com:8443/")
    val response = httpclient.execute(httpget)
    val entity = response.getEntity()
    println("========================> " +response.getStatusLine())
    response.close()
  }

}
