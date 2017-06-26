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
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userService;
    private DaoFactory mockDaoFactory;
    private UserDao mockUserDao;
    private User testUser;

    @Before
    public void init() {
        mockDaoFactory = mock(DaoFactory.class);
        mockUserDao = mock(UserDao.class);
        userService = new UserServiceImpl(mockDaoFactory);
        testUser = new User.Builder()
                .setId(1)
                .setFirstName("Test")
                .setLastName("Test")
                .setEmail("test1")
                .setPassword("123")
                .setRole(User.Role.READER)
                .build();
    }

    @Test
    public void testFindAllUsers()  {
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
        int indexToFind = 1;
        User expectedUser = testUser;
        Optional<User> expectedResult = Optional.of(testUser);

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.find(indexToFind)).thenReturn(expectedResult);

        Optional<User> actualResult = userService.find(indexToFind);

        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).find(indexToFind);

        actualResult.ifPresent(theUser -> assertNotNull(theUser));
        if(actualResult.isPresent()) {
            User actualUser = actualResult.get();
            assertEquals(expectedUser, actualUser);
        }
    }

    @Test
    public void testFindUserByEmail() {
        String email = "email";
        User expectedUser = testUser;
        Optional<User> expectedResult = Optional.of(testUser);

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.findByEmail(email)).thenReturn(expectedResult);

        Optional<User> actualResult = userService.findByEmail(email);

        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).findByEmail(email);

        actualResult.ifPresent(theUser -> assertNotNull(theUser));
        if(actualResult.isPresent()) {
            User actualUser = actualResult.get();
            assertEquals(expectedUser, actualUser);
        }
    }

    @Test
    public void testUpdateUser() {
        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        doNothing().when(mockUserDao).update(testUser);

        userService.create(testUser);
        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).update(testUser);
    }

    @Test
    public void testDeleteUser() {
        int userIdForDeleting = 1;
        int expectedNumberOfDeletedRows = 1;

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.delete(userIdForDeleting)).thenReturn(expectedNumberOfDeletedRows);

        int actualNumberOfDeletedRows = userService.delete(userIdForDeleting);
        assertEquals(expectedNumberOfDeletedRows, actualNumberOfDeletedRows);
    }
}
