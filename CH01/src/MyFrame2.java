
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame2 extends JFrame {
	JTextField id, name, tel, dept, search;
	JButton previousButton, nextButton, InsertButton, ClearButton, deleteButton,
	SearchButton;
	
	
	
	Statement stmt;
	
	public MyFrame2() throws SQLException{
		super("Database Viewer");
		Connection con = makeConnection();
		
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery("SELECT * FROM student");
		
		setLayout(new GridLayout(0,2));
		
		add(new JLabel("stuID", JLabel.CENTER));
		add(id = new JTextField());
		
		add(new JLabel("name", JLabel.CENTER));
		add(name = new JTextField());
		
		add(new JLabel("tel", JLabel.CENTER));
		add(tel = new JTextField());
		
		add(new JLabel("dept", JLabel.CENTER));
		add(dept= new JTextField());
		
		add(new JLabel("검색", JLabel.CENTER));
		add(search = new JTextField());
		
		
	
		previousButton = new JButton("Previous");
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					rs.previous();
					id.setText("" + rs.getInt("stuID"));
					name.setText("" + rs.getString("name"));
					tel.setText("" + rs.getString("tel"));
					dept.setText("" + rs.getString("dept"));
				
					
				}catch(SQLException e) {
					e.printStackTrace();
					
				}
			}
		});
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					rs.next();
					id.setText("" + rs.getInt("stuID"));
					name.setText("" + rs.getString("name"));
					tel.setText("" + rs.getString("tel"));
					dept.setText("" + rs.getString("dept"));
					
					
				}catch(SQLException e) {
					e.printStackTrace();
					
				}
			}
		});
		
		InsertButton = new JButton("Insert");
		InsertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String insertQuery = "INSERT INTO student(stuId, name, tel, dept) VALUES (?, ?, ?, ?)";
					PreparedStatement pstmt = stmt.getConnection().prepareStatement(insertQuery);
					pstmt.setString(1,id.getText());
					pstmt.setString(2,name.getText());
					pstmt.setString(3,tel.getText());
					pstmt.setString(4,dept.getText());
					
					pstmt.executeUpdate();
					System.out.println("insert 성공");
					
					}catch(SQLException e) {
						e.printStackTrace();
					}
			}
		});
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					int bookId = Integer.parseInt(id.getText());
					String deleteQuery = "DELETE FROM student WHERE stuId=?";
					PreparedStatement pstmt = stmt.getConnection().prepareStatement(deleteQuery);
					pstmt.setInt(1, bookId);
					int k = pstmt.executeUpdate();
					if(k>0)
						System.out.println("삭제 성공");
					else
						System.out.println("삭제 실패");
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		SearchButton = new JButton("Search");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String searchKeyword = search.getText();
				try {
					String query = "SELECT * FROM student WHERE name LIKE '%" + searchKeyword + "%'";
					ResultSet searchResult = stmt.executeQuery(query);
					
					if(searchResult.next()) {
						id.setText("" + searchResult.getInt("stuID"));
						name.setText("" + searchResult.getString("title"));
						tel.setText("" + searchResult.getString("publisher"));
						dept.setText("" + searchResult.getString("year"));
						
						
						
					
					
					}else {
						System.out.println("검색 결과가 없습니다.");
						
					}
					
					
				}catch(SQLException e) {
					e.printStackTrace();
					
				}
			}
		});
		
		ClearButton = new JButton("Clear");
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				id.setText("");
				name.setText("");
				tel.setText("");
				dept.setText("");
				
				search.setText("");
			}
		});

		add(previousButton);
		add(nextButton);
		add(InsertButton);
		add(deleteButton);
		add(SearchButton);
		add(ClearButton);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 200);
		setVisible(true);
		
		
		
		
		
	}
	
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/duksung";
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
		// TODO Auto-generated method stub
		new MyFrame2();

	}

}