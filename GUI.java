package MiniProject;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class GUI extends JFrame{
    private MiniProject.MainPage main;
    private MiniProject.addStudent firstPage;
    private listStudents secondPage;
    private Students[] students;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public GUI(Socket socket, ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        try {
            this.socket = socket;
            this.outputStream = outputStream;
            this.inputStream = inputStream;

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Student Application");
            setSize(600, 500);
            setLayout(null);

            main = new MiniProject.MainPage(this);
            main.setVisible(true);
            add(main);

            firstPage = new MiniProject.addStudent(this);
            firstPage.setVisible(false);
            add(firstPage);

            secondPage = new listStudents(this);
            secondPage.setVisible(false);
            add(secondPage);

        }catch (Exception e){ e.printStackTrace(); }
    }

    public MiniProject.MainPage getMain() {
        return main;
    }

    public void setMain(MainPage main) {
        this.main = main;
    }

    public MiniProject.addStudent getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(addStudent firstPage) {
        this.firstPage = firstPage;
    }

    public listStudents getSecondPage() {
        return secondPage;
    }

    public void setSecondPage(listStudents secondPage) {
        this.secondPage = secondPage;
    }



    public void setStudents(Students[] students) {
        this.students = students;
    }

    public void addStudent(Students student) {
        try {
            PackageData packageData = new PackageData("ADD_STUDENT", null, student);
            outputStream.writeObject(packageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Students> getStudent(){
        ArrayList<Students> result = new ArrayList<>();
        try{
            PackageData packageData1 = new PackageData("LIST_STUDENTS", null, null);
            outputStream.writeObject(packageData1);
            PackageData packageData = (PackageData) inputStream.readObject();
            result = packageData.getStudents();
        }catch (Exception e) { e.printStackTrace(); }
    return result;
    }
}