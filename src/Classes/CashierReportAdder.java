package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CashierReportAdder {

    public boolean submitReport(
            String reportType,
            String description,
            String vehicleNumber,
            String location,
            String cashierName
    ) {

        String sql;

        try (Connection conn = Database.getConnection()) {

            
            if (reportType.equalsIgnoreCase("Accident")) {

                sql = """
                    INSERT INTO AccidentReports
                    (VehicleNumber, Location, Description, ReportedBy)
                    VALUES (?, ?, ?, ?)
                """;

                try (PreparedStatement pst = conn.prepareStatement(sql)) {
                    pst.setString(1, vehicleNumber);
                    pst.setString(2, location);
                    pst.setString(3, description);
                    pst.setString(4, cashierName);
                    pst.executeUpdate();
                }

            }
            
            else {

                sql = """
                    INSERT INTO Reports
                    (CashierName, ReportType, Description, Location)
                    VALUES (?, ?, ?, ?)
                """;

                try (PreparedStatement pst = conn.prepareStatement(sql)) {
                    pst.setString(1, cashierName);
                    pst.setString(2, reportType);
                    pst.setString(3, description);
                    pst.setString(4, location);
                    pst.executeUpdate();
                }
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}