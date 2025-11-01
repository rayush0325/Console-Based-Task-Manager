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
                "3. Mark Task Completed\n" +
                "==============\n";



        System.out.println(menu);

        System.out.print("Your Option : ");
        int option = in.nextInt();
        in.nextLine();
        while (option != 0){
            switch (option){
                case 1 : viewTask();
                    break;
                case 2: addTask();
                    break;
                case 3: markTaskCompleted();
                    break;

            }
            System.out.println(menu);
            System.out.print("Your Option : ");
            option = in.nextInt();
            in.nextLine();//to clear the buffer
        }
    }

    private static void markTaskCompleted() {
        System.out.print("Enter Task Id : ");
        int id = in.nextInt();
        in.nextLine();
        Task.markTaskCompleted(id);
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


