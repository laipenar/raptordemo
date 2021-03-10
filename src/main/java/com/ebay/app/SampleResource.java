package com.ebay.app;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import com.ebay.raptor.gen.api.SampleApi;

@Path("/")
@Component
public class SampleResource implements SampleApi {

  private final WebTarget target;
  
  @Inject
  public SampleResource(@Named("myService.myClient") WebTarget target){
    this.target = target;
  }

  @Override
  public Response hello() {
    String message =  "Hello from Raptor IO";
    return Response.ok(message).build();
  }

  @Override
  public Response testClient() {
    String clientResponse = target.path("samplesvc/v1/sample/hello").request().get(String.class);
    return Response.ok(clientResponse).build();
  }
  
  @Override
  public Response version() {
    return Response.ok("1.0.0").build();
  }
}
