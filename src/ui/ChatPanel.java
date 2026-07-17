package ui;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class ChatPanel extends JPanel{
    private final JTextPane chatArea;
    private final Document document;

    public ChatPanel(){
        setLayout(new BorderLayout());

        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setFont(UIConstants.CHAT_FONT);
        chatArea.setBackground(UIConstants.CHAT_BACKGROUND);

        document = chatArea.getDocument();

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void appendMessage(String message){
        appendMessage(message, UIConstants.NORMAL_COLOR);
    }

    public void appendMessage(String message, Color color){
        try{
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setForeground(attributes, color);

            document.insertString(
                    document.getLength(),
                    message + "\n",
                    attributes
            );
            chatArea.setCaretPosition(document.getLength());
        }
        catch (BadLocationException e){
            e.printStackTrace();
        }
    }

    public void appendRichMessage(String timestamp, String username, String messageText, Color usernameColor) {
        try {
            // 1. Timestamp styling (Gray and un-bolded)
            SimpleAttributeSet timeStyle = new SimpleAttributeSet();
            StyleConstants.setForeground(timeStyle, UIConstants.SYSTEM_COLOR);

            // 2. Username styling (Bold and Colorful)
            SimpleAttributeSet nameStyle = new SimpleAttributeSet();
            StyleConstants.setForeground(nameStyle, usernameColor);
            StyleConstants.setBold(nameStyle, true);

            // 3. Message text styling (Standard color)
            SimpleAttributeSet textStyle = new SimpleAttributeSet();
            StyleConstants.setForeground(textStyle, UIConstants.FOREGROUND);

            // Insert each piece one by one into the document
            document.insertString(document.getLength(), "[" + timestamp + "] ", timeStyle);
            document.insertString(document.getLength(), username + ": ", nameStyle);
            document.insertString(document.getLength(), messageText + "\n", textStyle);

            chatArea.setCaretPosition(document.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        chatArea.setText("");
    }
}