package StockAccountService;

import org.json.JSONArray;

public interface StockAccountInterface {
    abstract JSONArray buyShare(String companyName, JSONArray array);
    abstract JSONArray sellShare(JSONArray array);
    abstract void display(JSONArray jsonArray);
    abstract void calculateEachValue(JSONArray jsonArray);
    abstract void calculateTotalValue(JSONArray jsonArray);
}
