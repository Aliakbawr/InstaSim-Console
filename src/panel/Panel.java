package panel;

import All.Page.Page;
import All.User.UserManager;
import All.User.UserPanel;

import java.util.Scanner;

public class Panel {
    static Scanner scanner = new Scanner(System.in);

    public static void mainPanel() {
        System.out.println("Welcome! choose :\n1.Sign Up\t2.Sign In");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                signUp();
                break;
            case 2:signIn();break;
        }
    }

    public static void signUp() {
        String firstname, lastname, email, phoneNumber, username, password;
        System.out.println("Enter username");
        username = scanner.next();
        System.out.println("Enter firstname");
        firstname = scanner.next();
        System.out.println("Enter lastname");
        lastname = scanner.next();
        System.out.println("Enter phoneNumber");
        phoneNumber = scanner.next();
        System.out.println("Enter email");
        email = scanner.next();
        System.out.println("Enter password");
        password = scanner.next();

        System.out.println("Checked ... continue ?\n 1 for yes and 2 for skipping");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: {
                String bio;
                boolean isPrivate = false;
                System.out.println("Enter Bio");
                bio = scanner.nextLine();
                System.out.println("1.private 2.Public");
                int choice02 = scanner.nextInt();
                switch (choice02) {
                    case 1 -> isPrivate = true;
                    case 2 -> {
                    }
                }
                Page page1 = new Page(bio, isPrivate);
                UserManager.addUser(username, firstname, lastname, phoneNumber, email, password, page1);
                System.out.println("Done");
                mainPanel();
            }
            break;
            case 2:
                Page page = new Page();
                UserManager.addUser(username, firstname, lastname, phoneNumber, email, password, page);
                System.out.println("Done");
                mainPanel();
                break;
        }
    } //    sign up
    public static boolean signIn() {
        String username,password;
        System.out.println("Enter username");
        username = scanner.next();
        System.out.println("Enter password");
        password = scanner.next();

        for (int i = 0; i < UserManager.users.size(); i++) {
            if (UserManager.users.get(i).getUsername().equals(username)
                    && UserManager.users.get(i).getPassword().equals(password)){
                UserPanel.mainPanel(UserManager.findUser(username));
                return true;
            }
        }
        System.out.println("User not found\n1.re-enter 2.return");
        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1: signIn();break;
            case 2: mainPanel();break;
        }
        return false;
    } //    sign in

}
