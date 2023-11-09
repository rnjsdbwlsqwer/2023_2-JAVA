package rnjsdbwls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Student1 extends JFrame implements ActionListener {
	
	protected JTextField field1, field2, field3;
	
	public Student1() {
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("학생 등록하기");
		label1.setBounds(20,100,20,20);
		panel.add(label1);
		JLabel label2 = new JLabel("이름");
		field1 = new JTextField(10);
		field1.addActionListener(this);
		JLabel label3 = new JLabel("학번");
		field2 = new JTextField(10);
		field2.addActionListener(this);
		
		JLabel label4 = new JLabel("성적");
		field3 = new JTextField(10);
		field3.addActionListener(this);
		
		
		panel.add(label2);
		panel.add(field1);
		panel.add(label3);
		panel.add(field2);
		panel.add(label4);
		panel.add(field3);
		
		JButton button1 = new JButton("등록하기");
		button1.addActionListener(this);
		panel.add(button1);
		JButton button2 = new JButton("취소");
		
		panel.add(button2);
		
		
		add(panel);
		setSize(300,150);
		setTitle("덕성여대 화이팅");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
}
	
	
		public void actionPerformed(ActionEvent e) {
			
			String text = field1.getText();
			String text1 = field2.getText();
			String text2 = field3.getText();
			
			System.out.println("이름:" + text + "학번:" + text1 + "성적:" + text2);
			
			}
			
			
		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student1 s = new Student1();

	}

}
