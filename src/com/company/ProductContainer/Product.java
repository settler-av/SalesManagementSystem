package com.company.ProductContainer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    }

    public void modifyItem() {

    }


    public void listOfItem() {

    }

    public void purchase() {
        System.out.println("Will purchase the product");
    }

    /**
     * {@link } https://stackoverflow.com/questions/686231/quickly-read-the-last-line-of-a-text-file
     *
     * @return the code of last added item
     * @implNote What tail does is zoom straight to the last character of the file, then steps backward, character by character,
     * recording what it sees until it finds a line break. Once it finds a line break, it breaks out of the loop. Reverses what
     * was recorded and throws it into a string and returns. 0xA is the new line and 0xD is the carriage return.
     * If your line endings are \r\n or crlf or some other "double newline style newline", then you will have to specify n*2 lines
     * to get the last n lines because it counts 2 lines for every line.
     */
    public int lastCode() {
        RandomAccessFile fileHandler = null;
        try {
            fileHandler = new RandomAccessFile(productsFile, "r");
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();

                if (readByte == 0xA) {
                    if (filePointer == fileLength) {
                        continue;
                    }
                    break;

                } else if (readByte == 0xD) {
                    if (filePointer == fileLength - 1) {
                        continue;
                    }
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
    }

    /**
     * @param line takes the perticular line from the file
     * @return the ArrayList of the filtered content
     * @implNote This function splits the content of the line retrieved by the file
     */
    ArrayList<String> splitContentOfLine(String line) {
        return new ArrayList<String>(List.of(line.split("_", 3)));
    }

    /**
     * {@link} https://stackoverflow.com/questions/35970237/how-to-delete-a-line-from-text-line-by-id-java
     *
     * @param tempCode the code of product that needs to be deleted
     * @implNote This method will delete particular entry from the file
     */
    private void deleteRecord(int tempCode) {
        // TODO: 22-10-2021 Write logic to delete the file
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
            boolean successful = tempFile.renameTo(productsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyRecord(int tempCode) {

    }

    private void displayRecord(int tempCode) {
        try {
            Scanner sc = new Scanner(productsFile);
            while (sc.hasNext()) {
                String lineOfFile = sc.nextLine();
                ArrayList<String> filteredData = splitContentOfLine(lineOfFile);
//                System.out.println(lineOfFile);
                if(Integer.parseInt(filteredData.get(0)) == tempCode){
                    System.out.println(lineOfFile);
                    break;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param tempCode the id of item
     * @return true when the record is found
     * @implNote will search for particular id in file and gives the status
     */
    private boolean itemFound(int tempCode) {
        boolean found = false;
        try {
            Scanner sc = new Scanner(productsFile);
            while (sc.hasNext()) {
                String lineOfFile = sc.nextLine();
                ArrayList<String> filteredData = splitContentOfLine(lineOfFile);
//                System.out.println(lineOfFile);
                if(Integer.parseInt(filteredData.get(0)) == tempCode){
                    found = true;
                    break;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return found;
        }

        private int recordNumber ( int tempCode){
            return 0;
        }

        private void sort () {
        }

        /**
         * @implNote This method sets the format of the data of object that needs to be stored in the file
         * @see "Product.dat"
         * @return the string of current state of object
         */
        @Override
        public String toString () {
            return "\n" + itemCode + "_" + itemName + "_" + itemCost + "_" + itemPrice;
        }
    }
