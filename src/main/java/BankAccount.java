public class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isNeg(int val) {
        return (balance-val) < 0;
    }

    public void withdraw(int val) {
        balance -= val;
    }



}
