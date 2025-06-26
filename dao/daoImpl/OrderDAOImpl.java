package dao.daoImpl;

import dao.OrderDAO;
import model.Order;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void save(Order order, String userId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO orders (order_id, user_id, total_amount, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, order.getOrderId());
            stmt.setString(2, userId);
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
