package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection conn = null;
    private final String URL_DB = "jdbc:mysql://localhost:3306/bankmanager";
    private final String USER_NAME = "root";
    private final String PASSWORD = "abc@123";

    public Database() {
        getConnection();
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL_DB, USER_NAME, PASSWORD);
            System.out.println("Database connected");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection FAILED!!");
            return null;
        }
    }
    public boolean deleteCustomer(String numbercard) {
    	try {
			PreparedStatement s = conn.prepareStatement("delete from customer where numbercard= ?");
			s.setString(1,numbercard);
			int res = s.executeUpdate();
			return res>=0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
    }
    public boolean addCustomer(String numberCard, String cccd, String name, String numberPhone, int yBirth, String sex) {
        PreparedStatement s = null;
        try {
            s = conn.prepareStatement("INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?)");
            s.setString(1, numberCard);
            s.setString(2, cccd);
            s.setString(3, name);
            s.setString(4, numberPhone);
            s.setInt(5, yBirth);
            s.setString(6, sex);
            int res = s.executeUpdate();
            System.out.println("SUCCESSFUL!!");
            return res >= 0;
        } catch (SQLException e) {
            System.out.println("Add FAILED!!");
            e.printStackTrace();
            return false;
        } finally {
            // Đảm bảo rằng kết nối được đóng sau khi sử dụng
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ResultSet getNumberCard(String nbc) {
        try {
            java.sql.Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT numbercard FROM customer WHERE numbercard= " + nbc);
            return rs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getCustomer() {
    	try {
			Statement s =  conn.createStatement();
			ResultSet rs = s.executeQuery("select* from customer");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    }
    public ResultSet getCustomerByName(String name) {
    	try {
			Statement s = conn.createStatement();
			return s.executeQuery("SELECT * FROM customer WHERE name LIKE '%" + name + "%'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    }
    public boolean updateCustomer(String cccd, String name, String numberPhone, int yBirth, String sex) {
        PreparedStatement s = null;
        try {
            s = conn.prepareStatement("update customer set name= ?, cccd= ?, birthYear= ?, sex= ? where numberphone= ?");
            s.setString(2, cccd);
            s.setString(1, name);
            s.setString(5, numberPhone);
            s.setInt(3, yBirth);
            s.setString(4, sex);
            int res = s.executeUpdate();
            System.out.println("SUCCESSFUL!!");
            return res >= 0;
        } catch (SQLException e) {
            System.out.println("Add FAILED!!");
            e.printStackTrace();
            return false;
        } finally {
            // Đảm bảo rằng kết nối được đóng sau khi sử dụng
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public ResultSet getAccount(String phone, String pw) {
    	try {
    		PreparedStatement s = conn.prepareStatement("select * from account where numberphone= ? and password= ?");
    		s.setString(1, phone);
    		s.setString(2, pw);
    		ResultSet rs = s.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    public ResultSet getUserByNP(String numberphone) {
    	try {
			PreparedStatement s = conn.prepareStatement("select name, c.numbercard, numberphone, balance, establish from customer c inner join carddetail cd on c.numbercard=cd.numbercard where numberphone= ?");
			s.setString(1, numberphone);
			return s.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
    }
    public boolean Deposit_update(String nc, int balance) {
    	try {
			PreparedStatement s = conn.prepareStatement("update carddetail set balance= ? where numbercard= ?");
			s.setInt(1, balance);
			s.setString(2, nc);
			int res = s.executeUpdate();
			return res>=0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    public ResultSet getSDbyNC(String nbc) {
    	try {
			Statement s = conn.createStatement();
			return s.executeQuery("select balance from carddetail where numbercard= "+nbc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
    }
    public boolean updatePass(String phone, String pass) {
    	try {
			PreparedStatement s = conn.prepareStatement("update account set password= ? where numberphone= ?");
			s.setString(1, pass);
			s.setString(2, phone);
			int res = s.executeUpdate();
			return res>=0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
    }
    public ResultSet getLSGD(String nbc) {
    	try {
			Statement s = conn.createStatement();
			return s.executeQuery("select id_trancsaction, datetrancsaction, balance, deposit_amount, withdrawal_amount from historytrancsacsion where numbercard= "+nbc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
    }
    // Đảm bảo rằng kết nối cơ sở dữ liệu được đóng khi không cần thiết
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
