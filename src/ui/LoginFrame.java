package ui;

import client.ChatClient;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginFrame extends JFrame{
    private JLabel titleLabel;
    private JLabel usernameLabel;

    private JTextField usernameField;

    private JButton connectButton;
    
    public LoginFrame(){
        initializeComponents();
        layoutComponents();
        registerEvents();

        setTitle(UIConstants.APP_TITLE);
        setSize(UIConstants.LOGIN_WIDTH, UIConstants.LOGIN_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        titleLabel = new JLabel("Java TCP Chat");
        titleLabel.setFont(UIConstants.TITLE_FONT);

        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(UIConstants.NORMAL_FONT);

        usernameField = new JTextField(20);
        usernameField.setFont(UIConstants.NORMAL_FONT);

        connectButton = new JButton("Connect");
        connectButton.setFont(UIConstants.NORMAL_FONT);
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Add Username Label
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(usernameLabel, gbc);

        // Add Username Field
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(connectButton, gbc);

        add(panel);
    }

    private void registerEvents() {
        connectButton.addActionListener(e -> connect());
        usernameField.addActionListener(e -> connect());
    }

    private void connect(){
        String username = usernameField.getText().trim();

        if(username.isEmpty()){
            JOptionPane.showMessageDialog(
                    this,
                    "Username cannot be empty",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try{
            ChatClient client = new ChatClient("localhost", 2020, username);
            SwingUtilities.invokeLater(() -> {
                new ChatFrame(client);
                dispose();
            });
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to connect to server.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
