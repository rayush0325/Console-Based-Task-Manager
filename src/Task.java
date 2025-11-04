import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {
    private static final String filePath = "C:\\ayush\\Programming\\Learning Backend\\TaskManager\\src\\MyTaskFile.txt";
    private static BufferedReader reader;
    private static BufferedWriter writer;
//    private static Map<Integer,Task> taskMap;
    private static int taskId;

    static {
//        taskMap = new HashMap<>();
//        try{
//
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        taskId = 0;
    }
    Task(String description){
        this.id = ++taskId;
        this.description = description;
    }
    int id;
    String description;
    boolean status;

    public static void addTask(String description){
        Task task = new Task(description);
//        taskMap.put(task.id, task);
        try{
            writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(String.format("%s,%s,%s", task.id, task.description, (task.status)?"completed":"pending"));
            writer.newLine();
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("==Task Added Successfully==");
    }
    public static void viewAllTask(){
//        if(taskMap.size() == 0){
//            System.out.println("==No Tasks Yet==");
//            return;
//        }
        try {
            reader = new BufferedReader(new FileReader(filePath));
            if(reader.readLine() == null){
                System.out.println("==No Tasks Yet==");
                return;
            }
            System.out.println("==Tasks==");
            String line = reader.readLine();
            while (line != null){
                String[] taskDetail = line.split(",");
                System.out.println(String.format("Id : %s , Description : %s, status : %s",taskDetail[0] , taskDetail[1], taskDetail[2]));
                line = reader.readLine();
            }
            reader.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

//        for(Map.Entry<Integer, Task> entry : taskMap.entrySet()){
//            Task task = entry.getValue();
//
//        }
    }

    public static void exit() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void markTaskCompleted(int id) {
//        //check if the task with given id is present or not
//        if(!isValidId(id)){
//            return;
//        }
//        Task task = taskMap.get(id);
//        if(task.status == true){
//            System.out.println("Task is already completed");
//        }
//        else {
//            task.status = true;
//            System.out.println(String.format("Task : %s is marked completed successfully",task.description));
//        }
//    }

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

