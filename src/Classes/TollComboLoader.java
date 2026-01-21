package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;

public class TollComboLoader {

    public void load(JComboBox<String> combo) {
        combo.removeAllItems();

        String sql = "SELECT TollName FROM Tolls ORDER BY Position";

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                combo.addItem(rs.getString("TollName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}