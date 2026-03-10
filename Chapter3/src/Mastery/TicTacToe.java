package Mastery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame 
{
    private static final int GRID_SIZE = 3;
    private static final int WINDOW_WIDTH = 420;
    private static final int WINDOW_HEIGHT = 520;
    private static final int BORDER_WIDTH = 2;

    private static final Color BG          = new Color(30, 30, 46);
    private static final Color GRID_BG     = new Color(45, 45, 65);
    private static final Color BTN_DEFAULT = new Color(55, 55, 80);
    private static final Color BTN_HOVER   = new Color(70, 70, 100);
    private static final Color X_COLOR     = new Color(243, 139, 168);
    private static final Color O_COLOR     = new Color(137, 180, 250);
    private static final Color TEXT_COLOR  = new Color(205, 214, 244);
    private static final Color WIN_COLOR   = new Color(166, 227, 161);
    private static final Color HIGHLIGHT_COLOR = new Color(40, 80, 50);

    private static final Font MARK_FONT   = new Font("Segoe UI", Font.BOLD, 60);
    private static final Font STATUS_FONT = new Font("Segoe UI", Font.PLAIN, 20);

    private final JButton[][] buttons = new JButton[GRID_SIZE][GRID_SIZE];
    private final JLabel statusLabel;
    private char currentPlayer = 'X';
    private boolean gameOver = false;

    public TicTacToe() 
    {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(BG);
        setLayout(new BorderLayout(0, 10));

        JLabel titleLabel = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(BG);
        add(titleLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 6, 6));
        gridPanel.setBackground(GRID_BG);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        for (int row = 0; row < GRID_SIZE; row++) 
        {
            
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                JButton btn = createTileButton(row, col);
                buttons[row][col] = btn;
                gridPanel.add(btn);
            }
            
        }
        
        add(gridPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Player X starts!", SwingConstants.CENTER);
        statusLabel.setFont(STATUS_FONT);
        statusLabel.setForeground(X_COLOR);
        statusLabel.setOpaque(true);
        statusLabel.setBackground(BG);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 15, 0));
        add(statusLabel, BorderLayout.SOUTH);

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
    }

    private JButton createTileButton(int row, int col) 
    {
        JButton btn = new JButton("");
        btn.setFont(MARK_FONT);
        btn.setFocusPainted(false);
        btn.setBackground(BTN_DEFAULT);
        btn.setForeground(TEXT_COLOR);
        btn.setBorder(BorderFactory.createLineBorder(GRID_BG, BORDER_WIDTH));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() 
        {
            public void mouseEntered(MouseEvent e) 
            {
                if (btn.getText().isEmpty() && !gameOver) 
                {
                    btn.setBackground(BTN_HOVER);
                }
                
            }
            
            public void mouseExited(MouseEvent e) 
            {
                if (btn.getText().isEmpty()) 
                {
                    btn.setBackground(BTN_DEFAULT);
                }
                
            }
        });

        btn.addActionListener(e -> handleClick(row, col));
        return btn;
    }

    private void handleClick(int row, int col) 
    {
        if (gameOver || !buttons[row][col].getText().isEmpty()) 
        {
            return;
        }
        

        buttons[row][col].setText(String.valueOf(currentPlayer));
        
        if (currentPlayer == 'X')
        {
            buttons[row][col].setForeground(X_COLOR);
        }
        else
        {
            buttons[row][col].setForeground(O_COLOR);
        }
        
        buttons[row][col].setBackground(BTN_DEFAULT);

        if (checkWin(currentPlayer)) 
        {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            statusLabel.setForeground(WIN_COLOR);
            highlightWinningCells();
            gameOver = true;
            showPlayAgainDialog("Player " + currentPlayer + " wins!");
            return;
        }
        

        if (isBoardFull()) 
        {
            statusLabel.setText("It's a draw!");
            statusLabel.setForeground(TEXT_COLOR);
            gameOver = true;
            showPlayAgainDialog("It's a draw!");
            return;
        }
        

        if (currentPlayer == 'X')
        {
            currentPlayer = 'O';
        }
        else
        {
            currentPlayer = 'X';
        }
        
        statusLabel.setText("Player " + currentPlayer + "'s turn");
        
        if (currentPlayer == 'X')
        {
            statusLabel.setForeground(X_COLOR);
        }
        else
        {
            statusLabel.setForeground(O_COLOR);
        }
        
    }

    private boolean checkWin(char player) 
    {
        String playerString = String.valueOf(player);
        
        for (int index = 0; index < GRID_SIZE; index++) 
        {
            if (buttons[index][0].getText().equals(playerString) &&
                buttons[index][1].getText().equals(playerString) &&
                buttons[index][2].getText().equals(playerString)) 
            {
                return true;
            }
            
            
            if (buttons[0][index].getText().equals(playerString) &&
                buttons[1][index].getText().equals(playerString) &&
                buttons[2][index].getText().equals(playerString)) 
            {
                return true;
            }
            
        }
        
        if (buttons[0][0].getText().equals(playerString) &&
            buttons[1][1].getText().equals(playerString) &&
            buttons[2][2].getText().equals(playerString)) 
        {
            return true;
        }
        
        if (buttons[0][2].getText().equals(playerString) &&
            buttons[1][1].getText().equals(playerString) &&
            buttons[2][0].getText().equals(playerString)) 
        {
            return true;
        }
        
        return false;
    }

    private void highlightWinningCells() 
    {
        String playerString = String.valueOf(currentPlayer);
        
        for (int index = 0; index < GRID_SIZE; index++) 
        {
            if (buttons[index][0].getText().equals(playerString) &&
                buttons[index][1].getText().equals(playerString) &&
                buttons[index][2].getText().equals(playerString)) 
            {
                
                for (int col = 0; col < GRID_SIZE; col++) 
                {
                    buttons[index][col].setBackground(HIGHLIGHT_COLOR);
                }
                
            }
            
        }
        
        for (int index = 0; index < GRID_SIZE; index++) 
        {
            if (buttons[0][index].getText().equals(playerString) &&
                buttons[1][index].getText().equals(playerString) &&
                buttons[2][index].getText().equals(playerString)) 
            {
                
                for (int row = 0; row < GRID_SIZE; row++) 
                {
                    buttons[row][index].setBackground(HIGHLIGHT_COLOR);
                }
                
            }
            
        }
        
        if (buttons[0][0].getText().equals(playerString) &&
            buttons[1][1].getText().equals(playerString) &&
            buttons[2][2].getText().equals(playerString)) 
        {
            buttons[0][0].setBackground(HIGHLIGHT_COLOR);
            buttons[1][1].setBackground(HIGHLIGHT_COLOR);
            buttons[2][2].setBackground(HIGHLIGHT_COLOR);
        }
        
        if (buttons[0][2].getText().equals(playerString) &&
            buttons[1][1].getText().equals(playerString) &&
            buttons[2][0].getText().equals(playerString)) 
        {
            buttons[0][2].setBackground(HIGHLIGHT_COLOR);
            buttons[1][1].setBackground(HIGHLIGHT_COLOR);
            buttons[2][0].setBackground(HIGHLIGHT_COLOR);
        }
        
    }

    private boolean isBoardFull() 
    {
        for (int row = 0; row < GRID_SIZE; row++)
        {
            
            for (int col = 0; col < GRID_SIZE; col++)
            {
                
                if (buttons[row][col].getText().isEmpty()) 
                {
                    return false;
                }
                
            }
            
        }
        
        return true;
    }

    private void showPlayAgainDialog(String message) 
    {
        int result = JOptionPane.showConfirmDialog(
                this, message + "\nPlay again?", "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
        if (result == JOptionPane.YES_OPTION) 
        {
            resetBoard();
        }
        
    }

    private void resetBoard() 
    {
        for (int row = 0; row < GRID_SIZE; row++) 
        {
            
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                buttons[row][col].setText("");
                buttons[row][col].setBackground(BTN_DEFAULT);
                buttons[row][col].setForeground(TEXT_COLOR);
            }
            
        }
        
        currentPlayer = 'X';
        gameOver = false;
        statusLabel.setText("Player X starts!");
        statusLabel.setForeground(X_COLOR);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            try 
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } 
            catch (Exception ignored) 
            {
            }
            
            new TicTacToe().setVisible(true);
        });
    }
}
