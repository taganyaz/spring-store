package com.codewithedward.store;

import com.codewithedward.store.repositories.UserRepository;
import com.codewithedward.store.services.ProductService;
import com.codewithedward.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var repository = context.getBean(UserRepository.class);

//        var user = repository.findById(1L).orElseThrow();
//        System.out.println(user.getEmail());

//        repository.findAll().forEach(u -> System.out.println(u.getEmail()));

        //repository.deleteById(1L);

//        var userService = context.getBean(UserService.class);
//        userService.fetchLoyalUsers();

        var productService = context.getBean(ProductService.class);
        productService.fetchPaginatedProducts(1,2);

	}

}
