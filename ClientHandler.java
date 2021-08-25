package MiniProject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    private Socket socket;
    private int id;

    public ClientHandler(Socket socket, int id){
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            DBManager db = new DBManager();
            db.connect();

            PackageData data = null;
            while ((data = (PackageData) inputStream.readObject()) != null) {
                if(data.getOperationType().equalsIgnoreCase("ADD_STUDENT")){
                    Students student = data.getStudent();
                    db.addTheStudent(student);
                }
                if(data.getOperationType().equalsIgnoreCase("LIST_STUDENTS")){
                    ArrayList<Students> arrayList = db.getAllStudents();
                    PackageData packageData = new PackageData();
                    packageData.setStudents(arrayList);
                    outputStream.writeObject(packageData);
                }
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
}

