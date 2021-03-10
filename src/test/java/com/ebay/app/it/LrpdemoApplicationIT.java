package com.ebay.app.it;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebay.jaxrs.client.config.ConfigurationBuilder;
import com.ebay.platform.raptor.cosadaptor.exceptions.TokenCreationException;
import com.ebay.platform.raptor.cosadaptor.token.ISecureTokenManager;
import com.ebay.raptor.test.it.IntegrationTest;
import com.ebay.raptor.test.it.SmokeTest;

/**
 * Integration test class name ends with "IT" and is annotated with @Category(IntegrationTest.class)
 *
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
    "ginger-client.testService.testClient.endpointUri=http://",
    "ginger-client.testService.testClient.readTimeout=5000" })
public class LrpdemoApplicationIT {

  @LocalServerPort
  private int port;

  @Value("${poolUrl:}")
  private String injectedPoolUrl;

  @Inject
  private ISecureTokenManager tokenGenerator;


  private String getPoolUrl() {
    if (injectedPoolUrl == null || injectedPoolUrl.isEmpty()) {
      return "http://localhost:" + port;
    }
    return injectedPoolUrl;
  }

  /**
   * This is a sample test case for how to mark Smoke Test via Junit 4 annotation @Category.
   * Please refer to http://go/rioit
   * for how to mark SmokeTest in other test frameworks such as TestNG, Breeze and JUnit 5.
   */
  @Category(SmokeTest.class)
  @Test
  public void testHello() throws TokenCreationException {
    Configuration configuration = ConfigurationBuilder.newConfig("testService.testClient");
    Client client = ClientBuilder.newClient(configuration);
    String response = client.target(getPoolUrl()).path("/samplesvc/v1/sample/hello").request().header("Authorization", tokenGenerator.getToken().getAccessToken()).get(String.class);
    Assert.assertEquals("Hello from Raptor IO", response);

  }

  @Test
  public void testClient() throws TokenCreationException {
    Configuration configuration = ConfigurationBuilder.newConfig("testService.testClient");
    Client client = ClientBuilder.newClient(configuration);
    String response = client.target(getPoolUrl()).path("/samplesvc/v1/sample/helloclient").request().header("Authorization", tokenGenerator.getToken().getAccessToken()).get(String.class);
    Assert.assertEquals("Hello from Raptor IO", response);
  }

}
