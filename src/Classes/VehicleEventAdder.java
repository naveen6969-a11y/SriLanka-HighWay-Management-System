package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class VehicleEventAdder {

    public boolean addEntrance(String vehicleNo, String location) {
        String sql = """
            INSERT INTO VehicleEvents
            (VehicleNumber, EntranceLocation, EntranceTime, Status)
            VALUES (?, ?, GETDATE(), 'IN')
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, vehicleNo);
            pst.setString(2, location);

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
