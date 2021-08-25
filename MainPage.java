package MiniProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JPanel {
    private GUI parent;

    private JButton button1;
    private JButton button2;
    private JButton button3;

    public MainPage(GUI parent){
        this.parent = parent;
        setSize(600, 500);
        setLayout(null);

        button1 = new JButton("ADD STUDENT");
        button1.setSize(250,30); button1.setLocation(170,100); add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMain().setVisible(false);
                parent.getFirstPage().setVisible(true);
            }
        });

        button2 = new JButton("LIST STUDENTS");
        button2.setSize(250,30); button2.setLocation(170, 150); add(button2);
        button2.addActionListener((ActionEvent e )->{
            if(parent.getStudent()==null){
                System.exit(0);
            }
            parent.getSecondPage().generateTable(parent.getStudent());
            parent.getSecondPage().setVisible(true);
            parent.getMain().setVisible(false);

        });

        button3 = new JButton("EXIT");
        button3.setSize(250,30); button3.setLocation(170,200); add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-0);
            }
        });

    }


}
