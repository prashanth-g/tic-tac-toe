package com.prashanth;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

public class TicTacToe {

    final int boardWidth = 600;
    final int boardHeight = 650;

    final JFrame frame = new JFrame("Tic-Tac-Toe");
    final JLabel textLabel = new JLabel();
    final JPanel textPanel = new JPanel();
    final JPanel boardPanel = new JPanel();

    final JButton[][] board = new JButton[3][3];
    final String playerX = "X";
    final String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int moves = 0;

    public TicTacToe() {

        // Set up the frame
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set up the text panel
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        // Add the text panel to the frame
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Set up the board panel
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        // Set up the buttons
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.darkGray);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       if (gameOver) return;
                       JButton tile = (JButton) e.getSource();
                       if (tile.getText().isEmpty()) {
                           moves++;
                           tile.setText(currentPlayer);
                           checkWinner();
                           if (!gameOver) {
                               currentPlayer = Objects.equals(currentPlayer, playerX) ? playerO : playerX;
                               textLabel.setText(currentPlayer + "'s turn");
                           }
                       }
                    }
                });
            }
        }
    }

    void checkWinner() {
        // Check rows
        for (int r = 0; r < 3; r++) {
            if (Objects.equals(board[r][0].getText(), "")) continue;;
            if (Objects.equals(board[r][0].getText(), board[r][1].getText()) &&
                    Objects.equals(board[r][1].getText(), board[r][2].getText())) {

                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                
                gameOver = true;
                return;
            }
        }

        // Check columns
        for (int c = 0; c < 3; c++) {
            if (Objects.equals(board[0][c].getText(), "")) continue;
            if (Objects.equals(board[0][c].getText(), board[1][c].getText()) &&
                    Objects.equals(board[1][c].getText(), board[2][c].getText())) {

                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }

                gameOver = true;
                return;
            }
        }

        // Check diagonals
        if (!Objects.equals(board[0][0].getText(), "") &&
                Objects.equals(board[0][0].getText(), board[1][1].getText()) &&
                Objects.equals(board[1][1].getText(), board[2][2].getText())) {

            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }

            gameOver = true;
            return;
        }

        // Anti-diagonal
        if (!Objects.equals(board[0][2].getText(), "") &&
                Objects.equals(board[0][2].getText(), board[1][1].getText()) &&
                Objects.equals(board[1][1].getText(), board[2][0].getText())) {

            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);

            gameOver = true;
            return;
        }

        // Check for a tie
        if (moves == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }

    }

    private void setWinner(JButton tile) {
        tile.setBackground(Color.gray);
        tile.setForeground(Color.green);
        textLabel.setText(currentPlayer + " wins!");
    }

    private void setTie(JButton jButton) {
        jButton.setBackground(Color.gray);
        jButton.setForeground(Color.orange);
        textLabel.setText("It's a tie!");
    }
}
