package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository; // dao
    @InjectMocks
    private UserServices userServices; // manager

    @BeforeEach
    void setUp() {
        this.userServices = new UserServices(this.userRepository);
    }
    @Test
    public void getUsersTest() {
        // testing
        List<User> list = new ArrayList<User>();
        User user1 = new User(1, "Pin", "pin@gmail.com", "092-992-9899");
        User user2 = new User(2, "Bam", "bam@gmail.com", "090-929-9289");
        User user3 = new User(3, "Fay", "fay@gmail.com", "982-099-0029");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(userRepository.findAll()).thenReturn(list);

        // testing
        List<User> list1 = userServices.getUsers();
        assert(3 == list1.size());
        verify(userRepository, times(1)).findAll();

    }

    @Test
    public void getUserByIdTest() {
        User user = new User(1, "Pin",
                "pin@gmail.com", "092-992-9899");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User user1 = userServices.getUser(1);

        assert("Pin" == user1.getName());
        assert("pin@gmail.com" == user1.getEmail());
        assert("092-992-9899" == user1.getTelephone());
    }

    @Test
    public void addUserTest() {
        User user = new User(1, "Pin",
                "pin@gmail.com", "092-992-9899");
        userServices.addUser(user);
        verify(userRepository, times(1)).save(user);
    }
}
