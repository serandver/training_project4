package com.training.library.model.dao;

import com.training.library.model.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class TestUserDao {
    DaoFactory daoFactory;
    UserDao userDao;

    @Before
    public void getUserDao() {
        daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.createUserDao();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> list = userDao.findAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

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

    @Test
    public void testCreateUser() throws Exception {
        User user = new User.Builder()
                .setId(100500)
                .setFirstName("Test")
                .setLastName("Test")
                .setLogin("test")
                .setPassword("123")
                .setRole(User.Role.READER).build();
        userDao.create(user);
        Optional<User> result = userDao.find(100500);
        result.ifPresent(theUser -> Assert.assertNotNull(theUser));
        if(result.isPresent()) {
            User addedUser = result.get();
            String expected = user.getLogin();
            String actual = user.getLogin();
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testDeleteUser() throws Exception {
        userDao.delete(100500);
        Optional<User> result = userDao.find(100500);
        result.ifPresent(theUser -> Assert.assertNull(theUser));
    }
}
