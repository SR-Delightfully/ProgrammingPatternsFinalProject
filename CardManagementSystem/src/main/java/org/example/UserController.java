package org.example;

public class UserController {
    private Database database;

    public UserController(Database database) {
        this.database = database;
    }

    public void addUser(User user) {
        database.addUser(user);
    }

    public void updateUser(String userID, User updatedUser) {
        database.updateUser(userID, updatedUser);
    }

    public void removeUser(String userID) {
        database.removeUser(userID);
    }
}
