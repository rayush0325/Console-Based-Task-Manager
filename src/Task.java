import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {
    private static Map<Integer,Task> taskMap;
    private static int taskId;
    static {
        taskMap = new HashMap<>();
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
        taskMap.put(task.id, task);
        System.out.println("==Task Added Successfully==");
    }
    public static void viewAllTask(){
        if(taskMap.size() == 0){
            System.out.println("==No Tasks Yet==");
            return;
        }
        System.out.println("==Tasks==");
        for(Map.Entry<Integer, Task> entry : taskMap.entrySet()){
            Task task = entry.getValue();
            System.out.println(String.format("Id : %d , Description : %s, status : %s",task.id , task.description,
                    (task.status) ? "completed" : "pending"
                    ));
        }
    }

    public static void markTaskCompleted(int id) {
        //check if the task with given id is present or not
        if(!isValidId(id)){
            return;
        }
        Task task = taskMap.get(id);
        if(task.status == true){
            System.out.println("Task is already completed");
        }
        else {
            task.status = true;
            System.out.println(String.format("Task : %s is marked completed successfully",task.description));
        }
    }

    public static void deleteTask(int id) {
        if(!isValidId(id)){
            return;
        }
        taskMap.remove(id);
        System.out.println("\n==Task Deleted Successfully==\n");


    }
    private  static boolean isValidId(int id){
        if (taskMap.containsKey(id)) {
            return true;
        }
        System.out.println("Task with given id is not present");
        return false;
    }
}
//how to generate unique id for an object?
//currently building the app such that task get deleted once the program closes
//make variables private
//what if we want to mark task as incomplete?

