package app.service.impl;

import app.dao.DAOProvider;
import app.dao.UserDao;
import app.entity.User;
import app.service.Service;

import java.sql.SQLException;

public class UserService implements Service {
    UserDao userDao;
    User user;

    public UserService() {
        userDao = DAOProvider.getInstance().getUserDao();
    }

    /**
     * Return the User by name. If he not exist, create new user and the return
     * @param name user's name
     * @return the User
     */
    @Override
    public Object get(String name) {

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
