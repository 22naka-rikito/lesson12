import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbExam4 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            // load JDBC Driver
            Class.forName("org.postgresql.Driver");

            // database connect
            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "axizuser", "axiz");

            // SQL query string
            int param1 = 101;
            String param2 = "地球儀";
            String sql = "SELECT * FROM products WHERE product_id = ? OR product_name = ? ORDER BY product_id";

            // create statement
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, param1);
            stmt.setString(2, param2);

            // execute
            ResultSet rs = stmt.executeQuery();

            // output
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String price = rs.getString("price");

                System.out.println("product_id:" + id + ", product_name:" + name + ", price:" + price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
