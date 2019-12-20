package app.utils;


import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class GoodsUtilTest36 {

    @Test
    public void testBuildShoppingMap(){
        //Given
        Map<String, Double> expected = new HashMap<>();
        expected.put("War and Peace", 5.5);
        expected.put("The Great Gatsby", 4.5);
        String[] bookArray = new String[] {"War and Peace (5.5 $)", "The Great Gatsby (4.5 $)"};
        //When
        Map<String, Double> actual = GoodsUtil.buildShoppingMap(bookArray);
        //Then
        assertEquals(expected, actual);
    }

    @Test(expected=NullPointerException.class)
    public void testBuildShoppingMapNullCase(){
        //Given
        String[] bookArray = new String[] {"Hamlet by William Shakespeare (3.5 $)", null};
        //When
        Map<String, Double> actual = GoodsUtil.buildShoppingMap(bookArray);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBuildShoppingMapWrongArgs(){
        //Given
        String[] bookArray = new String[] {"War and Peace (5kl$)", "The Great Gatsby (4.5 $)"};
        //When
        Map<String, Double> actual = GoodsUtil.buildShoppingMap(bookArray);
    }

    @Test
    public void testCountTotalPrice() {
        //Given
        Map<String, Double> orderMap = new HashMap<>();
        orderMap.put("War and Peace, t.1", 5.5);
        orderMap.put("The Great Gatsby, t.1", 4.5);
        orderMap.put("War and Peace", 5.5);
        orderMap.put("The Great Gatsby", 4.5);
        Double expected = 20.0;
        //When
        Double actual = GoodsUtil.countTotalPrice(orderMap);
        //Then
        assertEquals(expected, actual);
    }
}