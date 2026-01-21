package Classes;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TollTableLoader {

    public void loadTable(JTable table) {
        String sql = "SELECT * FROM Tolls";
        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"TollID", "TollName", "Position"}, 0);
            while(rs.next()) {
                int id = rs.getInt("TollID");
                String name = rs.getString("TollName");
                int pos = rs.getInt("Position");
                model.addRow(new Object[]{id, name, pos});
            }
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
