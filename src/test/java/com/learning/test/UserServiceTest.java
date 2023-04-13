package com.learning.test;

import com.learning.domains.entities.User;
import com.learning.domains.repositories.UserRepository;
import com.learning.domains.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @MockBean
    UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Before
    public void mockUserRepository(){
        List<User> userList = new ArrayList<>();
        for(int i = 0 ; i < 10; i++){
            userList.add(new User(1L,"Nguyen Van"+ i, null,i+"@gmail.com","password","0914917499" + i));
        }
        Mockito.when(userRepository.findAll())
                .thenReturn(userList);
    }

    @Test
    public void testGetAll(){
        Assert.assertEquals(10, userService.getAllSize());
    }
}
