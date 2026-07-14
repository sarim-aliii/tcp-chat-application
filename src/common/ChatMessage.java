package common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private MessageType type;
    private String sender;
    private String receiver;
    private String message;
    private List<String> users;
    private LocalDateTime timestamp;

    public ChatMessage(MessageType type, String sender, String receiver, String message) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    public ChatMessage(MessageType type, List<String> users) {
        this.type = type;
        this.users = users;
    }

    public MessageType getType() {
        return type;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getUsers() {
        return users;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + type + "] "
                + sender
                + (receiver != null ? " -> " + receiver : "")
                + " : "
                + message;
    }
}
