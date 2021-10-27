package com.company.AccountContainer;

import java.util.Date;

public class Account {
    /**
     * Will hold the Account code, that will be useful to find the account holder in the Accounts.dat file
     */
    private int code;
    /**
     * will hold the bill number for the account holder
     */
    private int billNo;
    /**
     * no Idea
     */
    private int length;
    /**
     * tells the date when the bill was created
     */
    private Date DateCreated;

    private double cost; //Not needed now
    private double price;
    private double quantity;
    private String NameOfAccountHolder;

    /**
     * THIS FUNCTION DISPLAYS THE LIST OF THE BILLS
     */
    public void billList() {

    }

    /**
     * THIS FUNCTION PREPARES AND DISPLAYS THE BILL FOR THE GIVEN BILL NO. ACCORDING TO PURCHASES MADE.
     * @param tempBillNo acts as a bill number for current bill
     */
    public void prepareBill(int tempBillNo) {

    }

    /**
     * THIS FUNCTION RETURNS THE BILL NO. OF THE LAST RECORD IN THE BILL FILE (BILL.DAT)
     */
    public int lastBillNo() {

    }

    /**
     * THIS FUNCTION ADDS THE RECORD IN THE BILL FILE (BILL.DAT)
     * @param tempBillNo acts as a temporary bill number
     * @param tempItemCode acts as a temporary item code
     * @param tempItemName acts as a temporary item name
     * @param tempQuantity acts as a temporary Quantity
     * @param tempCost acts as a temporary cost
     * @param tempPrice acts as a temporary price
     */
    public void addBill(int tempBillNo, int tempItemCode, String tempItemName, double tempQuantity, double tempCost, double tempPrice) {

    }
}
