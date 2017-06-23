package com.training.library.model.dao;

import com.training.library.model.entities.User;
import com.training.library.model.services.UserService;
import com.training.library.model.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService userService;
    private DaoFactory mockDaoFactory;
    private UserDao mockUserDao;

    private User testUser = new User.Builder()
            .setId(1)
            .setFirstName("Test")
            .setLastName("Test")
            .setEmail("test1")
            .setPassword("123")
            .setRole(User.Role.READER)
            .build();

    @Test
    public void testFindAllUsers()  {
        mockDaoFactory = mock(DaoFactory.class);
        mockUserDao = mock(UserDao.class);
        userService = new UserServiceImpl(mockDaoFactory);
        List<User> usersFromMockUserDao = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            usersFromMockUserDao.add(mock(User.class));
        }

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.findAll()).thenReturn(usersFromMockUserDao);

        List<User> users = userService.findAll();
        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).findAll();
        assertNotNull(users);
        assertTrue(users.size() == 3);
    }


    @Test
    public void testCreateUser() {
        mockDaoFactory = mock(DaoFactory.class);
        mockUserDao = mock(UserDao.class);
        userService = new UserServiceImpl(mockDaoFactory);
        User mockUser = mock(User.class);
        int expectedId = 1;

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.create(mockUser)).thenReturn(expectedId);

        int actualId = userService.create(mockUser);
        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).create(mockUser);
        assertEquals(expectedId, actualId);
    }


    @Test
    public void testFindUserById() {
        mockDaoFactory = mock(DaoFactory.class);
        mockUserDao = mock(UserDao.class);
        userService = new UserServiceImpl(mockDaoFactory);
        int indexToFind = 1;
        Optional<User> expectedResult = Optional.of(testUser);

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.find(indexToFind)).thenReturn(expectedResult);

        Optional<User> actualResult = userService.find(indexToFind);

        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).find(indexToFind);
        actualResult.ifPresent(theUser -> assertNotNull(theUser));
        if(actualResult.isPresent()) {
            User actualUser = actualResult.get();
            assertEquals(testUser.getFirstName(), actualUser.getFirstName());
            assertEquals(testUser.getLastName(), actualUser.getLastName());
            assertEquals(testUser.getEmail(), actualUser.getEmail());
            assertEquals(testUser.getPassword(), actualUser.getPassword());
            assertEquals(testUser.getRole(), actualUser.getRole());
        }
    }

    @Test
    public void testFindUserByEmail() {
        mockDaoFactory = mock(DaoFactory.class);
        mockUserDao = mock(UserDao.class);
        userService = new UserServiceImpl(mockDaoFactory);
        String email = "email";
        Optional<User> expectedResult = Optional.of(testUser);

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.findByEmail(email)).thenReturn(expectedResult);

        Optional<User> actualResult = userService.findByEmail(email);

        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).findByEmail(email);
        actualResult.ifPresent(theUser -> assertNotNull(theUser));
        if(actualResult.isPresent()) {
            User actualUser = actualResult.get();
            assertEquals(testUser.getFirstName(), actualUser.getFirstName());
            assertEquals(testUser.getLastName(), actualUser.getLastName());
            assertEquals(testUser.getEmail(), actualUser.getEmail());
            assertEquals(testUser.getPassword(), actualUser.getPassword());
            assertEquals(testUser.getRole(), actualUser.getRole());
        }
    }
/*
    @Test
    public void testUpdateUser() {
        int userIdForUpdating = testUser.getId();

        String updatedFirstName = "Another first name";
        String updatedLastName = "Another last name";
        String updatedPass = "Another pass";
        String updatedEmail = "Another email";
        User.Role updatedUserRole = User.Role.LIBRARIAN;

        Optional<User> beforeUpdate = userService.find(userIdForUpdating);
        beforeUpdate.ifPresent(theUser -> assertNotNull(theUser));
        if(beforeUpdate.isPresent()) {
            User userBeforeUpdating = beforeUpdate.get();
            userBeforeUpdating.setFirstName(updatedFirstName);
            userBeforeUpdating.setLastName(updatedLastName);
            userBeforeUpdating.setPassword(updatedPass);
            userBeforeUpdating.setEmail(updatedEmail);
            userBeforeUpdating.setRole(updatedUserRole);
            userService.update(userBeforeUpdating);
        }

        User updatedUser = new User();
        Optional<User> afterUpdate = userService.find(userIdForUpdating);
        afterUpdate.ifPresent(theUser -> assertNotNull(theUser));
        if(afterUpdate.isPresent()) {
            updatedUser = afterUpdate.get();
        }
        assertEquals(updatedFirstName, updatedUser.getFirstName());
        assertEquals(updatedLastName, updatedUser.getLastName());
        assertEquals(updatedPass, updatedUser.getEmail());
        assertEquals(updatedEmail, updatedUser.getPassword());
        assertEquals(updatedUserRole, updatedUser.getRole());
    }

    @Test
    public void testDeleteUser() {
        int userIdForDeleting = testUser.getId();
        userService.delete(userIdForDeleting);
        Optional<User> result = userService.find(userIdForDeleting);
        result.ifPresent(theUser -> Assert.assertNull(theUser));
    }
    */
}
