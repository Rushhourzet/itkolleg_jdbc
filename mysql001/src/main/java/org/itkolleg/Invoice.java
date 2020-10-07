package org.itkolleg;

import java.sql.Date;

public class Invoice {
    private int index;
    private Date date;
    private String description;
    private double value;
    private boolean paid;

    public Invoice(int index, Date date, String description, double value, boolean paid) {
        this.index = index;
        this.date = date;
        this.description = description;
        this.value = value;
        this.paid = paid;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void printInvoice(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "index=" + index +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", paid=" + paid +
                '}';
    }
}
