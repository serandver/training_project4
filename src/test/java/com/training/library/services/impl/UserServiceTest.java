package com.training.library.services.impl;

import com.training.library.dao.DaoFactory;
import com.training.library.dao.UserDao;
import com.training.library.model.User;
import com.training.library.services.UserService;
import com.training.library.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userService;
    private DaoFactory mockDaoFactory;
    private UserDao mockUserDao;
    private User testUser;

    private void buildInitData() {
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
    public void shouldGetListOfUsersFromDao()  {
        buildInitData();

        List<User> usersFromMockUserDao = Arrays.asList(mock(User.class), mock(User.class), mock(User.class));

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.findAll()).thenReturn(usersFromMockUserDao);

        List<User> users = userService.findAll();
        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).findAll();
        assertTrue(users.size() == 3);
    }

    @Test
    public void shouldReturnGeneratedIdAfterCreatingNewUser() {
        buildInitData();

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
    public void shouldReturnOptinalUserById() {
        buildInitData();

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
        buildInitData();

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
    public void shouldReturnNumberOfUpdatedRows() {
        buildInitData();

        int expectedNumberOfUpdatedRows = 1;

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.update(testUser)).thenReturn(expectedNumberOfUpdatedRows);

        int actualNumberOfUpdatedRows = userService.update(testUser);
        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).update(testUser);
        assertEquals(expectedNumberOfUpdatedRows, actualNumberOfUpdatedRows);
    }

    @Test
    public void shouldReturnNumberOfDeletedRows() {
        buildInitData();

        int userIdForDeleting = 1;
        int expectedNumberOfDeletedRows = 1;

        when(mockDaoFactory.createUserDao()).thenReturn(mockUserDao);
        when(mockUserDao.delete(userIdForDeleting)).thenReturn(expectedNumberOfDeletedRows);

        int actualNumberOfDeletedRows = userService.delete(userIdForDeleting);

        verify(mockDaoFactory).createUserDao();
        verify(mockUserDao).delete(userIdForDeleting);
        assertEquals(expectedNumberOfDeletedRows, actualNumberOfDeletedRows);
    }
}
