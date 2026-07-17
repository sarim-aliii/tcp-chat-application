package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InputPanel extends JPanel{
    private final JTextField messageField;
    private final JButton sendButton;

    public InputPanel(){
        setLayout(new BorderLayout(10, 0));

        messageField = new JTextField();

        messageField.setFont(UIConstants.NORMAL_FONT);

        sendButton = new JButton("Send");
        sendButton.setFont(UIConstants.NORMAL_FONT);
        sendButton.setBackground(UIConstants.PRIMARY);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        add(messageField, BorderLayout.CENTER);

        add(sendButton, BorderLayout.EAST);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public JTextField getMessageField(){
        return messageField;
    }

    public JButton getSendButton(){
        return sendButton;
    }

    public String getMessage(){
        return messageField.getText().trim();
    }

    public void clear(){
        messageField.setText("");
    }
}
