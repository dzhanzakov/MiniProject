package MiniProject;

import JDBS_dataBases1.Items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private Connection connection;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentsDatabase?useUnicode=true&serverTimezone=UTC", "root", ""
            );
            System.out.println(connection.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Students> getAllStudents() {
        ArrayList<Students> students = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                students.add(new Students(id, name, surname, age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public void addTheStudent(Students student) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO students(id,name,surname,age) VALUES(NULL,?,?,?)");

            st.setString(1, student.getName());
            st.setString(2, student.getSurname());
            st.setInt(3, student.getAge());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void updateAnItem(Items i) {
//        try {
//            PreparedStatement st = connection.prepareStatement("UPDATE items set name = ?, price = ? where id = ?");
//            st.setString(1, i.getName());
//            st.setDouble(2, i.getPrice());
//            st.setLong(3, i.getId());
//            st.executeUpdate();
//            st.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void deleteItem(Long id) {
//        try {
//            PreparedStatement st = connection.prepareStatement("DELETE FROM items where id = ?");
//            st.setLong(1, id);
//            st.executeUpdate();
//            st.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

