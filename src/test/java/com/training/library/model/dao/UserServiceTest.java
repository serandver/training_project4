package com.training.library.model.dao;

import com.training.library.model.entities.User;
import com.training.library.model.services.UserService;
import com.training.library.model.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserServiceTest {
    private UserService userService;
    private DaoFactory daoFactory;
    private UserDao userDao;
    private User testUser;

    @Before
    public void getUserDao() {
        userService = new UserServiceImpl();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = userService.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Ignore
    @Test
    public void testCreateUser() throws Exception {
        testUser = initTestUser();
        int indexFromUserService = userService.create(testUser);
        int indexFromUser = testUser.getId();
        assertEquals(indexFromUserService, indexFromUser);
    }

    private User initTestUser() {
        return testUser =  new User.Builder()
                .setFirstName("Test")
                .setLastName("Test")
                .setEmail("test")
                .setPassword("123")
                .setRole(User.Role.READER).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        User expectedUser;
        testUser = initTestUser();
        int index = 1;
        Optional<User> result = userService.find(index);
        result.ifPresent(theUser -> assertNotNull(theUser));
        if(result.isPresent()) {
            expectedUser = result.get();
            assertEquals(expectedUser.getFirstName(), testUser.getFirstName());
            assertEquals(expectedUser.getLastName(), testUser.getLastName());
            assertEquals(expectedUser.getEmail(), testUser.getEmail());
            assertEquals(expectedUser.getPassword(), testUser.getPassword());
            assertEquals(expectedUser.getRole(), testUser.getRole());
        }
    }

    @Test
    public void testGetUserLogin() throws Exception {
        String expectedUserLogin = "test";
        Optional<User> result = userService.findByLogin(expectedUserLogin);
        result.ifPresent(theUser -> assertNotNull(theUser));
        if(result.isPresent()) {
            User user = result.get();
            String actualUserLogin = user.getEmail();
            assertEquals(expectedUserLogin, actualUserLogin);
        }
    }

    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setLastName("Another");
        int index = 1;
        Optional<User> result = userService.find(index);
        result.ifPresent(theUser -> assertNotNull(theUser));
        if(result.isPresent()) {
            user = result.get();
        }
    }

    @Ignore
    @Test
    public void testDeleteUser() throws Exception {
        userDao.delete(100500);
        Optional<User> result = userService.find(100500);
        result.ifPresent(theUser -> Assert.assertNull(theUser));
    }


}
