package vn.aptech.warehousemobile.entity;

public class Transaction {
    private int id;
    private String type;
    private String goods_name;
    private String from_loc;
    private String to_loc;
    private int quantity;

    public Transaction() {
    }

    public Transaction(int id, String type, String goods_name, String from_loc, String to_loc, int quantity) {
        this.id = id;
        this.type = type;
        this.goods_name = goods_name;
        this.from_loc = from_loc;
        this.to_loc = to_loc;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getFrom_loc() {
        return from_loc;
    }

    public void setFrom_loc(String from_loc) {
        this.from_loc = from_loc;
    }

    public String getTo_loc() {
        return to_loc;
    }

    public void setTo_loc(String to_loc) {
        this.to_loc = to_loc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
