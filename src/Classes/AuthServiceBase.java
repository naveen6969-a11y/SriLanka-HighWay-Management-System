package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AuthServiceBase {

    
    protected abstract String getTableName();

    public boolean authenticate(String username, String password) {
        String sql = "SELECT 1 FROM " + getTableName() + " WHERE username = ? AND password = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            System.err.println("Auth error: " + e.getMessage());
            return false;
        }
    }
}
