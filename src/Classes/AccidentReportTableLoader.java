/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AccidentReportTableLoader {

    public void loadTable(JTable tbl) {
        String sql = "SELECT ReportID, VehicleNumber, Location, Description, TimeReported, ReportedBy FROM AccidentReports";

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Report ID");
            model.addColumn("Vehicle Number");
            model.addColumn("Location");
            model.addColumn("Description");
            model.addColumn("Time");
            model.addColumn("Reported By");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ReportID"),
                    rs.getString("VehicleNumber"),
                    rs.getString("Location"),
                    rs.getString("Description"),
                    rs.getString("TimeReported"),
                    rs.getString("ReportedBy")
                });
            }

            tbl.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
