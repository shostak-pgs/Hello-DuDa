package app.service.impl;

import app.dao.UserDto;
import app.dao.impl.UserDaoImpl;
import app.entity.User;
import app.exception.ServiceException;
import app.utils.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Lazy
public class UserServiceImpl {
    private UserDaoImpl userDao;
    private UserConverter converter;

    @Autowired
    public void setUserDao(UserDaoImpl userDao, UserConverter converter) {
        this.converter = converter;
        this.userDao = userDao;
    }

    /**
     * Return the User by id
     * @param id user's id
     * @return the User
     */
    @Transactional
    public UserDto getUser(Long id) {
        Optional<User> user = userDao.getUserById(id);
        return converter.toDto(user.orElseThrow(() -> new ServiceException("User not found((")));
    }

    /**
     * Return list contains all users in base
     * @return the list with all users
     */
    @Transactional
    public List<UserDto> getAllUsers(){
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userDao.getAllUsers()) {
           userDtoList.add(converter.toDto(user));
        }
        return userDtoList;
    }

    /**
     * Create a new user by the transferred name
     * @param user the UserDto contains all necessary information
     * @return created user
     */
    @Transactional
    public User create(UserDto user) throws ServiceException {
        userDao.addUser(user.getLogin(), user.getPassword(), user.getRole());
        return userDao.getUserById((long)userDao.getAllUsers().size()).orElseThrow(() -> new ServiceException("User can't be created"));
    }

    /**
     * Delete the user by the transferred id
     * @param id the user's id
     */
    @Transactional
    public void delete(final Long id) {
        userDao.delete(id);
    }
}
