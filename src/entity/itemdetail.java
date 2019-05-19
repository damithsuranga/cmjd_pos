package entity;

public class itemdetail {


   private itemdetailPK itemdetailPK;
    private Integer qty;
    private double unitPrice;

    public itemdetail(){

    }

    public itemdetail(itemdetailPK itemdetailPK, Integer qty, double unitPrice) {
        this.itemdetailPK = itemdetailPK;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public itemdetail(String orderId, String itemCode, Integer qty, double unitPrice) {
       this.itemdetailPK = new itemdetailPK(orderId,itemCode);
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public entity.itemdetailPK getItemdetailPK() {
        return itemdetailPK;
    }

    public void setItemdetailPK(entity.itemdetailPK itemdetailPK) {
        this.itemdetailPK = itemdetailPK;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "itemdetail{" +
                "itemdetailPK=" + itemdetailPK +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}



