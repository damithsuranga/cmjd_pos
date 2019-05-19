package entity;

import java.sql.Date;
import java.time.LocalDate;

public class orders {

   private String id;
    private LocalDate date;
    private String customerId;

    public orders(String string, Date date, String resultSetString){

    }

    public orders(String id, LocalDate date, String customerId) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    @Override
    public String toString() {
        return "orders{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
