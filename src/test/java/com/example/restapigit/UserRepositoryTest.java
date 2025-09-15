package com.example.restapigit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.restapigit.entity.User;
import com.example.restapigit.repository.UserRepository;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindUser() {
        User user = new User();
        user.setName("Rajanichirala");
        user.setEmail("rajanibangaru@gmail.com");
        user.setPhone("9347319203");

        User saved = userRepository.save(user);
        Optional<User> found = userRepository.findById(saved.getUserId());

        assertTrue(found.isPresent());
        assertEquals("Rajanichirala", found.get().getName());
    }
}
