import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Card extends JFrame {

    private JPanel panel;

    private JLabel label;



    public Card() {

        setSize(1000, 700);



        panel = new JPanel();

        label = new JLabel("");

        ImageIcon icon = new ImageIcon("C:\\maru.jpg");

        label.setIcon(icon);



       

        label.setBounds(0, 50, 100, 100);



        panel.add(label);

        add(panel);
        
        JLabel label1 = new JLabel("권유진");
        JLabel label2 = new JLabel("프로젝트 매니저");
        JLabel label3 = new JLabel("덕성주식회사");
        
        
        label1.setBounds(50,50,50,50);
        label2.setBounds(50,70,50,50);
        label3.setBounds(50,90,50,50);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        
        


        setVisible(true);

    }



    public static void main(String[] args) {

        Card F = new Card();

    }

}