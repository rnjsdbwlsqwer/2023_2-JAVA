import java.awt.*;
import javax.swing.*;

public class Week03_01  extends JFrame{
	public Week03_01() {
		setSize(500, 200);
		setLayout(new BorderLayout());
		setTitle("회원 등록하기");
		
		JPanel p= new JPanel();
		add(p);
		
		JLabel l= new JLabel("회원 등록하기");
		JLabel l1 = new JLabel("이름");
		JLabel l2 = new JLabel("패스워드");
		JLabel l3 = new JLabel("이메일 주소");
		JLabel l4 = new JLabel("전화번호");
		
		JTextField f1 = new JTextField(10);
		JTextField f2 = new JTextField(10);
		JTextField f3 = new JTextField(10);
		JTextField f4 = new JTextField(10);
		
		JButton b1 = new JButton("등록하기");
		JButton b2 = new JButton("취소");
		
		p.add(l, "North");
		p.add(l1, "West");
		p.add(f1, "East");
		p.add(l2, "West");
		p.add(f2, "East");
		p.add(l3, "West");
		p.add(f3, "East");
		p.add(l4, "West");
		p.add(f4, "East");
		
		
		p.add(b1, "South");
		p.add(b2, "South");
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Week03_01 w = new Week03_01(); 

	}

}
