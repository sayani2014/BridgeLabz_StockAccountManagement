package StockAccountUtil;

import java.util.Scanner;

public class UserInputOutput {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getUserChoice() {

        System.out.println("Enter option");
        System.out.println("Enter 1 to Buy stocks");
        System.out.println("Enter 2 to Sell stocks");
        System.out.println("Enter 3 to Display stocks");
        System.out.println("Enter 4 to Calculate each value stocks");
        System.out.println("Enter 5 to Calculate total value stocks");
        System.out.println("Enter 6 to Quit" + "\n");

        return scanner.nextInt();
    }
}
