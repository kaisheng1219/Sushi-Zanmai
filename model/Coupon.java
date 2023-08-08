package model;

public class Coupon {
    private String code;
    private double discountAmount;
    private String couponType;

    public Coupon(String code, double discountAmount) {
        this.code = code;
        this.discountAmount = discountAmount;
        setCouponType();
    }

    public double getDiscountAmount() {
        return this.discountAmount;
    }

    public String getCouponType() {
        return this.couponType;
    }

    public boolean matches(String code) {
        return this.code.equals(code);     
    }

    private void setCouponType() {
        if (this.code.charAt(0) == 'F')
            this.couponType = "FOOD";
        else if (this.code.charAt(0) == 'D')
            this.couponType = "DRINK";
        else if (this.code.charAt(0) == 'A')
            this.couponType = "ALL";
    }
}