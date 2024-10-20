import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe1 implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    TicTacToe1() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        FirstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O Turn");
                        Check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X Turn");
                        Check();
                    }
                }
            }
        }
    }

    public void FirstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player1_turn = random.nextInt(2) == 0;
        textfield.setText(player1_turn ? "X Turn" : "O Turn");
    }

    public void Check() {
        // Check win conditions
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals("X") && buttons[i * 3 + 1].getText().equals("X") && buttons[i * 3 + 2].getText().equals("X")) {
                XWin(i * 3, i * 3 + 1, i * 3 + 2);
                return;
            }
            if (buttons[i].getText().equals("X") && buttons[i + 3].getText().equals("X") && buttons[i + 6].getText().equals("X")) {
                XWin(i, i + 3, i + 6);
                return;
            }
        }
        if (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) {
            XWin(0, 4, 8);
            return;
        }
        if (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X")) {
            XWin(2, 4, 6);
            return;
        }

        // Check O win conditions
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals("O") && buttons[i * 3 + 1].getText().equals("O") && buttons[i * 3 + 2].getText().equals("O")) {
                OWin(i * 3, i * 3 + 1, i * 3 + 2);
                return;
            }
            if (buttons[i].getText().equals("O") && buttons[i + 3].getText().equals("O") && buttons[i + 6].getText().equals("O")) {
                OWin(i, i + 3, i + 6);
                return;
            }
        }
        if (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) {
            OWin(0, 4, 8);
            return;
        }
        if (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O")) {
            OWin(2, 4, 6);
            return;
        }

        // Check for a draw
        if (isDraw()) {
        	textfield.setForeground(Color.red);
            textfield.setText("Game Over!");
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        }
    }

    public boolean isDraw() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void XWin(int a, int b, int c) {
        buttons[a].setBackground(Color.black);
        buttons[b].setBackground(Color.black);
        buttons[c].setBackground(Color.black);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Wins");
    }

    public void OWin(int a, int b, int c) {
        buttons[a].setBackground(Color.black);
        buttons[b].setBackground(Color.black);
        buttons[c].setBackground(Color.black);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Wins");
    }

    public static void main(String[] args) {
        new TicTacToe1();
    }
}
