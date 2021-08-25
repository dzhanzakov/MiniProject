package MiniProject;

import serialization.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addStudent extends JPanel {
    private final GUI parent;

    private final JLabel nameL;
    private final JLabel surnameL;
    private final JLabel ageL;

    private final JTextField nameT;
    private final JTextField surnameT;
    private final JTextField ageT;

    private final JButton addB;
    private final JButton back;

    public addStudent(GUI parent){
        this.parent = parent;

        setSize(600, 500);
        setLayout(null);

        nameL = new JLabel("name:"); nameL.setSize(300,30); nameL.setLocation(100, 100);
        surnameL = new JLabel("surname:"); surnameL.setSize(300, 30); surnameL.setLocation(100, 150);
        ageL = new JLabel("age:"); ageL.setSize(300, 30); ageL.setLocation(100, 200);
        add(nameL); add(surnameL); add(ageL);

        nameT  = new JTextField(); nameT.setSize(300, 30); nameT.setLocation(170, 100);
        surnameT = new JTextField(); surnameT.setSize(300,30); surnameT.setLocation(170, 150);
        ageT = new JTextField(); ageT.setSize(300, 30); ageT.setLocation(170, 200);
        add(nameT); add(surnameT); add(ageT);

        addB = new JButton("ADD"); addB.setSize(140,30); addB.setLocation(150,400);
        addB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameT.getText();
                String surname = surnameT.getText();
                String age = ageT.getText();
                try{
                    int ageInt = Integer.parseInt(age);
                    Students student = new Students(null, name, surname, ageInt);
                    parent.addStudent(student);
                }catch (Exception ex) { ex.printStackTrace(); }
                nameT.setText("");
                surnameT.setText("");
                ageT.setText("");

            }
        });
        back = new JButton("BACK"); back.setSize(140,30); back.setLocation(300, 400);
        back.addActionListener((ActionEvent e )->{
            parent.getFirstPage().setVisible(false);
            parent.getSecondPage().setVisible(false);
            parent.getMain().setVisible(true);
        });

        add(back);add(addB);
    }
}
