package model;

import java.sql.Timestamp;


public class Cart {

    private int id;
    private int customerId;
    private Timestamp createdAt;

    public Cart() {
    }

    public Cart(int id, int customerId, Timestamp createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
