//package app.dao;
//
//import app.dao.impl.GoodDaoImpl;
//import app.dao.impl.OrderDaoImpl;
//import app.dao.impl.OrderGoodsDaoImpl;
//import app.dao.impl.UserDaoImpl;
//import javax.sql.DataSource;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.sql.*;
//
//public class DAOProvider {
//    private static final String CREATE_DB_URL = "jdbc:h2:mem:testdb;INIT=RUNSCRIPT FROM 'classpath:create_tables.sql'\\;RUNSCRIPT FROM 'classpath:populate.sql';DB_CLOSE_DELAY=-1";
//
//    static DataSource dataSource;
//
//    public static UserDao userDao = new UserDaoImpl(dataSource);////
//    public static GoodDao goodDao = new GoodDaoImpl(dataSource);////
//    public static OrderDao orderDao = new OrderDaoImpl(dataSource);////
//    public static OrderGoodsDao orderGoods = new OrderGoodsDaoImpl(dataSource);////
//
//    /**
//     * @return the GoodDao object
//     */
//    public UserDao getUserDao(){
//       return userDao;
//    }
//
//    /**
//     * @return the GoodDao object
//     */
//    public GoodDao getGoodDao(){
//        return goodDao;
//    }
//
//    /**
//     * @return the OrderDao object
//     */
//    public OrderDao getOrderDao(){
//        return orderDao;
//    }
//
//    /**
//     * @return the OrderGoodsDao object
//     */
//    public OrderGoodsDao getOrderGoodsDao(){
//        return orderGoods;
//    }
//
//}
