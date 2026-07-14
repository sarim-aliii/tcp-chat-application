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

        add(messageField, BorderLayout.CENTER);

        add(sendButton, BorderLayout.EAST);
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
