package vaishnavim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataUsageDAOImpl implements DataUsageDAO {

    private Connection con;

    public DataUsageDAOImpl() throws Exception {
        // Load MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create connection
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/",
                "root",
                "Vaishnavi@15"   // change if needed
        );
    }

    @Override
    public void addUsage(DataUsageDTO dto) {
        try {
            String sql =
                "INSERT INTO data_usage (mobile_no, data_used, extra_charge, payment_status) " +
                "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dto.getMobileNo());
            ps.setDouble(2, dto.getDataUsed());
            ps.setDouble(3, dto.getExtraCharge());
            ps.setString(4, dto.getPaymentStatus());

            ps.executeUpdate();
            System.out.println("Usage record added successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewUsage() {
        try {
            String sql = "SELECT * FROM data_usage";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("mobile_no") + " | " +
                        rs.getDouble("data_used") + " | " +
                        rs.getDouble("extra_charge") + " | " +
                        rs.getString("payment_status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void payBill(int id) {
        try {
            String sql =
                "UPDATE data_usage SET payment_status = 'PAID' WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Bill paid successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
