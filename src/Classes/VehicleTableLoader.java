package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VehicleTableLoader {

    
    public void loadTable(JTable table, String sql, String[] cols) {
    try (Connection conn = Database.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        DefaultTableModel model = new DefaultTableModel(cols, 0);

        while (rs.next()) {
            Object[] row = new Object[cols.length];
            for (int i = 0; i < cols.length; i++) {
                row[i] = rs.getObject(i+1); 
            }
            model.addRow(row);
        }

        table.setModel(model);
        table.setAutoCreateRowSorter(true);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
}

