package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class TableLoaderBase {

    
    protected abstract String getTableName();

    
    public void loadTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        String sql = "SELECT username, password FROM " + getTableName();

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                model.addRow(new Object[]{username, password});
            }

        } catch (Exception e) {
            System.err.println("Table load error: " + e.getMessage());
        }
    }
}
