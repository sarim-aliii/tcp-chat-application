package ui;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel{
    private final JTextArea chatArea;

    public ChatPanel(){
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(UIConstants.CHAT_FONT);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void appendMessage(String message){
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public void clear(){
        chatArea.setText("");
    }
}
