package com.codewithedward.store.services;

import com.codewithedward.store.entities.Address;
import com.codewithedward.store.entities.User;
import com.codewithedward.store.repositories.AddressRepository;
import com.codewithedward.store.repositories.ProfileRepository;
import com.codewithedward.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showEntityStates() {

        var user = User.builder()
                .name("John Doe")
                .email("john@gmail.com")
                .password("12345")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient/ Detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient/ Detached");
        }

    }

    @Transactional
    public  void showRelatedEntities() {
//        var profile = profileRepository.findById(2L).orElseThrow();
//        System.out.println(profile.getUser().getEmail());

        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address.getCity());
    }


    public  void persistRelated() {
        var user = User.builder()
                .name("John Doe")
                .email("john@gmail.com")
                .password("12345")
                .build();

        var address =  Address.builder()
                .street("123 Main St")
                .city("Main St")
                .state("Main St")
                .zip("12345")
                .build();

        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(6L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void fetch() {
        var user = userRepository.findByEmail("john@gmail.com").orElseThrow();
        System.out.println(user);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(user -> {
            System.out.println(user);
            user.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public  void fetchProfiles() {
        var profiles = profileRepository.findProfiles(2);
        profiles.forEach(System.out::println);
    }

    @Transactional
    public  void fetchLoyalUsers() {
        var users = userRepository.findLoyalUsers(10);
        users.forEach(System.out::println);
    }
}
