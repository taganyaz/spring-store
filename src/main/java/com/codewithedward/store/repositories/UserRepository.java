package com.codewithedward.store.repositories;

import com.codewithedward.store.dtos.UserSummary;
import com.codewithedward.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = {"tags", "addresses"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User  u")
    List<User> findAllWithAddresses();

    @Query("select u.id as id, u.email as email from User u inner join Profile p on p.id = u.id where p.loyaltyPoints > :loyaltyPoints")
    List<UserSummary> findLoyalUsers(int loyaltyPoints);
}
