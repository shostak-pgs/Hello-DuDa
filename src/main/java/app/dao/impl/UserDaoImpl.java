package app.dao.impl;

import app.dao.UserDao;
import app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
@Lazy
public class UserDaoImpl implements UserDao {
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "userName";
    private static final String SELECT_USER_BY_NAME = "from User WHERE userName = :userName";
    private static final String SELECT_USER = "from User WHERE id = :id";
    private static final String SELECT_ALL_USERS = "from User";
    private static final String DELETE_USER = "delete User WHERE id = :id";

    private SessionFactory factory;

    @Inject
    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Returns the user by the transferred id
     * @param id user's id
     * @return the {@link User}
     */
    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> user;
        try(Session session = factory.openSession()) {
            user = Optional.ofNullable((User)session.createQuery(SELECT_USER).setParameter(ID_COLUMN, id).uniqueResult());
        }
        return user;
    }

    /**
     * Returns the user by the transferred name
     * @param name user's name
     * @return the {@link User}
     */
    @Override
    public Optional<User> getUserByName(String name) {
        Optional<User> user;
        try(Session session = factory.openSession()) {
            user = Optional.ofNullable((User)session.createQuery(SELECT_USER_BY_NAME).setParameter(NAME_COLUMN, name).uniqueResult());
        }
        return user;
    }

    /**
     * Return list contains all users in base
     * @return the list with all users
     */
    @Override
    public List<User> getAllUsers(){
        List<User> users;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery(SELECT_ALL_USERS);
            users = query.getResultList();
        }
        return users;
    }

    /**
     * Add user in base by the transferred name
     * @param userName user's name
     * @param password user's password
     */
    @Override
    public void addUser(String userName, String password, String role){
        try(Session session = factory.openSession()) {
            User user = new User();
            user.setLogin(userName);
            user.setPassword(password);
            user.setRole(role);
            session.save(user);
        }
    }

    /**
     * Delete transferred user by id
     * @param id the user's id
     */
    @Override
    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery(DELETE_USER);
            query.setParameter(ID_COLUMN, id);
            query.executeUpdate();
        }
    }
}
