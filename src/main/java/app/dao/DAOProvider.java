package app.dao;

import app.dao.impl.GoodDaoImpl;
import app.dao.impl.OrderDaoImpl;
import app.dao.impl.OrderGoodsDaoImpl;
import app.dao.impl.UserDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;

public class DAOProvider {
    private static final String CREATE_DB_URL = "jdbc:h2:mem:testdb;INIT=RUNSCRIPT FROM 'classpath:create_tables.sql'\\;RUNSCRIPT FROM 'classpath:populate.sql';DB_CLOSE_DELAY=-1";

    static Connection connection;

 
    public static final DAOProvider instance = new DAOProvider();

    /**
     * Return the single provider object
     * @return the {@link DAOProvider}
     */
    public static DAOProvider getInstance() {
        return instance;
    }

    /**
     * The constructor establishes a connection and creates the provider object
     */
    private DAOProvider(){
        try {
            connection = DriverManager.getConnection(CREATE_DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserDao userDao = new UserDaoImpl(connection);////
    public static GoodDao goodDao = new GoodDaoImpl(connection);////
    public static OrderDao orderDao = new OrderDaoImpl(connection);////
    public static OrderGoodsDao orderGoods = new OrderGoodsDaoImpl(connection);////

    /**
     * @return the GoodDao object
     */
    public UserDao getUserDao(){
       return userDao;
    }

    /**
     * @return the GoodDao object
     */
    public GoodDao getGoodDao(){
        return goodDao;
    }

    /**
     * @return the OrderDao object
     */
    public OrderDao getOrderDao(){
        return orderDao;
    }

    /**
     * @return the OrderGoodsDao object
     */
    public OrderGoodsDao getOrderGoodsDao(){
        return orderGoods;
    }

}
