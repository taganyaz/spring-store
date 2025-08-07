package com.codewithedward.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var orderService = context.getBean(OrderService.class);
        orderService.placeOrder();

        var userService = context.getBean(UserService.class);
        var user = new User(
                1L,
                "Test user 1",
                "test1@email.com",
                "1234"
        );

        userService.register(user);

        //userService.register(user);

        context.close();
	}

}
