package com.company.ProductContainer;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

/**
 * @implNote It controls over all functions related to product item
 */
public class Product {
    int itemCode;
    String itemName;
    double itemCost;
    double itemPrice;

    /**
     * @implNote this function add Records in the Product.dat
     */
    public void addItem() {
        int tempCode;//Temporary product code to traverse in file
        boolean isValid; //To check item given by user is valid or not
        String userInput; //To handle user input from the console
        String itemCostStr; //To get item cost from user
        String itemPriceStr; //To get the selling price of item from user

        //To Update the entry of table we need to get the code of last entry of the table
        tempCode = lastCode();
        tempCode++;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("~~~~~~~~~~~~ADDITION OF PRODUCTS~~~~~~~~~~~~~");
            System.out.println("Item code : " + tempCode);

            //block to get Item name
            do{
                isValid = true;
                System.out.println("Enter item name to add in the menu");
                System.out.print("Item Name : ");
                itemName = input.nextLine();
                itemName = itemName.toUpperCase();
                if(itemName.length() <1 || itemName.length()>20){
                    isValid = false;
                    System.out.println("Range of name = 1 ... 20");
                }
            }while (!isValid);
            System.out.println("Item Name (Confirmed) : " + itemName);

            //Block to get the cost of item
            do{
                isValid = true;
                System.out.println("Enter item cost to add in the menu");
                System.out.print("Item Cost : ");
                itemCostStr = input.nextLine();
                itemCost = parseDouble(itemCostStr);
                if(itemCost < 1 || itemCost > 800){
                    isValid = false;
                    System.out.println("Range of cost = 1 ... 800");
                }
            }while(!isValid);
            System.out.println("Item Cost (Confirmed): " + itemCost);

            //Block to get the selling price of item
            do{
                isValid = true;
                System.out.println("Enter item price to add in the menu");
                System.out.print("Item price : ");
                itemPriceStr = input.nextLine();
                itemPrice = parseDouble(itemPriceStr);
                if(itemPrice < 1 || itemPrice > 800){
                    isValid = false;
                    System.out.println("Range of price = " +itemCost+ "... 1000");
                }
            }while(!isValid);
            System.out.println("Item Price (Confirmed): " + itemPrice);

            //Getting Confirmation from user
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            do{
                System.out.println("Do you want to save this record? (y/n) : ");
                userInput = input.nextLine();
            }while(!(userInput.equalsIgnoreCase("Y") && userInput.equalsIgnoreCase("N")));

            //If yes update the file product.dat
            if(userInput.equalsIgnoreCase("Y")){
                itemCode = tempCode;
                //open the file update and close use try catch block

//                //if operation is successful
//                tempCode++;

//                System.out.println("File successfully updated");
            }

            //Ask if user want to add more records or not
            do{
                System.out.print("Do you want to add more records (y/n) : ");
                userInput=input.nextLine();
            }while (!(userInput.equalsIgnoreCase("Y") && userInput.equalsIgnoreCase("N")));
        } while (userInput.equalsIgnoreCase("Y"));
    }//End of method addItem

    public void deleteItem() {
    }

    public void modifyItem() {

    }


    public void listOfItem() {
    }

    public void purchase() {
    }

    private int lastCode() {
        return 0;
    }


    private void deleteRecord(int tempCode) {
    }

    private void modifyRecord(int tempCode) {
    }

    private void displayRecord(int tempCode) {
    }

    private int itemFound(int tempCode) {
        return 0;
    }

    private int recordNumber(int tempCode) {
        return 0;
    }

    private void sort() {
    }

}
