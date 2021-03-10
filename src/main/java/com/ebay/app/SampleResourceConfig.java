package com.ebay.app;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/samplesvc/v1")
public class SampleResourceConfig extends Application {

	private final Feature etsFeature;
	
	@Autowired
	public SampleResourceConfig(@Qualifier("ets-feature") Feature etsFeature){
		this.etsFeature = etsFeature;
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> providers = new LinkedHashSet<Class<?>>();
		providers.add(SampleResource.class);
		return providers;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> providers = new LinkedHashSet<Object>();
		providers.add(etsFeature);
		return providers;
	}

}
