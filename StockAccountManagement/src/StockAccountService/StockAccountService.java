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
    public JSONArray insertShare(JSONArray array) {
        System.out.print("Enter the name of the share: ");
        stockAccount.setName(input.next());
        System.out.print("Enter the number of shares: ");
        stockAccount.setNoOfShare(input.nextInt());
        System.out.print("Enter the share price: ");
        stockAccount.setSharePrice(input.nextDouble());
        System.out.println();

        jsonObject = new JSONObject();
        jsonObject.put("Name", stockAccount.getName());
        jsonObject.put("Number of shares", stockAccount.getNoOfShare());
        jsonObject.put("Price", stockAccount.getSharePrice());

        array.put(jsonObject);

        return array;
    }

    @Override
    public JSONArray buyShare(String companyName, JSONArray array) {
        int sharesNumberExist;
        for(int i = 0; i < array.length(); i++) {
            jsonObject = (JSONObject) array.get(i);
            if(jsonObject.get("Name").equals(companyName)) {
                sharesNumberExist = (int) jsonObject.get("Number of shares");
                System.out.print("Enter the number of shares you want to buy: ");
                int sharesNumber = input.nextInt();
                if(sharesNumberExist >= sharesNumber) {
                    sharesNumberExist = sharesNumberExist - sharesNumber;
                    jsonObject.put("Number of shares", sharesNumberExist);
                    System.out.println("Bought successfully" + "\n");
                }
                else
                    System.out.println("Number of shares exceeds the total number of shares!");
                break;
            }
        }
        return array;
    }

    @Override
    public JSONArray sellShare(String companyName, JSONArray array) {
        int sharesExist;
        for (int i = 0; i < array.length(); i++) {
            jsonObject = (JSONObject) array.get(i);
            if(jsonObject.get("Name").equals(stockAccount.getName())) {
                System.out.print("Enter the number of shares you want to sell: ");
                int shares = input.nextInt();
                sharesExist = (int) jsonObject.get("Number of shares");
                sharesExist = shares + sharesExist;
                jsonObject.put("Number of shares", sharesExist);
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
            //if (jsonObject.get("Name").equals(stockAccount.getName())) {
            price = (double) jsonObject.get("Price");
            numberOfShare = (int) jsonObject.get("Number of shares");
            value = price * numberOfShare;
           // }
            System.out.println("Value of the particular stock for " +jsonObject.get("Name")+ " is " +value);
        }
        System.out.println("\n");
    }

    @Override
    public void calculateTotalValue(JSONArray jsonArray) {
        double totalValue = 0, value=0;
        double price=0;
        int numberOfShare=0;

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            //if (jsonObject.get("Name").equals(stockAccount.getName())) {
            price = (double) jsonObject.get("Price");
            numberOfShare = (int) jsonObject.get("Number of shares");
            value = price * numberOfShare;
            totalValue = totalValue + value;
            //}
        }
        System.out.println("Total value of the stock: " +totalValue +"\n");
    }
}
