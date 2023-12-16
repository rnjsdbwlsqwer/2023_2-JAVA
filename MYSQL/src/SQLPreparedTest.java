import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPreparedTest {
	 public static Connection makeConnection() {
	        String url = "jdbc:mysql://localhost:3306/book_db";
	        String id = "root";
	        String password = "";
	        Connection con = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            System.out.println("드라이버 적재 성공");
	            con = DriverManager.getConnection(url, id, password);
	            System.out.println("데이터베이스 연결 성공");

	        } catch (ClassNotFoundException e) {
	            System.out.println("드라이버를 찾을 수 없습니다.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("연결에 실패하였습니다.");
	            e.printStackTrace();
	        }

	        return con;
	    }

	public static void main(String[] args) throws SQLException {
		Connection con = makeConnection();
		String query = "SELECT books.title, books.price" + " FROM books" + " WHERE publisher = ?";
		
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "Wiley");
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			String title = rs.getString("title");
			int price = rs.getInt("price");
			System.out.println(title + " " + price);
			
		}
		con.close();
		
		
		
		
		// TODO Auto-generated method stub

	}

}
