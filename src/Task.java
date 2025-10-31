import java.util.ArrayList;
import java.util.List;

public class Task {
    private static List<Task> taskList;
    private static int taskId;
    static {
        taskList = new ArrayList<>();
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
        taskList.add(new Task(description));
        System.out.println("==Task Added Successfully==");
    }
    public static void viewAllTask(){
        if(taskList.size() == 0){
            System.out.println("==No Tasks Yet==");
            return;
        }
        System.out.println("==Tasks==");
        for(Task task : taskList){
            System.out.println(String.format("Id : %d , Description : %s",task.id , task.description));
        }
    }
}
//how to generate unique id for an object?
//currently building the app such that task get deleted once the program closes
//make variables private
