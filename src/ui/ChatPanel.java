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

    public void clear(){
        chatArea.setText("");
    }
}