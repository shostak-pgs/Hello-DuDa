package app.entity;

import app.service.ContextUtil;
import java.util.Map;

public class Basket {

    private Map<String, Integer> basket;
    private static Basket instance;
    private Long orderId;

    private Basket(Long orderId) {
        this.orderId = orderId;
        basket = ContextUtil.getInstance().getOrderGoodsService().getOrderedGoods(orderId);
    }

    /**
     * @return the basket of current user
    */
    public static Basket getBasket() {
        return instance;
    }

    /**
     * @return the basket of current user. Is the basket is empty, creates new basket
     * @param orderId order for basket creation
     */
    public static Basket getBasket(Long orderId) {
        if (instance == null) {
           instance = new Basket(orderId);
        }
        return instance;
    }

    /**
     * Added chosen item to basket
     * @param item to add
     */
    public void toBasket(String item) {
        ContextUtil.getInstance().getOrderGoodsService().add(item, orderId);
        basket = ContextUtil.getInstance().getOrderGoodsService().getOrderedGoods(orderId);
    }

    /**
     * Used for clearing the basket
     */
    public void clear(){
        instance = null;
    }

    /**
     * @return the map contains chosen goods
     */
    public Map<String, Integer> getGoods() {
        return basket;
    }
}
