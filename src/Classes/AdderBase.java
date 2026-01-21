package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class AdderBase {

    
    protected abstract String getTableName();

    
    public boolean add(String username, String password) {
        String sql = "INSERT INTO " + getTableName() + " (username, password) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, password);
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("Add error (" + getTableName() + "): " + e.getMessage());
            return false;
        }
    }
}
