package StockAccountController;

import StockAccountService.StockAccountService;
import StockAccountService.StockAccountInterface;
import StockAccountUtil.UserInputOutput;
import org.json.JSONArray;

import java.util.Scanner;

public class StockAccountMain {
    static StockAccountInterface stockAccountService = new StockAccountService();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        boolean flag = true;

        while (flag) {
            int choice = UserInputOutput.getUserChoice();
            switch (choice) {
                case 1:
                    System.out.println("\n" + "Buy stocks");
                    System.out.print("Enter the name of the share: ");
                    String companyName = input.next();
                    jsonArray = stockAccountService.buyShare(companyName, jsonArray);
                    flag = true;
                    break;
                case 2:
                    System.out.println("\n" + "Sell stocks");
                    jsonArray = stockAccountService.sellShare(jsonArray);
                    flag = true;
                    break;
                case 3:
                    System.out.println("\n" + "Display stocks");
                    stockAccountService.display(jsonArray);
                    flag = true;
                    break;
                case 4:
                    System.out.println("\n" + "Calculate each value stocks");
                    stockAccountService.calculateEachValue(jsonArray);
                    flag = true;
                    break;
                case 5:
                    System.out.println("\n" + "Calculate total value stocks");
                    stockAccountService.calculateTotalValue(jsonArray);
                    flag = true;
                    break;
                case 6:
                    flag = false;
                    break;

            }
        }
    }
}
