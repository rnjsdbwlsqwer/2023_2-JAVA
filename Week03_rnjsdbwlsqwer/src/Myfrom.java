import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Myfrom extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Myfrom frame = new Myfrom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Myfrom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 237);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(36, 45, 151, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("패스워드");
		lblNewLabel_1.setBounds(36, 70, 151, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("이메일 주소");
		lblNewLabel_2.setBounds(36, 95, 113, 15);
		contentPane.add(lblNewLabel_2);
				
		JLabel lblNewLabel_3 = new JLabel("전화번호");
		lblNewLabel_3.setBounds(36, 120, 57, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("회원 등록하기");
		lblNewLabel_4.setFont(new Font(" ",Font.PLAIN, 16));
		lblNewLabel_4.setBounds(152, 10, 151, 25);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(187, 45, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10); //이름 텍스트필드
				
		passwordField = new JPasswordField();
		passwordField.setBounds(187, 70, 116, 21);
		contentPane.add(passwordField); //패스워드 텍스트필드

		textField_1 = new JTextField();
		textField_1.setBounds(187, 95, 116, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10); //이메일주소 텍스트필드

		textField_2 = new JTextField();
		textField_2.setBounds(187, 120, 116, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10); //이메일주소 텍스트필드
			
		btnNewButton = new JButton("등록하기");
		btnNewButton.setBounds(90, 165, 97, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("취소");
		btnNewButton_1.setBounds(206, 165, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}