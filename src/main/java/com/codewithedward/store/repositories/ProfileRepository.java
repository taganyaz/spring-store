package com.codewithedward.store.repositories;

import com.codewithedward.store.dtos.UserSummary;
import com.codewithedward.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @EntityGraph(attributePaths = "user")
    @Query("select p.id, u.email from Profile p inner join User  u on u.id = p.id where p.loyaltyPoints > :loyaltyPoints ORDER BY u.email")
    List<UserSummary> findProfiles(int loyaltyPoints);
}
