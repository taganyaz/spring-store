package com.codewithedward.store;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> users;

    public InMemoryUserRepository() {
        users = new HashMap<>();
    }

    @Override
    public void save(User user)  {
        System.out.println("saving user: " + user);
        users.put(user.getEmail(), user);

    }

    @Override
    public User findByEmail(String email) {
        return users.getOrDefault(email, null);
    }
}
