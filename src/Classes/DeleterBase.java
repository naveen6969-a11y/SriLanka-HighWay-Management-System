package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class DeleterBase {

    
    protected abstract String getTableName();

    
    public boolean delete(String username) {
        String sql = "DELETE FROM " + getTableName() + " WHERE username = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("Delete error (" + getTableName() + "): " + e.getMessage());
            return false;
        }
    }
}
