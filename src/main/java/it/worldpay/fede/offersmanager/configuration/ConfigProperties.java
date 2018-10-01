package it.worldpay.fede.offersmanager.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "basepath")
public class ConfigProperties {

	
	private String basepath;

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	
	
}
