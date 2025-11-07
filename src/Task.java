import java.io.*;
import java.sql.*;
import java.util.*;

public class Task {

    private static int taskId;
    private static  final String username = "root";
    private static  final String password = "123456";
    private static  final String url = "jdbc:mysql://127.0.0.1:3306/taskmanagerdatabase";
    private static  Connection connection ;
    private static Scanner in;

    static {
        in = new Scanner(System.in);
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
        in.close();
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void changeTaskStatus() {
        //check if the task with given id is present or not

        try {
            System.out.print("Enter task id :");
            int id = in.nextInt();
            in.nextLine();
            Statement statement = connection.createStatement();
            String fetchQuery = String.format("select status from task where id = %d", id);
            ResultSet resultSet = statement.executeQuery(fetchQuery);
            if(!resultSet.next()){
                System.out.printf("\n Task with id : %d does not exist",id);
                return;
            }
            String status = resultSet.getString("status");

            String updateQuery = String.format("update task set status = \"%s\" where id = %d",
                    (status.equals("completed") ? "pending" : "completed"),
                    id
                    );
            statement.executeUpdate(updateQuery);
            System.out.println("Status changed successfully");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteTask(int id) {
        try {
            Statement statement = connection.createStatement();
            String deleteQuery = String.format("delete from task where id = %d",id);
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if(rowsAffected > 0){
                System.out.println("\n==Task Deleted Successfully==\n");
            }else {
                System.out.printf("\nTask with id : %d does not exist",id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void editTask() {

        try {
            System.out.print("Enter task id :");
            int id = in.nextInt();
            in.nextLine();
            Statement statement = connection.createStatement();
            String fetchQuery = String.format("select description from task where id = %d", id);
            ResultSet resultSet = statement.executeQuery(fetchQuery);
            if(!resultSet.next()){
                System.out.printf("\nTask with id : %d does not exist",id);
                return;
            }

            String description = resultSet.getString("description");
            System.out.printf("\nCurrent description : \"%s\"\n",description);

            System.out.print("Enter new description : ");
            String newDesciption = in.nextLine();


            String updateQuery = String.format("update task set description = \"%s\" where id = %d",newDesciption, id);
            statement.executeUpdate(updateQuery);
            System.out.println("Description changed successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void searchByKeyword() {
        System.out.print("Enter Keyword : ");
        String keyword = in.nextLine();
        String fetchQuery = String.format("select * from task where description like \"%%%s%%\"",keyword);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(fetchQuery);
            if(!resultSet.next()){
                System.out.printf("\nNo task with keyword \"%s\" is present", keyword);
                return;
            }
            do {
                System.out.printf("\nid : %d | description : \"%s\" | status : %s\n",
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("status")
                        );
            }while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


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

