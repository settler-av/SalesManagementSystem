package com.company.Menu;

import com.company.AccountContainer.Account;
import com.company.ProductContainer.Product;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public void mainMenu() {
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("              S A L E S M A N A G E M E N T");
            System.out.println("1: PURCHASE PRODUCTS");
            System.out.println("2: LIST OF PRODUCTS");
            System.out.println("3: EDIT PRODUCTS FILE");
            System.out.println("4: BILLS REPORT");
            System.out.println("0: QUIT");
            System.out.print("Enter Your Choice : ");
            choice = input.nextInt();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            switch (choice) {
                case 1:
                    Product P = new Product();
                    P.purchase();
                    break;
                case 2:
                    Product giveList = new Product();
                    giveList.listOfItem();
                    break;
                case 3:
                    editMenu();
                    break;
                case 4:
                    Account A = new Account();
                    A.billList();
                    break;
                case 0:
                    System.out.println("Thank you for using");
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void editMenu() {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                      E D I T _ M E N U");
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("1: ADD PRODUCTS");
        System.out.println("2: MODIFY PRODUCTS");
        System.out.println("3: DELETE PRODUCTS");
        System.out.println("0: EXIT");
        System.out.print("Enter Choice : ");
        choice = input.nextInt();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (true) {
            switch (choice) {
                case 1:
                    Product P = new Product();
                    try {
                        P.addItem();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    Product giveList = new Product();
                    giveList.modifyItem();
                    break;
                case 3:
                    Product del = new Product();
                    del.deleteItem();
                    break;
                case 0:
                    System.out.println("Thank you for using");
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
