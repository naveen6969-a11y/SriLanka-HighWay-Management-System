package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VehicleExitProcessor {

    private int eventId;       
    private double lastTotalCost; 
    private String lastExitLocation;

    public double getLastTotalCost() {
        return lastTotalCost;
    }

    public int getEventId() {
        return eventId;
    }

    
    public boolean calculateExit(String vehicleNo, String exitLocation) {
        vehicleNo = vehicleNo.trim().toUpperCase();
        exitLocation = exitLocation.trim();
        lastExitLocation = exitLocation;

        String findSql = """
            SELECT TOP 1 EventID, EntranceLocation
            FROM VehicleEvents
            WHERE UPPER(LTRIM(RTRIM(VehicleNumber))) = ?
              AND Status = 'IN'
            ORDER BY EntranceTime DESC
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(findSql)) {

            pst.setString(1, vehicleNo);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                System.out.println("No active entrance found for vehicle: " + vehicleNo);
                return false;
            }

            eventId = rs.getInt("EventID");
            String entranceLocation = rs.getString("EntranceLocation");

            int entrancePos = getPosition(conn, entranceLocation);
            int exitPos = getPosition(conn, exitLocation);

            int tollsPassed = Math.abs(exitPos - entrancePos);
            lastTotalCost = tollsPassed * 200; 
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            lastTotalCost = 0;
            return false;
        }
    }

    
    public boolean submitExit() {
        if (eventId == 0) return false; 

        String updateSql = """
            UPDATE VehicleEvents
            SET ExitLocation = ?,
                ExitTime = GETDATE(),
                TotalCost = ?,
                Status = 'COMPLETED'
            WHERE EventID = ?
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement up = conn.prepareStatement(updateSql)) {

            up.setString(1, lastExitLocation);
            up.setDouble(2, lastTotalCost);
            up.setInt(3, eventId);
            up.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private int getPosition(Connection conn, String tollName) throws Exception {
        String sql = "SELECT Position FROM Tolls WHERE TollName = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tollName.trim());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return rs.getInt("Position");
        }
        return 0;
    }
}
