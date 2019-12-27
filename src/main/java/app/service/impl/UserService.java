package app.service.impl;

import app.dao.UserDao;
import app.entity.User;

import java.sql.SQLException;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Return the User by name. If he not exist, create new user and the return
     * @param name user's name
     * @return the User
     */
    public Object get(String name) {
        User user = null;
        if (!isExist(name)) {
            user = create(name);
        }
        return user;
    }

    /**
     * Check if user exist in base. Return boolean result of checking
     * @param name user's name for checking
     * @return the result
     */
    private boolean isExist(String name) {
        boolean isPresent = true;
        User user = null;
        try {
            user = userDao.getUserByName(name);
            if (user == null) {
                return false;
            }
        } catch (SQLException e) {
            isPresent = false;
        }
        return isPresent;
    }

    /**
     * Create a new user by the transferred name
     * @param name the user's name
     * @return created user
     */
    private User create(String name) {
        User user = null;
        try {
            userDao.addUser(name, name);
            user = userDao.getUserByName(name);

        } catch (SQLException e) {
            System.out.println("Unsuccessful user creation. Check out data base connection");
            e.printStackTrace();
        }
        return user;
    }
}
