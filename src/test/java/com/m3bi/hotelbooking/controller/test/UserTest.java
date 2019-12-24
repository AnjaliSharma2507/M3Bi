package com.m3bi.hotelbooking.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.m3bi.hotelbooking.application.HotelBookingApplication;
import com.m3bi.hotelbooking.controller.UserController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=UserController.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = HotelBookingApplication.class)
public class UserTest {
	private TestRestTemplate restTemplate;
    private URL base;
    @LocalServerPort
    int randomServerPort;
    
    @Before
    public void setUp() throws MalformedURLException {
    	restTemplate = new TestRestTemplate("admin@admin.com", "admin");
    	base = new URL("http://localhost:"+randomServerPort);
    }
    
    @Test
    public void getAllUserTest()throws IllegalStateException, IOException {
    	ResponseEntity<String> response = restTemplate.getForEntity(base.toString()+"/user/all", String.class);
    	assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void getUserTest()throws IllegalStateException, IOException {
    	ResponseEntity<String> response = restTemplate.getForEntity(base.toString()+"/user", String.class);
    	assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    

}
