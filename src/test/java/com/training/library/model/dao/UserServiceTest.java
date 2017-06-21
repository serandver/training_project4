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
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }

    @Ignore
    @Test
    public void testGetUserById() throws Exception {
        int expected = 1;
        Optional<User> result = userDao.find(expected);
        result.ifPresent(theUser -> Assert.assertNotNull(theUser));
        if(result.isPresent()) {
            User user = result.get();
            int actual = user.getId();
            Assert.assertEquals(expected, actual);
        }
    }

    @Ignore
    @Test
    public void testGetUserLogin() throws Exception {
        int expected = 1;
        String userLoginToFind = "ser_pov";
        Optional<User> result = userDao.findByLogin(userLoginToFind);
        result.ifPresent(theUser -> Assert.assertNotNull(theUser));
        if(result.isPresent()) {
            User user = result.get();
            int actual = user.getId();
            Assert.assertEquals(expected, actual);
        }
    }

    @Ignore
    @Test
    public void testCreateUser() throws Exception {
        User user = new User.Builder()
                .setId(100500)
                .setFirstName("Test")
                .setLastName("Test")
                .setEmail("test")
                .setPassword("123")
                .setRole(User.Role.READER).build();
        userDao.create(user);
        Optional<User> result = userDao.find(100500);
        result.ifPresent(theUser -> Assert.assertNotNull(theUser));
        if(result.isPresent()) {
            User addedUser = result.get();
            String expected = user.getEmail();
            String actual = user.getEmail();
            Assert.assertEquals(expected, actual);
        }
    }

    @Ignore
    @Test
    public void testDeleteUser() throws Exception {
        userDao.delete(100500);
        Optional<User> result = userDao.find(100500);
        result.ifPresent(theUser -> Assert.assertNull(theUser));
    }

    private User initTestUser() {
        return testUser =  new User.Builder()
                .setId(100500)
                .setFirstName("Test")
                .setLastName("Test")
                .setEmail("test")
                .setPassword("123")
                .setRole(User.Role.READER).build();
    }
}
