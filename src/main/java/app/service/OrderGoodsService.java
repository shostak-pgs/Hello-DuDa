package app.service;

import app.entity.Good;
import java.util.List;
import java.util.Map;

public interface OrderGoodsService {

    /**
     * Returns the map contains name of item, its price and number of it in order
     * @param orderId id of order for map building
     * @return the map
     */
    Map<String, Integer> getOrderedGoods(Long orderId);

    /**
     * Added product by name to the OrderGoods db table
     * @param name name of product to be added
     * @param orderId id of order in which the product included
     */
    void add(String name, Long orderId);

    /**
     * Returns the list contains goods in order. Sampling by transferred id
     * @param orderId id of order for list building
     * @return the list
     */
     List<Good> getGoods(Long orderId);
}
