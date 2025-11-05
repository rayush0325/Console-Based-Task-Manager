import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {

    private static int taskId;
    private static  final String username = "root";
    private static  final String password = "123456";
    private static  final String url = "jdbc:mysql://127.0.0.1:3306/taskmanagerdatabase";
    private static  Connection connection ;

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    Task(String description){
        this.description = description;
    }
    int id;
    String description;
    boolean status;

    public static void addTask(String description){
        String insertQuery = String.format("insert into task (description) values (\"%s\");", description);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
            System.out.println("==Task Added Successfully==");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void viewAllTask(){
        String viewQuery = "select * from task";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(viewQuery);
            while (resultSet.next()){
                System.out.println(
                        String.format(
                                "Id : %d | Desc : %s | Status : %s",
                                resultSet.getInt("id"),
                                resultSet.getString("description"),
                                resultSet.getString("status")

                        )
                );
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void exit() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void markTaskCompleted(int id) {
        //check if the task with given id is present or not

        String fetchQuery = String.format("select description, status from task where id = %d", id);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(fetchQuery);
            if(!resultSet.next()){
                System.out.printf("Task with id : %d is absent",id);
                return;
            }
            String status = resultSet.getString("status"), description = resultSet.getString("description");
            if(status.equals("completed")){
                System.out.println("Task is already completed");
                return;
            }

            String updateQuery = String.format("update task set status = \"completed\" where id = %d", id);
            statement.executeUpdate(updateQuery);
            System.out.println(String.format("Task : %s is marked completed successfully",description));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//    public static void deleteTask(int id) {
//        if(!isValidId(id)){
//            return;
//        }
//        taskMap.remove(id);
//        System.out.println("\n==Task Deleted Successfully==\n");
//
//
//    }
//    private  static boolean isValidId(int id){
//        if (taskMap.containsKey(id)) {
//            return true;
//        }
//        System.out.println("Task with given id is not present");
//        return false;
//    }
}
//how to generate unique id for an object?
//currently building the app such that task get deleted once the program closes
//make variables private
//what if we want to mark task as incomplete?

