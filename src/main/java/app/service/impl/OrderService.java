package app.service.impl;

import app.dao.OrderDao;
import app.entity.Order;
import java.sql.SQLException;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * Return the Order by User's id. If it not exist, create a new order and the return
     * @param stringId user's id
     * @return the Order
     */
    public Object get(String stringId) {
        Order order = null;
        Long id = Long.parseLong(stringId);
        if (!isExist(id)) {
            order = create(id);
        }
        return order;
    }

    /**
     * Check if order exist in base. Return boolean result of checking
     * @param id user's id for checking
     * @return the result
     */
    private boolean isExist(Long id) {
        boolean isPresent = true;
        Order order = null;
        try {
            order = orderDao.getOrderByUserId(id);
            if (order == null) {
                return false;
            }
        } catch (SQLException e) {
            isPresent = false;
        }
        return isPresent;
    }

    /**
     * Create a new Order by the transferred user's id
     * @param id the user's id
     * @return created user
     */
    private Order create(Long id) {
        Order order = null;

        try {
            orderDao.addToOrder(id);
            order = orderDao.getOrderByUserId(id);
        } catch (SQLException e) {
            System.out.println("Unsuccessful order creation. Check out data base connection");
            e.printStackTrace();
        }
        return order;
    }

    /**
     * Clears basket when the order is finished
     */
    public void updateByUserId(double orderPrice, Long orderId) {
        orderDao.updateOrderById(orderPrice, orderId);
    }
}