package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class VehicleDeleter {

    public boolean deleteByEventId(int eventId) {

        String sql = "DELETE FROM VehicleEvents WHERE EventID = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, eventId);
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}