package com.company.ProductContainer;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

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
     * @status Done
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
                isValid = validateName(itemName);
            } while (!isValid);
            System.out.println("Item Name (Confirmed) : " + itemName);

            //Block to get the cost of item
            do {
                isValid = true;
                System.out.println("Enter item cost to add in the menu");
                System.out.print("Item Cost : ");
                itemCostStr = input.nextLine();
                itemCost = parseDouble(itemCostStr);
                isValid = validateCost(itemCost);
            } while (!isValid);
            System.out.println("Item Cost (Confirmed): " + itemCost);

            //Block to get the selling price of item
            do {
                isValid = true;
                System.out.println("Enter item price to add in the menu");
                System.out.print("Item price : ");
                itemPriceStr = input.nextLine();
                itemPrice = parseDouble(itemPriceStr);
                isValid = validatePrice(itemPrice);
            } while (!isValid);
            System.out.println("Item Price (Confirmed): " + itemPrice);

            //Getting Confirmation from user
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            do {
                System.out.println("Do you want to save this record? (y/n) : ");
                userInput = input.nextLine();
            } while (!(userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N")));

            //If yes update the file product.dat
            if (userInput.equalsIgnoreCase("Y")) {
                itemCode = tempCode;
                //open the file update and close use try catch block
                try (FileWriter logger = new FileWriter(productsFile, true)) {
                    logger.append(toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //if operation is successful
                tempCode++;
                System.out.println("File successfully updated");
            }

            //Ask if user want to add more records or not
            do {
                System.out.print("Do you want to add more records (y/n) : ");

                userInput = input.nextLine();
                System.out.println("The user input is recorded as " + userInput);
            } while (!(userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N")));
            if (userInput.equalsIgnoreCase("n")) {
                System.out.println("Inside if \"N\"");
            }
            System.out.println("The user input 2 is recorded as " + userInput);
        } while (userInput.equalsIgnoreCase("Y"));
    }//End of method addItem

    /**
     * @implNote This method will delete product from the Product file
     * @status done
     */
    public void deleteItem() {
        int tempCode; //user input to search for code
        String buffer;
        Scanner input = new Scanner(System.in);

        System.out.println("Press any key to see the list...");
        input.nextLine();
        System.out.print("Enter the code of product you want to delete : ");
        tempCode = input.nextInt();
        if (!itemFound(tempCode)) {
            System.out.println("Record not found");
            return;
        }

        //Display the details for product code `tempCode`
        displayRecord(tempCode);

        //Confirmation from user block
        do {
            System.out.println("Do you want to delete this record (Y/N) : ");
            buffer = input.nextLine();
        } while (!(buffer.equalsIgnoreCase("Y") || buffer.equalsIgnoreCase("N")));

        if (buffer.equalsIgnoreCase("N")) {
            System.out.println("The record is not deleted.");
            return;
        }
        deleteRecord(tempCode);
        System.out.println("Record deleted");
    }//End of method delete item

    public void modifyItem() {
// TODO: 27-10-2021 Make class modify item
    }//End of method of modify item


    public void listOfItem() {

    }//End of method listOfItem

    public void purchase() {
        System.out.println("Will purchase the product");
    }//End of method purchase

    /**
     * {@link } https://stackoverflow.com/questions/686231/quickly-read-the-last-line-of-a-text-file
     *
     * @return the code of last added item
     * @status done
     * @implNote What tail does is zoom straight to the last character of the file, then steps backward, character by character,
     * recording what it sees until it finds a line break. Once it finds a line break, it breaks out of the loop. Reverses what
     * was recorded and throws it into a string and returns. 0xA is the new line and 0xD is the carriage return.
     * If your line endings are \r\n or crlf or some other "double newline style newline", then you will have to specify n*2 lines
     * to get the last n lines because it counts 2 lines for every line.
     */
    private int lastCode() {
        RandomAccessFile fileHandler = null;
        try {
            fileHandler = new RandomAccessFile(productsFile, "r");
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();
                if (readByte == 0xA) {
                    if (filePointer == fileLength)
                        continue;
                    break;
                } else if (readByte == 0xD) {
                    if (filePointer == fileLength - 1) continue;
                    break;
                }
                sb.append((char) readByte);
            }

            String lastLine = sb.reverse().toString();
            ArrayList<String> myList = splitContentOfLine(lastLine);
            return Integer.parseInt(myList.get(0));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (fileHandler != null)
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    /* ignore */
                }
        }
    }//End of method last code

    /**
     * @param line takes the perticular line from the file
     * @return the ArrayList of the filtered content
     * @implNote This function splits the content of the line retrieved by the file
     */
    @Contract("_ -> new")
    private @NotNull ArrayList<String> splitContentOfLine(@NotNull String line) {
        return new ArrayList<String>(List.of(line.split("_", 3)));
    }//End of method splitContentOfLine

    /**
     * {@link} https://stackoverflow.com/questions/35970237/how-to-delete-a-line-from-text-line-by-id-java
     *
     * @param tempCode the code of product that needs to be deleted
     * @status done
     * @implNote This method will delete particular entry from the file
     */
    private void deleteRecord(int tempCode) {
//        Open the old file for reading
//        Open a new (temporary) file for writing
//        Iterate over the lines in the old file (probably using a BufferedReader)
//        For each line, check if it matches what you are supposed to remove
//        If it matches, do nothing
//        If it doesn't match, write it to the temporary file
//        When done, close both files
//        Delete the old file
//        Rename the temporary file to the name of the original file
        try {
//            File productsFile = new File(this.productsFile);
            File tempFile = new File("src/com/company/ProductContainer/tempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(productsFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = Objects.toString(tempCode, null);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                String[] trimmLine = trimmedLine.split("_");
                String part1 = trimmLine[0];

                if (!part1.equals(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            //Delete the original file
            if (!productsFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(productsFile))
                System.out.println("Could not rename file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//End of Method deleteItem

    private void modifyRecord(int tempCode) {
        int recordNumber = recordNumber(tempCode); //
        boolean valid = false; //flag for validity of Record
        int tCode; //Act as temporary product id
        Scanner input = new Scanner(System.in);
        String userChoice; //Will be used for yes/no handler
        double t_ItemCost; //Acts as a temporary product cost
        double t_ItemPrice; // Acts as a temporary product price
        String t_ItemName; //Acts as a temporary product name

        //Show the record for verification
        displayRecord(tempCode);

        //Ask if they really want to change the code
        do {
            System.out.println("Change code?(Y/N) : ");
            userChoice = input.nextLine();
        } while (!(userChoice.equalsIgnoreCase("Y") || userChoice.equalsIgnoreCase("N")));
        //Block to change the item code
        while (userChoice.equalsIgnoreCase("y") && !valid) {
            valid = true;
            System.out.println("ENTER ITEM CODE TO ADD IN THE MENU");
            System.out.println("Enter item code: ");
            tCode = input.nextInt();
            if (tCode == 0)
                return;
            if (itemFound(tCode) && tCode != tempCode) {
                valid = false;
                System.out.println("The code is occupied");
            }
        }

        //Ask if they really want to change the name
        do {
            System.out.println("Change name?(Y/N) : ");
            userChoice = input.nextLine();
        } while (!(userChoice.equalsIgnoreCase("Y") || userChoice.equalsIgnoreCase("N")));
        //Block to change the item name
        valid = false;
        while (userChoice.equalsIgnoreCase("y") && !valid) {
            valid = true;
            System.out.println("ENTER ITEM NAME TO ADD IN THE MENU");
            System.out.println("Enter item name: ");
            itemName = input.nextLine();
            valid = validateName(itemName);
        }

        //Ask if they really want to change the cost
        do {
            System.out.println("Change cost?(Y/N) : ");
            userChoice = input.nextLine();
        } while (!(userChoice.equalsIgnoreCase("Y") || userChoice.equalsIgnoreCase("N")));
        //Block to change the itemCost
        valid = false;
        while (userChoice.equalsIgnoreCase("y") && !valid) {
            valid = true;
            System.out.println("ENTER ITEM COST TO ADD IN THE MENU");
            System.out.println("Enter item cost: ");
            itemCost = input.nextDouble();
            valid = validateCost(itemCost);
        }

        //Ask if they really want to change the price
        do {
            System.out.println("Change Price?(Y/N) : ");
            userChoice = input.nextLine();
        } while (!(userChoice.equalsIgnoreCase("Y") || userChoice.equalsIgnoreCase("N")));
        //Block to change the itemCost
        valid = false;
        while (userChoice.equalsIgnoreCase("y") && !valid) {
            valid = true;
            System.out.println("ENTER ITEM Price TO ADD IN THE MENU");
            System.out.println("Enter item Price: ");
            itemPrice = input.nextDouble();
            valid = validatePrice(itemPrice);
        }
        //Getting Confirmation from user
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        do {
            System.out.println("Do you want to save this record? (y/n) : ");
            userChoice = input.nextLine();
        } while (!(userChoice.equalsIgnoreCase("Y") || userChoice.equalsIgnoreCase("N")));
        // TODO: 27-10-2021 update specific record according to user choice ref(line 780)
    }//End of method modify item

    private void displayRecord(int tempCode) {
        try {
            Scanner sc = new Scanner(productsFile);
            while (sc.hasNext()) {
                String lineOfFile = sc.nextLine();
                ArrayList<String> filteredData = splitContentOfLine(lineOfFile);
//                System.out.println(lineOfFile);
                if (Integer.parseInt(filteredData.get(0)) == tempCode) {
                    System.out.println("ID: " + filteredData.get(0) +
                                       "Name: " + filteredData.get(1) +
                                       "Cost: " + filteredData.get(2) +
                                       "Price: " + filteredData.get(3));
                    break;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//End of method displayRecord

    /**
     * @param tempCode the id of item
     * @return true when the record is found
     * @implNote will search for particular id in file and gives the status of the product id whether the ID is present in the file or not
     * @status done
     */
    private boolean itemFound(int tempCode) {
        boolean found = false;
        try {
            try (Scanner sc = new Scanner(productsFile)) {
                while (sc.hasNext()) {
                    String lineOfFile = sc.nextLine();
                    ArrayList<String> filteredData = splitContentOfLine(lineOfFile);
                    //                System.out.println(lineOfFile);
                    if (Integer.parseInt(filteredData.get(0)) == tempCode) {
                        found = true;
                        break;
                    }
                }
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }//End of method itemFound

    private int recordNumber(int tempCode) {
        return 0;
    }//End of method record number

    /**
     * this function will sort the content of file using ID of product it will be useful to get the lastID
     *
     * @status done
     */
    private void sort() {
        ArrayList<Integer> productIDContainer = new ArrayList<Integer>();
        ArrayList<String> lineContentOfFile = new ArrayList<String>();
        //This block will store the contents of file in the arraylist line by line
        try (Scanner productFile = new Scanner(productsFile)) {
            while (productFile.hasNext()) {
                String lineOfFile = productFile.nextLine();
                lineContentOfFile.add(lineOfFile);
                ArrayList<String> filteredData = splitContentOfLine(lineOfFile);
                productIDContainer.add(Integer.parseInt(filteredData.get(0)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Processing the data of arrayList
        //Simple bubble sort (Not optimized solution)
        for (int i = 0; i < productIDContainer.size() - 1; i++)
            for (int j = 0; j < productIDContainer.size() - i - 1; j++)
                if (productIDContainer.get(j) > productIDContainer.get(j + 1)) {
                    // swap arr[j+1] and arr[j]
                    Collections.swap(productIDContainer, j, j + 1);
                    Collections.swap(lineContentOfFile, j, j + 1);
                }

        //Rewriting the whole File
        try {
            File tempFile = new File("src/com/company/ProductContainer/tempFile.dat");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            for (String s : lineContentOfFile) {
                writer.write(s + "\n");
            }

            writer.close();
            //Delete the original file
            if (!productsFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(productsFile))
                System.out.println("Could not rename file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//End of method sort

    /**
     * @param itemName the name of the item
     * @return boolean decision
     * @implNote Will validate the input given by the user
     */
    boolean validateName(String itemName) {
        this.itemName = itemName.toUpperCase();
        if (itemName.length() < 1 || itemName.length() > 20) {
            System.out.println("Range of name = 1 ... 20");
            return false;
        }
        return true;
    }

    /**
     * @param itemCost the cost of the item
     * @return boolean decision
     * @implNote Will validate the input given by the user
     */
    boolean validateCost(double itemCost) {
        if (itemCost < 1 || itemCost > 800) {
            System.out.println("Range of cost = 1 ... 800");
            return false;
        }
        return true;
    }

    /**
     * @param itemPrice the cost of the item
     * @return boolean decision
     * @implNote Will validate the input given by the user
     */
    boolean validatePrice(double itemPrice) {
        if (itemPrice < 1 || itemPrice > 800) {
            System.out.println("Range of price = " + itemCost + "... 1000");
            return false;
        }
        return true;
    }

    /**
     * @return the string of current state of object
     * @implNote This method sets the format of the data of object that needs to be stored in the file
     * @status done
     * @see "Product.dat"
     */
    @Override
    public String toString() {
        //Example 1_product_13.0_19.0
        return "\n" + itemCode + "_" + itemName + "_" + itemCost + "_" + itemPrice;
    }//End of method toString

}
