import java.time.LocalTime;
import java.util.Random;

public class User implements Runnable {

private String name;
private BankAccount bankAccount;
private static final Random rand = new Random();
private static int iterations = 20;
private boolean isFinished = true;

    public User(String name, BankAccount bankAccount) {
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public static void setIterations(int iterations) {
        User.iterations = iterations;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void run() {
        isFinished = false;
        for (int i = iterations; i>0; i--) {
            synchronized (bankAccount) {
                int value = rand.nextInt(100)-50;
                System.out.println(name + " wants to withdraw " + value + " €");
                LocalTime time = LocalTime.now();
                while (bankAccount.isNeg(value)) {
                    try {
                        if ((LocalTime.now().getSecond() - time.getSecond()) > 2) break;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if (!bankAccount.isNeg(value)) {
                    bankAccount.withdraw(value);
                    System.out.println("Total balance: " + bankAccount.getBalance() + " €");
                    bankAccount.notify();
                }
            }
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isFinished = true;
        System.out.println("Simulation finished for " + getName());
    }
}
