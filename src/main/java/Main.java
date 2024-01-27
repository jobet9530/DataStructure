import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> items = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Update");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addItem(items, scanner);
                case 2 -> searchItems(items, scanner);
                case 3 -> deleteItem(items, scanner);
                case 4 -> displayItems(items);
                case 5 -> updateItem(items, scanner);
                case 0 -> {
                    exitProgram();
                    scanner.close();
                }
                default -> invalidChoice();
            }
        }
    }

    private static void addItem(Map<String, Integer> items, Scanner scanner) {
        System.out.println("Enter item name:");
        String name = scanner.next();
        System.out.println("Enter item price:");
        int price = scanner.nextInt();

        items.put(name, price);
        System.out.println(name + " added successfully");
        System.out.println("Item price: " + price);
    }

    private static void searchItems(Map<String, Integer> items, Scanner scanner) {
        System.out.println("Enter item name to search:");
        String name = scanner.next();

        if (name.isEmpty()) {
            System.out.println("Please enter the name of the item.");
        } else {
            if (items.containsKey(name)) {
                System.out.println("Item found");
                System.out.println("Item name: " + name + ", Item price: " + items.get(name));
            } else {
                System.out.println("Item not found");
            }
        }
    }

    private static void deleteItem(Map<String, Integer> items, Scanner scanner) {
        System.out.println("Enter item name to delete:");
        String name = scanner.next();

        if (name.isEmpty()) {
            System.out.println("Please enter the name of the item.");
        } else {
            if (items.containsKey(name)) {
                items.remove(name);
                System.out.println("Item deleted successfully");
            } else {
                System.out.println("Item not found");
            }
        }
    }

    private static void displayItems(Map<String, Integer> items) {
        if (items.isEmpty()) {
            System.out.println("No items in the list");
        } else {
            System.out.println("Items in the list");
            for (Map.Entry<String, Integer> item : items.entrySet()) {
                System.out.println("Item name: " + item.getKey() + ", Item price: " + item.getValue());
            }
        }
    }

    private static void updateItem(Map<String, Integer> items, Scanner scanner) {
        System.out.println("Enter item name to update:");
        String name = scanner.next();

        if (name.isEmpty()) {
            System.out.println("Please enter the name of the item.");
        } else {
            if (items.containsKey(name)) {
                System.out.println("Enter the new item price:");
                int price = scanner.nextInt();
                items.put(name, price);
                System.out.println("Item updated successfully");
                System.out.println("Item name: " + name + ", New item price: " + price);
            } else {
                System.out.println("Item not found");
            }
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting program. Goodbye!");
    }

    private static void invalidChoice() {
        System.out.println("Invalid choice. Please choose a valid option.");
    }
}