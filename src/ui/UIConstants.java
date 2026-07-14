package ui;

import java.awt.*;

public final class UIConstants {
    private UIConstants() {}

    // Application
    public static final String APP_TITLE = "Java TCP Chat";

    // Window
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 600;

    // Login Window
    public static final int LOGIN_WIDTH = 400;
    public static final int LOGIN_HEIGHT = 220;

    // Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font CHAT_FONT = new Font("Consolas", Font.BOLD, 15);

    // Colors
    public static final Color PRIMARY = new Color(33, 150, 243); // A nice Material Design Blue

    // Backgrounds
    public static final Color BACKGROUND = Color.WHITE;
    public static final Color CHAT_BACKGROUND = Color.WHITE;
    public static final Color INPUT_BACKGROUND = Color.WHITE;
    public static final Color USER_PANEL = new Color(245, 245, 245); // Light Gray for contrast

    // Text Colors (Foregrounds)
    public static final Color FOREGROUND = Color.BLACK; // Default text color
    public static final Color NORMAL_COLOR = Color.BLACK; // Standard user messages

    // Semantic Text Colors
    public static final Color SYSTEM_COLOR = new Color(158, 158, 158); // Gray for server messages
    public static final Color PRIVATE_COLOR = new Color(233, 30, 99); // Pink/Red for private messages
}
