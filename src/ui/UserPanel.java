package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserPanel extends JPanel{
    private final DefaultListModel<String> model;
    private final JList<String> userList;

    public UserPanel(){
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(180, 0));

        JLabel title = new JLabel("Online Users");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        model = new DefaultListModel<>();
        userList = new JList<>(model);

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(userList), BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void addUser(String username){
        if(!model.contains(username)) model.addElement(username);
    }

    public void removeUser(String username){
        model.removeElement(username);
    }

    public void setUsers(List<String> users){
        model.clear();
        for(String user : users){
            model.addElement(user);
        }
    }
}
