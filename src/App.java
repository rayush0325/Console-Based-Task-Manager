import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in;
    public static void main(String[] args) {
        in = new Scanner(System.in);
        System.out.println("==Task Manager==");
        String menu =
                "=== Menu ===\n"+
                "1. View Task\n" +
                "2. Add task\n" +
                "==============";



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
            }
            System.out.println(menu);
            System.out.print("Your Option : ");
            option = in.nextInt();
            in.nextLine();//to clear the buffer
        }
    }

    private static void addTask() {
        System.out.println("Enter task description : ");
        String description = in.nextLine();
        Task.addTask(description);
    }

    public static void viewTask(){
        Task.viewAllTask();
    }

}
//using scanner in app class
//handle wrong input by user


