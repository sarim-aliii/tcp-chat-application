package ui;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

public class ChatPanel extends JPanel{
    private final JTextPane chatArea;
    private final Document document;

    public ChatPanel(){
        setLayout(new BorderLayout());

        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setFont(UIConstants.CHAT_FONT);

        document = chatArea.getDocument();

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void appendMessage(String message){
        try{
            document.insertString(
                    document.getLength(),
                    message + "\n",
                    null
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