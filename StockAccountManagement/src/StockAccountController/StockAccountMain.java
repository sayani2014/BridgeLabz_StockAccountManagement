/**
 * Write a program to read in Stock Names, Number of Share, Share Price. Print a Stock Report with the total value of each Stock and the total value of Stock.
 * Input is Share Name, Number of Share, and Share Price.
 * Calculate the value of each stock and the total value.
 * Store the output in json format and print the json array in console.
 * Add the buy and sell of shares.
 *
 * @author: SAYANI KOLEY
 * @since: 20.06.2021
 */


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
        int count = 0;

        while (flag) {
            int choice = UserInputOutput.getUserChoice();
            switch (choice) {
                case 1:
                    System.out.println("\n" + "Insert Stocks");
                    jsonArray = stockAccountService.insertShare(jsonArray);
                    System.out.println("Number of stocks inserted is " +(++count) + "\n");
                    flag = true;
                    break;
                case 2:
                    System.out.println("\n" + "Buy Stocks");
                    System.out.print("Enter the name of the company you want to buy share from: ");
                    String companyName = input.next();
                    jsonArray = stockAccountService.buyShare(companyName, jsonArray);
                    flag = true;
                    break;
                case 3:
                    System.out.println("\n" + "Sell Stocks");
                    System.out.print("Enter the name of the company you want to sell the shares to: ");
                    String companyNameSell = input.next();
                    jsonArray = stockAccountService.sellShare(companyNameSell, jsonArray);
                    flag = true;
                    break;
                case 4:
                    System.out.println("\n" + "Display stocks");
                    stockAccountService.display(jsonArray);
                    flag = true;
                    break;
                case 5:
                    System.out.println("\n" + "Calculate each value stocks");
                    stockAccountService.calculateEachValue(jsonArray);
                    flag = true;
                    break;
                case 6:
                    System.out.println("\n" + "Calculate total value stocks");
                    stockAccountService.calculateTotalValue(jsonArray);
                    flag = true;
                    break;
                case 7:
                    flag = false;
                    break;

            }
        }
    }
}
