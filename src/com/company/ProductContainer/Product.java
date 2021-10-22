package com.company.ProductContainer;

import java.io.*;
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
    File productsFile = new File("src/com/company/ProductContainer/Product.dat");

    /**
     * @implNote this function add Records in the Product.dat
     */
    public void addItem() throws IOException {
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
            do {
                isValid = true;
                System.out.println("Enter item name to add in the menu");
                System.out.print("Item Name : ");
                itemName = input.nextLine();
                itemName = itemName.toUpperCase();
                if (itemName.length() < 1 || itemName.length() > 20) {
                    isValid = false;
                    System.out.println("Range of name = 1 ... 20");
                }
            } while (!isValid);
            System.out.println("Item Name (Confirmed) : " + itemName);

            //Block to get the cost of item
            do {
                isValid = true;
                System.out.println("Enter item cost to add in the menu");
                System.out.print("Item Cost : ");
                itemCostStr = input.nextLine();
                itemCost = parseDouble(itemCostStr);
                if (itemCost < 1 || itemCost > 800) {
                    isValid = false;
                    System.out.println("Range of cost = 1 ... 800");
                }
            } while (!isValid);
            System.out.println("Item Cost (Confirmed): " + itemCost);

            //Block to get the selling price of item
            do {
                isValid = true;
                System.out.println("Enter item price to add in the menu");
                System.out.print("Item price : ");
                itemPriceStr = input.nextLine();
                itemPrice = parseDouble(itemPriceStr);
                if (itemPrice < 1 || itemPrice > 800) {
                    isValid = false;
                    System.out.println("Range of price = " + itemCost + "... 1000");
                }
            } while (!isValid);
            System.out.println("Item Price (Confirmed): " + itemPrice);

            //Getting Confirmation from user
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            do {
                System.out.println("Do you want to save this record? (y/n) : ");
                userInput = input.nextLine();
            } while (!(userInput.equalsIgnoreCase("Y") && userInput.equalsIgnoreCase("N")));

            //If yes update the file product.dat
            if (userInput.equalsIgnoreCase("Y")) {
                itemCode = tempCode;
                //open the file update and close use try catch block

                try (FileOutputStream logger = new FileOutputStream(productsFile)) {

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
//                //if operation is successful
//                tempCode++;

//                System.out.println("File successfully updated");
            }

            //Ask if user want to add more records or not
            do {
                System.out.print("Do you want to add more records (y/n) : ");
                userInput = input.nextLine();
            } while (!(userInput.equalsIgnoreCase("Y") && userInput.equalsIgnoreCase("N")));
        } while (userInput.equalsIgnoreCase("Y"));
    }//End of method addItem

    /**
     * @implNote This method will delete product from the Product file
     */
    public void deleteItem() {
        int tempCode; //user input to search for code
        String buffer;
        Scanner input = new Scanner(System.in);

        System.out.println("Press any key to see the list...");
        input.nextLine();
        // TODO: 22-10-2021 fetch the list from the file
        System.out.print("Enter the code of product you want to delete : ");
        tempCode = input.nextInt();
        if(!itemFound(tempCode)){
            System.out.println("Record not found");
            return;
        }

        //Display the details for product code `tempCode`
        displayRecord(tempCode);

        //Confirmation from user block
        do{
            System.out.println("Do you want to delete this record (Y/N) : ");
            buffer = input.nextLine();
        }while(!(buffer.equalsIgnoreCase("Y") && buffer.equalsIgnoreCase("N")));

        if(buffer.equalsIgnoreCase("N")){
            System.out.println("The record is not deleted.");
            return;
        }
        deleteRecord(tempCode);
        System.out.println("Record deleted");
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

    private boolean itemFound(int tempCode) {
        return 0;
    }

    private int recordNumber(int tempCode) {
        return 0;
    }

    private void sort() {
    }

}
