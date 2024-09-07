import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {
    private static Set<Item> items = new TreeSet<>();
    private static Map<String, Set<Item>> itemsByType = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String[] commandArgs = input.split(" ");
            String command = commandArgs[0];

            switch (command) {
                case "add":
                    add(commandArgs);
                    break;
                case "filter":
                    filter(commandArgs);
                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static void add(String[] commandArgs) {
        String name = commandArgs[1];
        double price = Double.parseDouble(commandArgs[2]);
        String type = commandArgs[3];

        Item item = new Item(name, price, type);

        if (items.contains(item)) {
            System.out.printf("Error: Item %s already exists%n", name);
        } else {
            items.add(item);

            if (!itemsByType.containsKey(type)) {
                itemsByType.put(type, new TreeSet<>());
            }

            itemsByType.get(type).add(item);

            System.out.printf("Ok: Item %s added successfully%n", name);
        }
    }

    private static void filter(String[] commandArgs) {
        String filter = commandArgs[2];
        switch (filter) {
            case "type":
                String type = commandArgs[3];
                if (itemsByType.containsKey(type)) {
                    StringBuilder result = new StringBuilder();
                    result.append("Ok: ");

                    if (!itemsByType.get(type).isEmpty()) {

                        String sortedItemsStr = itemsByType
                                .get(type)
                                .stream()
                                .sorted(Item::compareTo)
                                .limit(10)
                                .map(Item::toString)
                                .collect(Collectors.joining(", "));

                        result.append(sortedItemsStr);
                    }

                    System.out.println(result.toString().trim());
                } else {
                    System.out.printf("Error: Type %s does not exist%n", type);
                }
                break;
            case "price":
                String secondFilter = commandArgs[3];

                switch (secondFilter) {
                    case "from":
                        int length = commandArgs.length;
                        if (length == 7) {

                            double minPrice = Double.parseDouble(commandArgs[4]);
                            double maxPrice = Double.parseDouble(commandArgs[6]);

                            StringBuilder result = new StringBuilder();
                            result.append("Ok: ");

                            String filteredToPrice = items
                                    .stream()
                                    .filter(item -> item.price >= minPrice && item.price <= maxPrice)
                                    .sorted(Item::compareTo)
                                    .limit(10)
                                    .map(Item::toString)
                                    .collect(Collectors.joining(", "));

                            result.append(filteredToPrice);

                            System.out.println(result.toString().trim());

                        } else {
                            double minPrice = Double.parseDouble(commandArgs[4]);

                            StringBuilder result = new StringBuilder();
                            result.append("Ok: ");

                            String filteredToPrice = items
                                    .stream()
                                    .filter(item -> item.price >= minPrice)
                                    .sorted(Item::compareTo)
                                    .limit(10)
                                    .map(Item::toString)
                                    .collect(Collectors.joining(", "));

                            result.append(filteredToPrice);

                            System.out.println(result.toString().trim());
                        }
                        break;

                    case "to":
                        double maxPrice = Double.parseDouble(commandArgs[4]);
                        StringBuilder result = new StringBuilder();
                        result.append("Ok: ");

                        String filteredToPrice = items
                                .stream()
                                .filter(item -> item.price <= maxPrice)
                                .sorted(Item::compareTo)
                                .limit(10)
                                .map(Item::toString)
                                .collect(Collectors.joining(", "));

                        result.append(filteredToPrice);

                        System.out.println(result.toString().trim());

                        break;
                }
                break;
        }
    }

    private static class Item implements Comparable<Item> {
        private String name;
        private double price;
        private String type;

        public Item(String name, double price, String type) {
            this.name = name;
            this.price = price;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getType() {
            return type;
        }

        @Override
        public int compareTo(Item item) {
            return Comparator.comparing(Item::getPrice)
                    .thenComparing(Item::getName)
                    .thenComparing(Item::getType)
                    .compare(this, item);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Double.compare(price, item.price) == 0 && Objects.equals(name, item.name) && Objects.equals(type, item.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price, type);
        }

        @Override
        public String toString() {
            return String.format("%s(%.2f)", name, price);
        }
    }
}
