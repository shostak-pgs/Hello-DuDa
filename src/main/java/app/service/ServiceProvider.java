package app.service;

import app.service.impl.OrderService;
import app.service.impl.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceProvider {
    static ApplicationContext context =
            new ClassPathXmlApplicationContext("beans.xml");

    public static ServiceProvider instance = (ServiceProvider) context.getBean("serviceProvider");

    /**
     * Return the single provider object
     * @return the {@link ServiceProvider}
     */
    public static ServiceProvider getInstance() {
        return instance;
    }

    /**
     * The private constructor to ensure the uniqueness of the object
     */
    private ServiceProvider(){
    }

    public static Service userService = (UserService)context.getBean("userService");
    public static Service orderService = (OrderService)context.getBean("orderService");
    public static OrderGoodsService orderGoodsService = (OrderGoodsService)context.getBean("orderGoodsService");

    /**
     * @return the UserService object
     */
    public Service getUserService(){
        return userService;
    }

    /**
     * @return the OrderService object
     */
    public Service getOrderService(){
        return orderService;
    }

    /**
     * @return the OrderGoodsService object
     */
    public OrderGoodsService getOrderGoodsService(){
        return orderGoodsService;
    }
}
