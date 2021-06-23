package StockAccountModel;

import java.util.Date;

public class StockBuySellDetails {
    private String message;
    private Date date;

    public StockBuySellDetails() {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }
}
