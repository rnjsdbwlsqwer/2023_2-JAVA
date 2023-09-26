import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Repair extends JPanel implements ActionListener {
    private List<JCheckBox> checkboxes = new ArrayList<>();
    private String[] items = {"엔진오일 교환", "자동변속기오일교환", "에어컨필터교환", "타이어 교환"};
    private int[] prices = {45000, 80000, 30000, 100000};
    private int totalCost = 0;
    private JLabel label;

    public Repair() {
        super();
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(items.length, 1));

        for (int i = 0; i < items.length; i++) {
            JCheckBox checkbox = new JCheckBox(items[i]);
            checkbox.setActionCommand(Integer.toString(i)); // 항목 인덱스를 액션 커맨드로 설정
            checkbox.addActionListener(this); // 체크박스에 액션 리스너 추가
            checkboxes.add(checkbox);
            menuPanel.add(checkbox);
        }

        add(menuPanel, BorderLayout.WEST);

        label = new JLabel("전체 가격: " + totalCost + "원", SwingConstants.CENTER);
        add(label, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox source = (JCheckBox) e.getSource();
        int index = Integer.parseInt(source.getActionCommand());
        int price = prices[index];

        if (source.isSelected()) {
            totalCost += price;
        } else {
            totalCost -= price;
        }

        label.setText("현재까지의 가격은 " + totalCost + "입니다.");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("수리 서비스 메뉴");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new Repair();
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }
}
