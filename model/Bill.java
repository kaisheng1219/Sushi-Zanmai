package model;
import java.util.ArrayList;

public class Bill implements Item { // Composite
    private ArrayList<Item> items;
    private Coupon coupon;

    public Bill() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public double getDiscount() {
        return calcDiscount();
    }

    public double getNetPayable() {
        return getPrice() - getDiscount();
    }

    @Override
    public String getName() {
        return "Bill";
    }
    
    @Override
    public double getPrice() {
        return calcTotalPrice();
    }

    @Override
    public String getItemType() { 
        return "BILL"; 
    }

    @Override
    public int getQuantity() {
        return 1;
    }

    private double calcTotalPrice() {
        double total = 0;
        for (Item i : items)
            total += i.getPrice();
        
        return total;
    }

    private double calcDiscount() {
        double discount = 0;
        if (coupon != null) {
            if (coupon.getCouponType().equals("ALL"))
                discount = coupon.getDiscountAmount() * calcTotalPrice();
        }
        return discount;
    }
}