package MiniProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class listStudents extends JPanel {
    private final GUI parent;
    private ArrayList<Students> students;
    private String header[] = {"ID", "Name","Surname", "Age"};
    private JTable table;
    private JScrollPane scrollPane;
    private JButton buttonBack;

    public listStudents(GUI parent){
        this.parent = parent;

        setSize(600, 500);
        setLayout(null);

        students = parent.getStudent();

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 13));
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(400,250);
        scrollPane.setLocation(100,100);
        add(scrollPane);

        buttonBack = new JButton("close"); buttonBack.setSize( 200, 30); buttonBack.setLocation(200, 400);
        buttonBack.addActionListener((ActionEvent e)-> {
            parent.getMain().setVisible(true);
            parent.getSecondPage().setVisible(false);
        });
        add(buttonBack);
    }

    public void generateTable(ArrayList<Students> students){
        Object data[][] = new Object[students.size()][4];

        for(int i = 0; i < students.size(); i++) {
            if (students.get(i) != null) {
                data[i][0] = students.get(i).getId();
                data[i][1] = students.get(i).getName();
                data[i][2] = students.get(i).getSurname();
                data[i][3] = students.get(i).getAge();
            }
        }
        DefaultTableModel model = new DefaultTableModel(data, header);
        table.setModel(model);
    }

}
