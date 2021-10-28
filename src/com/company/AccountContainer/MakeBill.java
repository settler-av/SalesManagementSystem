package com.company.AccountContainer;

import java.util.Date;

class Account {
    protected int code;       //Will hold the Account code, that will be useful to find the account holder in the Accounts.dat file
    protected int billNo;    //will hold the bill number for the account holder
    protected int length;
    protected Date DateCreated;   //tells the date when the bill was created
    protected double cost;        //Not needed now
    protected double price;
    protected double quantity;
    protected String NameOfAccountHolder;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getNameOfAccountHolder() {
        return NameOfAccountHolder;
    }

    public void setNameOfAccountHolder(String nameOfAccountHolder) {
        NameOfAccountHolder = nameOfAccountHolder;
    }
}

/**
 * contains all function for making bill
 */
public class MakeBill extends Account {
    /**
     * THIS FUNCTION DISPLAYS THE LIST OF THE BILLS
     */
    public void billList() {

    }

    /**
     * THIS FUNCTION PREPARES AND DISPLAYS THE BILL FOR THE GIVEN BILL NO. ACCORDING TO PURCHASES MADE.
     *
     * @param tempBillNo acts as a bill number for current bill
     */
    public void prepareBill(int tempBillNo) {

    }

    /**
     * THIS FUNCTION RETURNS THE BILL NO. OF THE LAST RECORD IN THE BILL FILE (BILL.DAT)
     */
    public int lastBillNo() {
        return 0;
    }

    /**
     * THIS FUNCTION ADDS THE RECORD IN THE BILL FILE (BILL.DAT)
     *
     * @param tempBillNo   acts as a temporary bill number
     * @param tempItemCode acts as a temporary item code
     * @param tempItemName acts as a temporary item name
     * @param tempQuantity acts as a temporary Quantity
     * @param tempCost     acts as a temporary cost
     * @param tempPrice    acts as a temporary price
     */
    public void addBill(int tempBillNo, int tempItemCode, String tempItemName, double tempQuantity, double tempCost, double tempPrice) {

    }
}
