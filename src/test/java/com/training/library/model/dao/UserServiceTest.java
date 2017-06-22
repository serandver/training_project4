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
    private UserService userService = new UserServiceImpl();
    private User testUser = new User.Builder()
            .setId(5)
            .setFirstName("Test")
            .setLastName("Test")
            .setEmail("test1")
            .setPassword("123")
            .setRole(User.Role.READER)
            .build();

    @Test
    public void testCreateUser() throws Exception {
        int indexFromUserService = userService.create(testUser);
        int indexFromUser = testUser.getId();
        assertEquals(indexFromUserService, indexFromUser);
    }

    @Test
    public void testFindAllUsers() throws Exception {
        List<User> users = userService.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testFindUserById() throws Exception {
        int index = testUser.getId();
        Optional<User> result = userService.find(index);
        result.ifPresent(theUser -> assertNotNull(theUser));
        if(result.isPresent()) {
            User expectedUser = result.get();
            assertEquals(expectedUser.getFirstName(), testUser.getFirstName());
            assertEquals(expectedUser.getLastName(), testUser.getLastName());
            assertEquals(expectedUser.getEmail(), testUser.getEmail());
            assertEquals(expectedUser.getPassword(), testUser.getPassword());
            assertEquals(expectedUser.getRole(), testUser.getRole());
        }
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        String expectedUserEmail = testUser.getEmail();
        Optional<User> result = userService.findByLogin(expectedUserEmail);
        result.ifPresent(theUser -> assertNotNull(theUser));
        if(result.isPresent()) {
            User user = result.get();
            String actualUserLogin = user.getEmail();
            assertEquals(expectedUserEmail, actualUserLogin);
        }
    }

    @Test
    public void testUpdateUser() throws Exception {
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
    public void testDeleteUser() throws Exception {
        int userIdForDeleting = testUser.getId();
        userService.delete(userIdForDeleting);
        Optional<User> result = userService.find(userIdForDeleting);
        result.ifPresent(theUser -> Assert.assertNull(theUser));
    }
}
