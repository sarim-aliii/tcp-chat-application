package client;

import common.MessageType;
import common.ChatMessage;

public class CommandParser {
    private CommandParser(){}

    public static ChatMessage parse(String input, String username){
        input = input.trim();

        // Exit
        if(input.equalsIgnoreCase("exit")){
            return new ChatMessage(
                    MessageType.LOGOUT,
                    username,
                    null,
                    username + " left the chat."
            );
        }

        // Private Message
        if(input.equalsIgnoreCase("/msg")){
            String[] parts = input.split("\\s+", 3);
            if(parts.length < 3){
                return new ChatMessage(
                        MessageType.SYSTEM,
                        "Server",
                        username,
                        "Usage: /msg <username> <message>"
                );
            }

            return new ChatMessage(
                    MessageType.PRIVATE,
                    username,
                    parts[1],
                    parts[2]
            );
        }

        return new ChatMessage(
                MessageType.MESSAGE,
                username,
                null,
                input
        );
    }
}
