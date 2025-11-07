import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    static Scanner in;
    public static void main(String[] args) {
        in = new Scanner(System.in);
        System.out.println("==Task Manager==");
        String menu =
                "\n=== Menu ===\n"+
                "1. View Task\n" +
                "2. Add task\n" +
                "3. Change Task Status\n" +
                "4. Delete Task\n" +
                "5. Edit Task\n" +
                "6. Search Tasks By Keyword\n" +
                "==============\n";



        System.out.println(menu);

        System.out.print("Your Option : ");
        int option = in.nextInt();
        in.nextLine();
        while (option != 0){
            switch (option){
                case 1 :
                    viewTask();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    Task.changeTaskStatus();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    Task.editTask();
                    break;
                case 6 : Task.searchByKeyword();
                    break;

            }
            System.out.println(menu);
            System.out.print("Your Option : ");
            option = in.nextInt();
            in.nextLine();//to clear the buffer
        }
        Task.exit();

    }



    private static void deleteTask() {
        System.out.print("Enter Task Id : ");
        int id = in.nextInt();
        in.nextLine();
        Task.deleteTask(id);
    }



    private static void addTask() {
        System.out.println("Enter task description : ");
        String description = null;
        try {
            description = in.nextLine();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(description != null){
            Task.addTask(description);
        }


    }

    public static void viewTask(){
        Task.viewAllTask();
    }

}
//using scanner in app class
//handle wrong input by user
//what about making getIdFromUser function which will check if given id is present or not


