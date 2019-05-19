package model;

public class ItemTM {

    private String code;
    private String description;
    private double unitPrice;
    private Integer qtyonHand;

    public ItemTM(){

    }

    public ItemTM(String code, String description, double unitPrice, Integer qtyonHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyonHand = qtyonHand;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQtyonHand() {
        return qtyonHand;
    }

    public void setQtyonHand(Integer qtyonHand) {
        this.qtyonHand = qtyonHand;
    }
}
