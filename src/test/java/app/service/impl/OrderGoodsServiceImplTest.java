package app.service.impl;

import app.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderGoodsServiceImplTest {
    @Mock
    private DAOProvider daoMock;
    @Mock
    private GoodDao goodDaoMock;
    @Mock
    private OrderGoodsDao orGoodsDaoMock;
    @Mock
    private OrderGoodsServiceImpl orGoodsServiceMock;
    @Mock
    private ServiceProvider serviceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getGoods() throws SQLException, ServiceException {
        //Given
        List<OrderGoods> inCurrentOrder = Arrays.asList(new OrderGoods(1L, 3L, 5L),
                                                        new OrderGoods(2L, 3L, 6L));
        List<Good> expected = Arrays.asList(new Good(5L, "Toy", 2.0),
                                         new Good(6L, "Car", 80.0));
        when(serviceMock.getOrderGoodsService()).thenReturn(orGoodsServiceMock);
        when(orGoodsServiceMock.getGoods(3L)).thenReturn(expected);

        //When
        List<Good> actual = serviceMock.getOrderGoodsService().getGoods(3L);
        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getOrderedGoods() throws ServiceException {
        List<Good> goodsList = Arrays.asList(new Good(1L, "Toy", 2.0),
                                            new Good(2L, "Toy", 2.0),
                                            new Good(3L, "House", 255.0),
                                            new Good(4L, "Car", 80.0));

        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("Toy (2.0 $)", 2);
        expectedMap.put("Toy (255.0 $)", 1);
        expectedMap.put("Toy (80.0 $)", 1);

        ServiceProvider serviceProviderMock = mock(ServiceProvider.class);
        when(serviceProviderMock.getOrderGoodsService()).thenReturn(orGoodsServiceMock);
        orGoodsServiceMock.setDao(daoMock);
        when(orGoodsServiceMock.getGoods(2L)).thenReturn(goodsList);
        when(orGoodsServiceMock.getOrderedGoods(2L)).thenReturn(expectedMap);
        //When
        Map<String, Integer> actual = serviceProviderMock.getOrderGoodsService(). getOrderedGoods(2L);
        //Then
        assertEquals(expectedMap, actual);
    }

}
