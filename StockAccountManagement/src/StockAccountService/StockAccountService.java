package StockAccountService;

import StockAccountModel.StockAccount;
import StockAccountModel.StockBuySellDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class StockAccountService implements StockAccountInterface{
    Scanner input = new Scanner(System.in);
    JSONArray stockArray = new JSONArray();
    StockAccount stockAccount = new StockAccount();
    StockBuySellDetails stock = new StockBuySellDetails();
    JSONObject jsonObject = null;
    String filepath = "src/StockAccountController/StockAccount.json";
    String filePathStockExchange = "src/StockAccountController/StockAccountBuySell.json";

    @Override
    public void writeFile(JSONArray array) {
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(array.toString());
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        readFile();
    }

    public JSONArray writeStockExchangeDetails(String message) {

        Date date = new Date();
        stock.setMessage(message);
        stock.setDate(date);

        jsonObject = new JSONObject();
        jsonObject.put("Message", stock.getMessage());
        jsonObject.put("Date of Transaction", stock.getDate());

        stockArray.add(jsonObject);

        try {
            FileWriter fw = new FileWriter(filePathStockExchange);
            fw.write(stockArray.toString());
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockArray;
    }

    @Override
    public JSONArray readFile() {
        JSONParser jsonParser = new JSONParser();
        JSONArray stock = null;
        try {
            FileReader reader = new FileReader(filepath);
            stock = (JSONArray) jsonParser.parse(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return stock;
    }

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

        array.add(jsonObject);

        writeFile(array);

        return array;
    }

    @Override
    public JSONArray buyShare(String companyName, JSONArray array) {
        long sharesNumberExist;
        String message = " ";
        for(int i = 0; i < array.size(); i++) {
            jsonObject = (JSONObject) array.get(i);
            String existingName=(String) jsonObject.get("Name");
            if(existingName.equalsIgnoreCase(companyName)) {
                sharesNumberExist = (long) jsonObject.get("Number of shares");
                System.out.print("Enter the number of shares you want to buy: ");
                int sharesNumber = input.nextInt();
                if(sharesNumberExist >= sharesNumber) {
                    sharesNumberExist = sharesNumberExist - sharesNumber;
                    jsonObject.replace("Number of shares", sharesNumberExist);
                    message = "Bought successfully from " +companyName ;
                }
                else
                    System.out.println("Number of shares exceeds the total number of shares!");
                break;
            }
        }
        writeStockExchangeDetails(message);
        return array;
    }

    @Override
    public JSONArray sellShare(String companyName, JSONArray array) {
        long sharesExist;
        String message = " ";
        for (int i = 0; i < array.size(); i++) {
            jsonObject = (JSONObject) array.get(i);
            String existingName = (String) jsonObject.get("Name");
            if(existingName.equalsIgnoreCase(companyName)) {
                System.out.print("Enter the number of shares you want to sell: ");
                int shares = input.nextInt();
                sharesExist = (long) jsonObject.get("Number of shares");
                sharesExist = shares + sharesExist;
                jsonObject.replace("Number of shares", sharesExist);
                message = "Sold successfully to " +companyName ;
                break;
            }
        }
        writeStockExchangeDetails(message);
        return array;
    }

    @Override
    public void display(JSONArray jsonArray) {
        /*for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject=(JSONObject) jsonArray.get(i);
            System.out.println(jsonObject.toString());
        }
        System.out.println();*/
        System.out.println(jsonArray + "\n");
    }

    @Override
    public void calculateEachValue(JSONArray jsonArray) {
        double value = 0, price = 0;
        long numberOfShare = 0;

        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            //if (jsonObject.get("Name").equals(stockAccount.getName())) {
            price = (double) jsonObject.get("Price");
            numberOfShare = (long) jsonObject.get("Number of shares");
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
        long numberOfShare=0;

        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            //if (jsonObject.get("Name").equals(stockAccount.getName())) {
            price = (double) jsonObject.get("Price");
            numberOfShare = (long) jsonObject.get("Number of shares");
            value = price * numberOfShare;
            totalValue = totalValue + value;
            //}
        }
        System.out.println("Total value of the stock: " +totalValue +"\n");
    }
}
