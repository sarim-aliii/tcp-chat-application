package common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MessageType type;
    private final String sender;
    private final String receiver;
    private final String message;
    private final List<String> users;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ChatMessage(MessageType type, String sender, String receiver, String message) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.users = null;
    }
    public ChatMessage(MessageType type, List<String> users) {
        this.type = type;
        this.users = users;
        this.sender = null;
        this.receiver = null;
        this.message = null;
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
                + (sender != null ? sender : "Server")
                + (receiver != null ? " -> " + receiver : "")
                + (message != null ? " : " + message : "");
    }
}
