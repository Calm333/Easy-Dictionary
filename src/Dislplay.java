import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dislplay extends JFrame {
    private JPanel top;
    private JTextField leftTextField;
    private JTextField rightTextField;
    private JButton reverse;
    private JButton translate;
    private JLabel leftLabel;
    private JLabel rirghtLable;
    private JButton exit;

    private int px;
    private int py;

    private final List<String> list;

    public Dislplay() {
        setUndecorated(true);
        setVisible(true);
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(top);

        list = new ArrayList<>();

        reverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reverse.isDefaultCapable()) {
                    reverse.setDefaultCapable(false);
                    list.add(leftTextField.getText());
                    leftLabel.setText("Английский");
                    rirghtLable.setText("Русский");
                    leftTextField.setText(rightTextField.getText());
                    for (String l : list) {
                        rightTextField.setText(l);
                    }
                } else {
                    reverse.setDefaultCapable(true);
                    list.add(rightTextField.getText());
                    leftLabel.setText("Русский");
                    rirghtLable.setText("Английский");
                    rightTextField.setText(leftTextField.getText());
                    for (String r : list) {
                        leftTextField.setText(r);
                    }
                }

            }
        });
        leftTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int word = e.getKeyCode();

                if (leftLabel.getText().equals("Русский")) {
                    if (word == KeyEvent.VK_ENTER) {
                        rightTextField.setText(Database.getHashMap().get(leftTextField.getText().toLowerCase()));
                    }
                } else if (leftLabel.getText().equals("Английский")) {
                    if (word == KeyEvent.VK_ENTER) {
                        for (Map.Entry<String, String> entry : Database.getHashMap().entrySet()) {
                            if (entry.getValue().equals(leftTextField.getText().toLowerCase())) {
                                rightTextField.setText(entry.getKey());
                            }
                        }
                    }
                }
            }
        });
        top.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                px = e.getX();
                py = e.getY();
            }
        });
        top.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - px, y - py);
            }
        });
    }
}
