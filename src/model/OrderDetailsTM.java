package model;

public class OrderDetailsTM {

    private String code;
    private String description;
    private Integer Quantity;
    private double unitPrice;
    private double Total;

    private OrderDetailsTM(){

    }

    public OrderDetailsTM(String code, String description, Integer quantity, double unitPrice, double total) {
        this.code = code;
        this.description = description;
        Quantity = quantity;
        this.unitPrice = unitPrice;
        Total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
}
