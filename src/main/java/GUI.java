import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI {
    private static final Scanner sc = new Scanner(System.in);
    private static List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome");
        BankAccount bankAccount = new BankAccount(50);
        /*User user = new User("HY", bankAccount);
        User user1 = new User("JK", bankAccount);
        User user2 = new User("FN", bankAccount);
        new Thread(user).start();
        new Thread(user1).start();
        new Thread(user2).start();*/

        userList.add(new User("HY", bankAccount));
        userList.add(new User("Gerry", bankAccount));

        for (;;) {

            System.out.println("What do you want to do:");
            System.out.println("Add a new User: 1");
            System.out.println("Run a simulation: 2");
            System.out.println("Delete a user: 3");

            int opt = sc.nextInt();

            switch (opt) {
                case 1 -> {
                    System.out.println("What's his name: ");
                    String name = sc.next();
                    if (userList.contains(new User(name, bankAccount))) {
                        System.out.println("User already exists");
                        break;
                    }
                    userList.add(new User(name, bankAccount));
                    System.out.println("User successfully added");
                }
                case 2 -> {
                    System.out.println("Set the number of iterations: ");
                    try {
                        int amount = sc.nextInt();
                        if (amount < 1) throw new Exception();
                        User.setIterations(amount);
                    } catch (Exception e) {
                        System.out.println("Not a valid number");
                    }
                    for (User user : userList) {
                        new Thread(user).start();
                    }
                }
                case 3 -> {
                    System.out.println("Which user do you want to delete: ");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println((i + 1) + ": " + userList.get(i).getName());
                    }
                    int delete = sc.nextInt() - 1;
                    try {
                        userList.remove(delete);
                        System.out.println("User successfully removed");
                    } catch (Exception e) {
                        System.out.println("This user doesn't exist!!");
                    }
                }
                default -> System.out.println("Faulty input");
            }
        }

    }

}
