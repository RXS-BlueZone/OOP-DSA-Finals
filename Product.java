package trial;

class Product {
    private int productNumber;
    private String productName;
    private int price;
    private int stocks;

    public Product(int productNumber, String productName, int price, int stocks) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.stocks = stocks;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getStocks() {
        return stocks;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public void updateStocks(int quantity) {
        stocks += quantity;
    }
}