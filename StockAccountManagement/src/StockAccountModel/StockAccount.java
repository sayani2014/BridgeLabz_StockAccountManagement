package StockAccountModel;

public class StockAccount {
    private String name;
    private int noOfShare;
    private double sharePrice;

    public StockAccount() {
    }

    public StockAccount(String name, int noOfShare, double sharePrice) {
        this.name = name;
        this.noOfShare = noOfShare;
        this.sharePrice = sharePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfShare() {
        return noOfShare;
    }

    public void setNoOfShare(int noOfShare) {
        this.noOfShare = noOfShare;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }
}
