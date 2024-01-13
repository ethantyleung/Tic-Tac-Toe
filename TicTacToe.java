import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TicTacToe {

    private final int BORDER_WIDTH = 600;
    private final int BORDER_HEIGHT = 670;

    JFrame mainFrame = new JFrame("Tic-Tac-Toe Game");
    JLabel titleLabel = new JLabel();
    JPanel titlePanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel replayPanel = new JPanel();

    JButton[][] buttons = new JButton[3][3];
    String player_X = "X", player_O = "O";
    String currentPlayer = setFirstPlayer();

    boolean isGameOver = false;
    short numTurns = 0;

    JButton replayButton = new JButton();

    // Constructor for the TicTacToe GUI
    TicTacToe() {
        mainFrame.setVisible(true);
        mainFrame.setSize(BORDER_WIDTH, BORDER_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        titleLabel.setBackground(Color.DARK_GRAY);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setText("Tic-Tac-Toe");
        titleLabel.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel);
        mainFrame.add(titlePanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.DARK_GRAY);
        mainFrame.add(boardPanel);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Helvetica", Font.BOLD, 120));
                button.setFocusable(false);
                buttons[i][j] = button;
                boardPanel.add(button);
                // Implement event on click
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(isGameOver) return;
                        JButton button = (JButton) e.getSource();
                        if(button.getText() == "") {
                            button.setText(currentPlayer);
                            numTurns++;
                            checkForWinner();
                            if(!isGameOver) {
                                currentPlayer = currentPlayer == player_O ? player_X : player_O;
                                titleLabel.setText(currentPlayer + "'s turn.");
                            }
                            
                        }
                    }
                });
            }
        }

        replayPanel.setLayout(new BorderLayout());
        replayPanel.setBackground(Color.DARK_GRAY);
        mainFrame.add(replayPanel, BorderLayout.SOUTH);

        replayButton.setText("Reset");
        replayButton.setBackground(Color.DARK_GRAY);
        replayButton.setForeground(Color.WHITE);
        replayButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        replayButton.setFocusable(false);

        replayPanel.add(replayButton);

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numTurns = 0;
                isGameOver = false;
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                        buttons[i][j].setBackground(Color.DARK_GRAY);
                        buttons[i][j].setForeground(Color.WHITE);
                        titleLabel.setText("Tic-Tac-Toe");
                        currentPlayer = setFirstPlayer();
                    }
                }
            }
        });



    }

    void checkForWinner() {
        // Check each column
        for(int i = 0; i < 3; i++) {
            if(buttons[0][i].getText() == "") continue;

            if(buttons[0][i].getText() == buttons[1][i].getText()
            && buttons[0][i].getText() == buttons[2][i].getText()) {
                for(int j = 0; j < 3; j++) {
                    buttons[j][i].setForeground(Color.green);
                    buttons[j][i].setBackground(Color.GRAY);
                    titleLabel.setText(currentPlayer + " Wins!");
                }
                isGameOver = true;
                return;
            }
        }

        // Check each row
        for(int i = 0; i < 3; i++) {
            if(buttons[i][0].getText() == "") continue;

            if(buttons[i][0].getText() == buttons[i][1].getText()
            && buttons[i][0].getText() == buttons[i][2].getText()) {
                for(int j = 0; j < 3; j++) {
                    buttons[i][j].setForeground(Color.green);
                    buttons[i][j].setBackground(Color.GRAY);
                    titleLabel.setText(currentPlayer + " Wins!");
                }
                isGameOver = true;
                return;
            }
        }

        // Checks left to right
        if(buttons[0][0].getText() != ""){
            if(buttons[0][0].getText() == buttons[1][1].getText()
            && buttons[0][0].getText() == buttons[2][2].getText()) {
                for(int j = 0; j < 3; j++) {
                    buttons[j][j].setForeground(Color.green);
                    buttons[j][j].setBackground(Color.GRAY);
                    titleLabel.setText(currentPlayer + " Wins!");
                }
                isGameOver = true;
                return;
            }
        }
        
        // Check right to left diagonal
        if(buttons[0][2].getText() != ""){
            if(buttons[0][2].getText() == buttons[1][1].getText()
            && buttons[0][2].getText() == buttons[2][0].getText()) {
                for(int j = 0; j < 3; j++) {
                    buttons[j][j].setForeground(Color.green);
                    buttons[j][j].setBackground(Color.GRAY);
                    titleLabel.setText(currentPlayer + " Wins!");
                }
                isGameOver = true;
                return;
            }
        }

        if(numTurns == 9) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    buttons[i][j].setForeground(Color.LIGHT_GRAY);
                    buttons[i][j].setBackground(Color.GRAY);
                }
            }
            titleLabel.setText("TIE.");
            isGameOver = true;
            return;
        }

        return;
    }

    String setFirstPlayer() {
        Random rand = new Random();
        final int NUM = rand.nextInt(1);
        if(NUM == 0) {
            return player_X;
        } else {
            return player_O;
        }
    }
}