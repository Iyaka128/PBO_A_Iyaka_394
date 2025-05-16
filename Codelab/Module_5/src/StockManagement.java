import java.util.ArrayList;
import java.util.Iterator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StockManagement {
    public static void main(String[] args) {
        // ----- USING ARRAY TO INITIATE FIXED ITEMS -----
        Item[] initialItems = {
                new Item("BOOK", 30),
                new Item("PEN", 100)
        };

        // ----- USING ARRAYLIST FOR DYNAMIC ITEM MANAGEMENT -----
        ArrayList<Item> itemList = new ArrayList<>();

        // Copy initial array items into ArrayList
        for (Item item : initialItems) {
            itemList.add(item);
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n----- STOCK MANAGEMENT MENU -----");
            System.out.println("1. ADD NEW ITEM");
            System.out.println("2. DISPLAY ALL ITEMS");
            System.out.println("3. REDUCE ITEM STOCK");
            System.out.println("0. EXIT");
            System.out.print("CHOOSE OPTION: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();
                        System.out.print("Initial stock: ");
                        try {
                            int stock = scanner.nextInt();
                            itemList.add(new Item(name, stock));
                            System.out.println("Item '" + name + "' successfully added.");
                        } catch (InputMismatchException e) {
                            System.out.println("Stock input must be a number!");
                            scanner.nextLine(); // clear buffer
                        }
                        break;

                    case 2:
                        if (itemList.isEmpty()) {
                            System.out.println("Item stock is empty.");
                        } else {
                            System.out.println("----- ITEM LIST (STOCK) -----");
                            Iterator<Item> iterator = itemList.iterator();
                            int index = 0;
                            while (iterator.hasNext()) {
                                Item item = iterator.next();
                                System.out.println(index + ". Name: " + item.getName() + ", Stock: " + item.getStock());
                                index++;
                            }
                        }
                        break;

                    case 3:
                        System.out.println("----- REDUCE ITEM STOCK (SELECT TO REDUCE STOCK) -----");
                        try {
                            for (int i = 0; i < itemList.size(); i++) {
                                Item item = itemList.get(i);
                                System.out.println(i + ". Name: " + item.getName() + ", Stock: " + item.getStock());
                            }

                            System.out.print("Enter item index number: ");
                            int index = scanner.nextInt();

                            System.out.print("Enter the amount of stock to reduce: ");
                            int amount = scanner.nextInt();

                            Item item = itemList.get(index);
                            if (amount > item.getStock()) {
                                throw new InsufficientStockException("Stock for " + item.getName() + " is only " + item.getStock() + " left.");
                            }

                            item.setStock(item.getStock() - amount);
                            System.out.println(item.getName() + " successfully reduced. Remaining stock: " + item.getStock());

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter numbers only.");
                            scanner.nextLine(); // clear buffer
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid index number!");
                        } catch (InsufficientStockException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Program terminated. Thank you!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Try again!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid option! Please enter a number.");
                scanner.nextLine(); // clear buffer
            }
        }
    }
}
