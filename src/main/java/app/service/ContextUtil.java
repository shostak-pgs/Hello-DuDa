package app.service;

import app.service.impl.OrderService;
import app.service.impl.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ContextUtil implements ApplicationContextAware {
    private ApplicationContext ctx;
    /**
     * @return the UserService object
     */
    public UserService getUserService(){
        return (UserService)ctx.getBean("userService");
    }

    /**
     * @return the OrderService object
     */
    public  OrderService getOrderService(){
        return (OrderService)ctx.getBean("orderService");
    }

    /**
     * @return the OrderGoodsService object
     */
    public OrderGoodsService getOrderGoodsService(){
        return (OrderGoodsService)ctx.getBean("orderGoodsService");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
