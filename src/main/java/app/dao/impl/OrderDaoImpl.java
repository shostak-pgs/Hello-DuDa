package app.dao.impl;

import app.dao.OrderDao;
import app.entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class OrderDaoImpl implements OrderDao {
    private static final String SELECT_ORDER_BY_USER_SQL_STATEMENT = "SELECT * FROM Orders WHERE userId = ?";
    private static final String INSERT_ORDER_SQL_STATEMENT = "INSERT INTO Orders (userId) VALUES (?)";
    private static final String UPDATE_ORDER_SQL_STATEMENT = "UPDATE Orders SET totalPrice = ? WHERE userId = ?";

    private final DataSource dataSource;

    public OrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Returns the order by the transferred user's id
     * @param id user's id
     * @return the {@link Order}
     * @throws SQLException An exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public Order getOrderByUserId(Long id) throws SQLException {
        Order order = null;
        try (PreparedStatement st = dataSource.getConnection().prepareStatement(SELECT_ORDER_BY_USER_SQL_STATEMENT)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getLong("id"), rs.getLong("userId"), rs.getDouble("totalPrice"));
            }
        }
        return order;
    }

    /**
     * Add to Order table by the transferred user's id
     * @param userId id user's id
     * @throws SQLException An exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public void addToOrder(Long userId) throws SQLException {
        try (PreparedStatement st = dataSource.getConnection().prepareStatement(INSERT_ORDER_SQL_STATEMENT)) {
            st.setLong(1, userId);
            st.executeUpdate();
        }
    }

    /**
     * Update user's Order with transferred price
     * @param totalPrice price to be assigned to the order
     * @param userId id of user to update
     */
    @Override
    public void updateOrderById(double totalPrice, long userId) {
        try (PreparedStatement st = dataSource.getConnection().prepareStatement(UPDATE_ORDER_SQL_STATEMENT)) {
            st.setDouble(1, totalPrice);
            st.setLong(2, userId);
        } catch (SQLException e){
            System.out.println("Update order exception");
            e.printStackTrace();
        }
    }
}
