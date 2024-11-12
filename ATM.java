

public class ATM {
   

    public static void main(String[] args) {
        // Creating a demo account
        accounts.add(new Account("user1", "1234", 1000.00));

        if (login()) {
            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        loggedInAccount.showTransactionHistory();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid login. Exiting.");
        }
    }

    private static boolean login() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        for (Account account : accounts) {
            if (account.validateCredentials(userId, pin)) {
                loggedInAccount = account;
                System.out.println("Login successful.");
                return true;
            }
        }
        return false;
    }

    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        loggedInAccount.withdraw(amount);
    }

    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        loggedInAccount.deposit(amount);
    }

    private static void transfer() {
        System.out.print("Enter recipient user ID: ");
        String userId = scanner.nextLine();
        Account recipient = null;
        for (Account account : accounts) {
            if (account.validateCredentials(userId, "")) {
                recipient = account;
                break;
            }
        }

        if (recipient != null && recipient != loggedInAccount) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            loggedInAccount.transfer(recipient, amount);
        } else {
            System.out.println("Invalid recipient.");
        }
    }
}
