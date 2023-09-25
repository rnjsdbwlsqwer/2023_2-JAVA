import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Myframe extends JFrame
{
	JButton button;
	JTextField t1;
	JTextField t2;
	public Myframe()
	{
		setSize(450,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mile->Km");
				
		JPanel panel1=new JPanel();
		JLabel label=new JLabel("마일을 입력하시오");
		t1=new JTextField(10); 
		t2=new JTextField(10);
		button=new JButton("변환");
		button.addActionListener(new MyListener());
		
		t1.setText("1"); //초기값 입력
		
		t2.setEditable(false);
		panel1.add(label);
		panel1.add(t1);
		panel1.add(new JLabel("->"));
		panel1.add(t2);
		panel1.add(button);
		this.add(panel1);

		setVisible(true);
	}
	
	private class MyListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==button)
			{
				int t=Integer.parseInt(t1.getText());
				double k=t*1.609344;
				t2.setText(k+" km");
			}
		}
	}
}

public class Mile {
	public static void main(String[] arge)
	{
		Myframe f=new Myframe();
	}
}