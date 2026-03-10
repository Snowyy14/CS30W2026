package Mastery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class BreakAPlate extends JFrame 
{
    private static final int PLAYS_PER_GAME = 3;
    private static final int COST_DEFAULT = 0;
    private static final int BORDER_PADDING = 20;

    private GameBooth booth;
    private JLabel platesLabel;
    private JLabel prizeLabel;
    private JButton playButton;
    private ImageIcon platesIcon;
    private ImageIcon twoBrokenIcon;
    private ImageIcon allBrokenIcon;
    private ImageIcon tigerIcon;
    private ImageIcon stickerIcon;
    private ImageIcon placeholderIcon;

    public BreakAPlate() 
    {
        booth = new GameBooth(COST_DEFAULT, "tiger plush", "sticker");
        
        String base = "images/";
        
        if (!(new File(base + "plates.gif").exists())) 
        {
            base = "..\\images\\";
            
            if (!(new File(base + "plates.gif").exists())) 
            {
                base = "c:\\Users\\38136012\\git\\CS30W2026\\Chapter3\\images\\";
            }
            
        }
        

        platesIcon = new ImageIcon(base + "plates.gif");
        twoBrokenIcon = new ImageIcon(base + "plates_two_broken.gif");
        allBrokenIcon = new ImageIcon(base + "plates_all_broken.gif");
        tigerIcon = new ImageIcon(base + "tiger_plush.gif");
        stickerIcon = new ImageIcon(base + "sticker.gif");
        placeholderIcon = new ImageIcon(base + "placeholder.gif");

        setTitle("BreakAPlate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        mainPanel.setBackground(Color.WHITE);

        platesLabel = new JLabel(platesIcon);
        platesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        playButton = new JButton("Play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFocusPainted(false);
        playButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        prizeLabel = new JLabel(placeholderIcon);
        prizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        prizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        mainPanel.add(platesLabel);
        mainPanel.add(Box.createVerticalStrut(BORDER_PADDING));
        mainPanel.add(playButton);
        mainPanel.add(Box.createVerticalStrut(BORDER_PADDING));
        mainPanel.add(prizeLabel);
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null); 
        
        playButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                if (playButton.getText().equals("Play")) 
                {
                    String prize = booth.start();
                    
                    if (prize.equals("tiger plush")) 
                    {
                        platesLabel.setIcon(allBrokenIcon);
                        prizeLabel.setIcon(tigerIcon);
                    } 
                    else 
                    {
                        platesLabel.setIcon(twoBrokenIcon);
                        prizeLabel.setIcon(stickerIcon);
                    }
                    
                    
                    prizeLabel.setText(prize);
                    prizeLabel.setHorizontalTextPosition(JLabel.CENTER);
                    prizeLabel.setVerticalTextPosition(JLabel.BOTTOM);
                    
                    playButton.setText("Play Again");
                } 
                else 
                {
                    platesLabel.setIcon(platesIcon);
                    prizeLabel.setIcon(placeholderIcon);
                    prizeLabel.setText("");
                    playButton.setText("Play");
                }
                
            }
        });
    }

    public static void main(String[] args) 
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception ignored) 
        {
        }
        
        
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                BreakAPlate game = new BreakAPlate();
                game.setVisible(true);
            }
        });
    }
}

class GameBooth 
{
    private static final int PLAYS_PER_GAME = 3;
    private int cost;
    private String firstPrize;
    private String consolationPrize;

    public GameBooth(int costParameter, String firstParameter, String consolationParameter) 
    {
        cost = costParameter;
        firstPrize = firstParameter;
        consolationPrize = consolationParameter;
    }

    public String start() 
    {
        int totalWins = 0;
        
        for (int index = 0; index < PLAYS_PER_GAME; index++) 
        {
            int randomNumber = (int)(Math.random() * 2);
            
            if (randomNumber == 1) 
            {
                totalWins++;
            }
            
        }
        
        if (totalWins == PLAYS_PER_GAME) 
        {
            return firstPrize;
        } 
        else 
        {
            return consolationPrize;
        }
        
    }

    public int getCost() 
    {
        return cost;
    }
}
