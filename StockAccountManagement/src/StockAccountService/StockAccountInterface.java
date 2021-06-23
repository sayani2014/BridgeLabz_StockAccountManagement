package StockAccountService;

import org.json.JSONArray;

public interface StockAccountInterface {
    abstract JSONArray insertShare(JSONArray array);
    abstract JSONArray buyShare(String companyName, JSONArray array);
    abstract JSONArray sellShare(String companyName, JSONArray array);
    abstract void display(JSONArray jsonArray);
    abstract void calculateEachValue(JSONArray jsonArray);
    abstract void calculateTotalValue(JSONArray jsonArray);
}
