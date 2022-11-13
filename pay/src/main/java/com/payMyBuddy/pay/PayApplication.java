package com.payMyBuddy.pay;

import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
	}

}
