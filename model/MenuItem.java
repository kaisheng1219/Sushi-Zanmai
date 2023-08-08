package model;

public class MenuItem implements Item {
    private String name;
    private double price;
    private String itemType;
    private int quantity = 0;

    public MenuItem(String name, double price, String itemType) {
        this.name = name;
        this.price = price;
        this.itemType = itemType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        if (this.quantity > 1)
            return this.quantity * this.price;
        return this.price; 
    }
    
    @Override
    public String getItemType() {
        return this.itemType;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }
}