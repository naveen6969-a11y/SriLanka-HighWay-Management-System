package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ReportTableLoader {

    private static final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void loadTable(JTable tbl) {
        String sql = "SELECT ReportID, CashierName, ReportType, Description, Location, ReportTime FROM Reports";

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Report ID");
            model.addColumn("Cashier");
            model.addColumn("Report Type");
            model.addColumn("Description");
            model.addColumn("Location");
            model.addColumn("Time");

            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("ReportTime");
                String timeStr = ts != null ? fmt.format(ts) : "";

                model.addRow(new Object[]{
                    rs.getInt("ReportID"),
                    rs.getString("CashierName"),
                    rs.getString("ReportType"),
                    rs.getString("Description"),
                    rs.getString("Location"),
                    timeStr
                });
            }

            tbl.setModel(model);
            tbl.setAutoCreateRowSorter(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
