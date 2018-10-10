package com.etiqa.dsa.ws.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiqa.dsa.ws.restful.config.ApplicationConfig;
import com.etiqa.dsa.ws.restful.model.ApplicationConfiguration;

@RestController
@RequestMapping("/ws")
public class WebServiceController {

	@Autowired
	private ApplicationConfig appConfig;
	
	@Value("${welcome.message}")
	private String welcomeMessage;

	@GetMapping("/welcome")
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	
	@GetMapping(path = "/about", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAppInfo() {
		ApplicationConfiguration app = new ApplicationConfiguration();
		app.setProfile(appConfig.getProfile());
		app.setDescription(appConfig.getDescription());
		app.setVersion(appConfig.getVersion());
		
		return new ResponseEntity<Object>(app, HttpStatus.OK);
	}
}
