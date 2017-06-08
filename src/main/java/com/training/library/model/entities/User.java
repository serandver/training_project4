package com.training.library.model.entities;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Role role;

    public static enum Role {
        LIBRARIAN,
        READER
    }

    public static class Builder{
        private int id;
        private String firstName;
        private String lastName;
        private String login;
        private String password;
        private Role role;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public User build(){
            User user = new User();
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            return user;
        }
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
