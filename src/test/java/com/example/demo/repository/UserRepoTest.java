package com.example.demo.repository;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void UserExistbyId() {
        User user = new User(1001, "pin", "pin@gmail.com", "092-929-2999");
        userRepository.save(user);
        Boolean actual = userRepository.existsById(1001);
        assertThat(actual).isTrue();
    }
}
