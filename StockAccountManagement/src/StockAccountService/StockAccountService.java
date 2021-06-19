package StockAccountService;

import StockAccountModel.StockAccount;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class StockAccountService implements StockAccountInterface{
    Scanner input = new Scanner(System.in);
    StockAccount stockAccount = new StockAccount();
    JSONObject jsonObject = null;

    @Override
    public JSONArray buyShare(String companyName, JSONArray array) {
        int sharesNumber;

        if(array.isEmpty()) {
            stockAccount.setName(companyName);
            System.out.print("Enter the number of shares you want to buy: ");
            stockAccount.setNoOfShare(input.nextInt());
            System.out.print("Enter the share price: ");
            stockAccount.setSharePrice(input.nextDouble());
            System.out.println();

            jsonObject = new JSONObject();
            jsonObject.put("Name", stockAccount.getName());
            jsonObject.put("Number of shares", stockAccount.getNoOfShare());
            jsonObject.put("Price", stockAccount.getSharePrice());

            array.put(jsonObject);
            System.out.println("Bought successfully" + "\n");
        }
        else {
            for(int i = 0; i < array.length(); i++) {
                jsonObject = (JSONObject) array.get(i);
                if(jsonObject.get("Name").equals(companyName)) {
                    sharesNumber = (int) jsonObject.get("Number of shares");
                    System.out.print("Enter the number of shares you want to buy: ");
                    sharesNumber = input.nextInt();
                    sharesNumber = sharesNumber + stockAccount.getNoOfShare();
                    jsonObject.put("Number of shares", sharesNumber);
                    System.out.println("Bought successfully" + "\n");

                    break;
                }
                else {
                    stockAccount.setName(companyName);
                    System.out.print("Enter the number of shares you want to buy: ");
                    stockAccount.setNoOfShare(input.nextInt());
                    System.out.print("Enter the share price: ");
                    stockAccount.setSharePrice(input.nextDouble());
                    System.out.println();

                    System.out.println("Bought successfully" + "\n");
                    jsonObject = new JSONObject();
                    jsonObject.put("Name", stockAccount.getName());
                    jsonObject.put("Number of shares", stockAccount.getNoOfShare());
                    jsonObject.put("Price", stockAccount.getSharePrice());

                    array.put(jsonObject);
                    break;
                }
            }
        }
        return array;
    }

    @Override
    public JSONArray sellShare(JSONArray array) {

        System.out.print("Enter the name of the share: ");
        stockAccount.setName(input.next());
        System.out.print("Enter the number of shares you want to buy: ");
        stockAccount.setNoOfShare(input.nextInt());
        System.out.println();

        int shares;
        for (int i = 0; i < array.length(); i++) {
            jsonObject = (JSONObject) array.get(i);
            if(jsonObject.get("Name").equals(stockAccount.getName()))
            {
                shares = (int) jsonObject.get("Number of shares");
                shares = shares - stockAccount.getNoOfShare();
                jsonObject.put("Number of shares", shares);
            }
        }
        return array;
    }

    @Override
    public void display(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject=(JSONObject) jsonArray.get(i);
            System.out.println(jsonObject.toString());
        }
        System.out.println();
    }

    @Override
    public void calculateEachValue(JSONArray jsonArray) {
        double value = 0, price = 0;
        int numberOfShare = 0;

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.get("Name").equals(stockAccount.getName())) {
                price = (double) jsonObject.get("Price");
                numberOfShare = (int) jsonObject.get("Number of shares");
                value = price * numberOfShare;
            }
        }
        System.out.println("Value of the particular stock for " +jsonObject.get("Name")+ " is " +value +"\n");
    }

    @Override
    public void calculateTotalValue(JSONArray jsonArray) {
        double totalValue = 0, value=0;
        double price=0;
        int numberOfShare=0;

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.get("Name").equals(stockAccount.getName())) {
                price = (double) jsonObject.get("Price");
                numberOfShare = (int) jsonObject.get("Number of shares");
                value = price * numberOfShare;
                totalValue = totalValue + value;
            }
        }
        System.out.println("Total value of the stock: " +totalValue +"\n");
    }
}
