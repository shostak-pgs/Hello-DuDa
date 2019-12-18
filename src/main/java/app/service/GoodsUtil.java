package app.service;

import javax.servlet.ServletContext;
import java.util.AbstractMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GoodsUtil {

    /**
     * The method creates a {@link Map} for storing the list of purchases and their prices
     * in a key-value format. The key is a String data type, the value is double data type.
     * @param record the array contains String representation of purchases made by user
     * @return created purchases map
     */
    public static void toBasket(String record, Map<String, Double> shoppingMap){
            Map.Entry<String, Double> pair = buildEntry(record);
            String key = pair.getKey();
            Double value = pair.getValue();
            if(shoppingMap.containsKey(pair.getKey())) {
                value = value + shoppingMap.get(key);
                shoppingMap.remove(key);
            }
            shoppingMap.put(key, value);
        }

    public static Map<String, Double> buildShoppingMap(String[] order){
        Map<String, Double> shoppingMap = new HashMap<>();
        for(String record : order) {
            Map.Entry<String, Double> pair = buildEntry(record);
            shoppingMap.put(pair.getKey(), pair.getValue());
        }
        return shoppingMap;
    }

    /**
     * The method creates a {@link Map.Entry} object that storing the purchase and its price
     * in a key-value format for building a list of purchases. The key is a String data type,
     * the value is double data type.
     * @param record String representation of purchase
     * @return {@link Map.Entry} object that storing the purchase and its price
     */
    private static Map.Entry<String, Double> buildEntry(String record) {
        if(record == null) {
            throw new NullPointerException("One argument is null! Check out web.xml parameters!");
        }
        Map.Entry<String, Double> entry;
        Pattern servletParameters = Pattern.compile("(.+)\\s{1}\\(([0-9]+\\.[0-9]+)\\s\\$\\)");
        Matcher matcher = servletParameters.matcher(record);
        Double price = null;
        String product = null;
        while (matcher.find()) {
            product = matcher.group(1);
            price = Double.parseDouble(matcher.group(2));
        }
        if(product == null | price == null) {
           throw new IllegalArgumentException("Parsing exception was found. Check out web.xml parameters for pattern according!");
        }
        entry = new AbstractMap.SimpleEntry<>(product, price);
        return entry;
    }

    /**
     * The method creates a {@link Map} for storing the list of goods and their prices
     * in a key-value format. The list is built from the {@link ServletContext} object passed to the method
     * @param servletContext ServletContext object in which the necessary data is stored
     * @return the list of goods and their prices storied in map
     */
    public static Map<String, Double> buildGoodsMap(final ServletContext servletContext) {
        Map<String, Double> goodsMap = new HashMap<>();
        Enumeration<java.lang.String> goods = servletContext.getInitParameterNames();
        while (goods.hasMoreElements()) {
            String name = goods.nextElement();
            goodsMap.put(name, Double.parseDouble(servletContext.getInitParameter(name)));
        }
        return goodsMap;
    }

    /**
     * Calculates the amount of the completed order
     * @param orderMap {@link Map} contains completed order
     * @return calculated price
     */
    public static double countTotalPrice(Map<String, Double> orderMap) {
        double totalPrice = 0;
        for (Map.Entry<String, Double> pair : orderMap.entrySet()) {
            totalPrice += pair.getValue();
        }
        return totalPrice;
    }
}
