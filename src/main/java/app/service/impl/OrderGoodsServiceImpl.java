package app.service.impl;

import app.dao.DAOProvider;
import app.entity.Good;
import app.entity.OrderGoods;
import app.service.OrderGoodsService;
import app.utils.GoodsUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderGoodsServiceImpl implements OrderGoodsService {
    DAOProvider dao = DAOProvider.getInstance();

    /**
     * Added product by name to the OrderGoods db table
     * @param name name of product to be added
     * @param orderId id of order in which the product included
     */
    @Override
    public void add(String name, Long orderId) {
        try {
            Good good = DAOProvider.getInstance().getGoodDao().getGood(GoodsUtil.getName(name));
            dao.getOrderGoodsDao().addToOrderGood(orderId, good.getId());
        } catch (SQLException e) {
            System.out.println("Exception with basket. Try again later");
        }
    }

    /**
     * Returns the map contains name of item, its price and number of it in order
     * @param orderId id of order for map building
     * @return the map
     */
    @Override
    public Map<String, Integer> getOrderedGoods(Long orderId) {
        Map<String, Integer> map = new HashMap<>();////
        List<Good> orderedGoods = getGoods(orderId);
        for(Good good : orderedGoods) {
            String item = (good.getName() + " (" + good.getPrice() + " $)");
            int value = 1;
            if (map.containsKey(item)) {
                value = map.get(item) + 1;
                map.remove(item);
            }
            map.put(item, value);
        }
        return map;
    }

    /**
     * Returns the list contains goods in order. Sampling by transferred id
     * @param orderId id of order for list building
     * @return the list
     */
  public List<Good> getGoods(Long orderId) {
      List<Good> orderedGoods = new ArrayList<>();////
      try {
          List<OrderGoods> inCurrentOrder = DAOProvider.getInstance().getOrderGoodsDao().getByOrderId(orderId);

          for (OrderGoods current : inCurrentOrder) {
              orderedGoods.add(DAOProvider.getInstance().getGoodDao().getGood(current.getGoodId()));
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return orderedGoods;
  }
}
