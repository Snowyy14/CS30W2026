package Mastery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.text.NumberFormat;

public class BankAccount extends JFrame 
{
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 520;
    private static final int INITIAL_BALANCE = 1000000;
    private static final int GRID_ROWS = 5;
    private static final int GRID_COLS = 2;
    private static final int GRID_HGAP = 5;
    private static final int GRID_VGAP = 10;
    private static final int BORDER_PADDING = 10;
    private static final int TRANS_DEPOSIT = 1;
    private static final int TRANS_WITHDRAW = 2;

    private JComboBox<String> actionCombo;
    private JTextField accNumField;
    private JTextField amountField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField beginBalanceField;
    private JTextArea accountInfoArea;
    private JButton processButton;

    private Bank bank;
    private String hintAcctID;

    public BankAccount() 
    {
        bank = new Bank();
        hintAcctID = bank.addAccount("ethan", "presinal", INITIAL_BALANCE);

        setTitle("Bank Account Transaction Provider");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        JLabel hintLabel = new JLabel("Hint: Account number is " + hintAcctID + " (ethan presinal)");
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(hintLabel);
        mainPanel.add(Box.createVerticalStrut(BORDER_PADDING));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actionPanel.add(new JLabel("Action:"));
        
        String[] actions = new String[]{"Deposit", "Withdraw"};
        actionCombo = new JComboBox<>(actions);
        actionPanel.add(actionCombo);
        mainPanel.add(actionPanel);

        JLabel redLabel = new JLabel("Complete info");
        redLabel.setForeground(Color.RED);
        redLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(redLabel);
        mainPanel.add(Box.createVerticalStrut(15));

        JPanel inputPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS, GRID_HGAP, GRID_VGAP));
        
        inputPanel.add(new JLabel("*Account Number:"));
        accNumField = new JTextField();
        inputPanel.add(accNumField);

        inputPanel.add(new JLabel("*Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("Beginning Balance:"));
        beginBalanceField = new JTextField();
        beginBalanceField.setEditable(false);
        inputPanel.add(beginBalanceField);

        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        JLabel infoLabel = new JLabel("Account info displayed here");
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(infoLabel);
        mainPanel.add(Box.createVerticalStrut(GRID_HGAP));

        accountInfoArea = new JTextArea(4, 20);
        accountInfoArea.setEditable(false);
        accountInfoArea.setLineWrap(true);
        accountInfoArea.setWrapStyleWord(true);
        accountInfoArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(accountInfoArea);
        mainPanel.add(Box.createVerticalStrut(15));

        processButton = new JButton("Process Transaction");
        processButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(processButton);

        add(mainPanel, BorderLayout.CENTER);

        ActionListener updateInfoAction = e -> updateAccountInfo();
        accNumField.addActionListener(updateInfoAction);
        
        accNumField.addFocusListener(new FocusAdapter() 
        {
            @Override
            public void focusLost(FocusEvent e) 
            {
                updateAccountInfo();
            }
        });

        processButton.addActionListener(e -> processTransaction());
    }

    private void updateAccountInfo() 
    {
        String accountNumber = accNumField.getText().trim();
        
        if (accountNumber.isEmpty()) 
        {
            displayInfo("");
            clearOptionalFields();
            return;
        }

        String result = bank.checkBalance(accountNumber);
        displayInfo(result);

        if (!result.equals("Account does not exist.")) 
        {
            try 
            {
                int balanceIndex = result.indexOf("Current balance is");
                
                if (balanceIndex != -1) 
                {
                    String namePart = result.substring(0, balanceIndex).trim();
                    String balancePart = result.substring(balanceIndex + 18).trim();
                    String[] nameParts = namePart.split("\\s+");
                    
                    if (nameParts.length >= 3) 
                    {
                        firstNameField.setText(nameParts[1]);
                        lastNameField.setText(nameParts[2]);
                    }
                    
                    beginBalanceField.setText(balancePart);
                }
                
            } 
            catch (Exception ex) 
            {
                // Ignored parsing exception for visual text fields
            }
            
        } 
        else 
        {
            clearOptionalFields();
        }
        
    }

    private void clearOptionalFields() 
    {
        firstNameField.setText("");
        lastNameField.setText("");
        beginBalanceField.setText("");
    }

    private void processTransaction() 
    {
        String accountNumber = accNumField.getText().trim();
        
        if (accountNumber.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please enter an account number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        String checkStr = bank.checkBalance(accountNumber);
        
        if (checkStr.equals("Account does not exist.")) 
        {
            JOptionPane.showMessageDialog(this, "The provided account number does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String amountStr = amountField.getText().trim();
        
        if (amountStr.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please enter an amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount;
        
        try 
        {
            amount = Double.parseDouble(amountStr);
        } 
        catch (NumberFormatException ex) 
        {
            JOptionPane.showMessageDialog(this, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (amount <= 0) 
        {
            JOptionPane.showMessageDialog(this, "Amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String action = (String) actionCombo.getSelectedItem();
        int transCode = TRANS_WITHDRAW;
        
        if ("Deposit".equals(action))
        {
            transCode = TRANS_DEPOSIT;
        }
        
        String transResult = bank.transaction(transCode, accountNumber, amount);
        
        displayInfo(action + " Processed:\n" + transResult);
        updateAccountInfo();
        displayInfo(action + " Processed:\n" + bank.checkBalance(accountNumber)); 
        
        amountField.setText("");
    }

    private void displayInfo(String text) 
    {
        accountInfoArea.setText(text);
    }

    public static void main(String[] args) 
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) 
        {
        }
        
        SwingUtilities.invokeLater(() -> 
        {
            BankAccount accountWindow = new BankAccount();
            accountWindow.setVisible(true);
        });
    }
}

class Bank 
{
    private ArrayList<Account> accounts;
    
    public Bank() 
    {
        accounts = new ArrayList<Account>();
    }
    
    public String addAccount(String firstName, String lastName, double balance) 
    {
        Account newAccount = new Account(balance, firstName, lastName);
        accounts.add(newAccount);
        return newAccount.getID();
    }

    public String deleteAccount(String accountID) 
    {
        int accountIndex;
        Account accountToMatch;
        
        accountToMatch = new Account(accountID);
        accountIndex = accounts.indexOf(accountToMatch);
        
        if (accountIndex > -1) 
        {
            accounts.remove(accountIndex);
            return "Account removed.";
        } 
        else 
        {
            return "Account does not exist.";
        }
        
    }
    
    public String transaction(int transactionCode, String accountID, double amount) 
    {
        int accountIndex;
        Account accountToMatch;
        Account account;
        
        accountToMatch = new Account(accountID);
        accountIndex = accounts.indexOf(accountToMatch);
        
        if (accountIndex > -1) 
        {
            account = accounts.get(accountIndex);
            
            if (transactionCode == 1) 
            {
                account.deposit(amount);
                accounts.set(accountIndex, account);
                return account.toString();
            } 
            else 
            {
                if (transactionCode == 2)
                {
                    account.withdrawal(amount);
                    accounts.set(accountIndex, account);
                    return account.toString();
                }
                
            }
            
        }
        
        return "Account does not exist.";
    }

    public String checkBalance(String accountID) 
    {
        int accountIndex;
        Account accountToMatch;
        Account account;
        
        accountToMatch = new Account(accountID);
        accountIndex = accounts.indexOf(accountToMatch);
        
        if (accountIndex > -1) 
        {
            account = accounts.get(accountIndex);
            return account.toString();
        } 
        else 
        {
            return "Account does not exist.";
        }
        
    }
}

class Account 
{
    private double balance;
    private Customer customer;
    private String accountID;
        
    public Account(double startingBalance, String firstName, String lastName) 
    {
        balance = startingBalance;
        customer = new Customer(firstName, lastName);
        accountID = firstName.substring(0, 1) + lastName;
    }
    
    public Account(String idParameter) 
    {
        balance = 0;
        customer = new Customer("", "");
        accountID = idParameter;
    }

    public String getID() 
    {
        return accountID;
    }

    public double getBalance() 
    {
        return balance;
    }

    public void deposit(double amount) 
    {
        balance += amount;
    }

    public void withdrawal(double amount) 
    {
        if (amount <= balance) 
        {
            balance -= amount;
        } 
        else 
        {
            System.out.println("Not enough money in account.");
        }
        
    }
    
    public boolean equals(Object accountObject) 
    {
        if (accountObject instanceof Account) 
        {
            Account testAccount = (Account)accountObject;
            return accountID.equals(testAccount.accountID);
        }
        
        return false;
    }

    public String toString() 
    {
        String accountString;
        NumberFormat money = NumberFormat.getCurrencyInstance();

        accountString = accountID + " ";
        accountString += customer.toString();
        accountString += "Current balance is " + money.format(balance);
        
        return accountString;
    }
}

class Customer 
{
    private String firstName;
    private String lastName;
        
    public Customer(String firstParameter, String lastParameter) 
    {
        firstName = firstParameter;
        lastName = lastParameter;
    }
    
    public String toString() 
    {
        String customerString;
        customerString = " " + firstName + " " + lastName + " ";
        
        return customerString;
    }
}
