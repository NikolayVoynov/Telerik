import java.util.*;
import java.util.stream.Collectors;

public class CatchThemAll {

    private static List<Pokemon> ranklist = new ArrayList<>();
    private static Map<String, TreeSet<Pokemon>> pokemonsByType = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("end")) {
            String[] commandArgs = input.split(" ");
            String command = commandArgs[0];

            switch (command.toLowerCase()) {
                case "add":
                    add(commandArgs);
                    break;
                case "find":
                    String type = commandArgs[1];
                    find(type);
                    break;
                case "ranklist":
                    int start = Integer.parseInt(commandArgs[1]);
                    int end = Integer.parseInt(commandArgs[2]);
                    printRanklist(start, end);
                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static class Pokemon implements Comparable<Pokemon> {
        private String name;
        private String type;
        private int power;

        public Pokemon(String name, String type, int power) {
            this.name = name;
            this.type = type;
            this.power = power;
        }

        public String getName() {
            return name;
        }

        public int getPower() {
            return power;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pokemon pokemon = (Pokemon) o;
            return power == pokemon.power && Objects.equals(name, pokemon.name) && Objects.equals(type, pokemon.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type, power);
        }

        @Override
        public String toString() {
            return String.format("%s(%d)", name, power);
        }

        @Override
        public int compareTo(Pokemon pokemon) {
            return Comparator.comparing(Pokemon::getName)
                    .thenComparing(Comparator.comparing(Pokemon::getPower).reversed())
                    .compare(this, pokemon);
        }
    }

    private static void add(String[] commandArgs) {
        String name = commandArgs[1];
        String type = commandArgs[2];
        int power = Integer.parseInt(commandArgs[3]);
        int position = Integer.parseInt(commandArgs[4]);

        Pokemon newPokemon = new Pokemon(name, type, power);

        ranklist.add(position - 1, newPokemon);

        if (!pokemonsByType.containsKey(type)) {
            pokemonsByType.put(type, new TreeSet<>());
        }

        pokemonsByType.get(type).add(newPokemon);

        System.out.printf("Added pokemon %s to position %d%n", name, position);
    }

    private static void find(String type) {
        StringBuilder sbResult = new StringBuilder();

        String firstLine = String.format("Type %s: ", type);
        sbResult.append(firstLine);

        List<Pokemon> listPokemonsByType = new ArrayList<>();

        if (pokemonsByType.containsKey(type) && pokemonsByType.get(type) != null) {
            listPokemonsByType = pokemonsByType.get(type).stream().limit(5).collect(Collectors.toList());
        }

        for (Pokemon pokemon : listPokemonsByType) {
            sbResult.append(pokemon).append("; ");
        }

        if (!listPokemonsByType.isEmpty()) {
            sbResult.replace(sbResult.length() - 2, sbResult.length() - 1, "");
            System.out.println(sbResult.toString().trim());
            return;
        }

        System.out.println(sbResult.toString().trim());
    }

    private static void printRanklist(int start, int end) {
        StringBuilder result = new StringBuilder();

        for (int i = start - 1; i < end; i++) {
            result.append(String.format("%d. %s; ", i + 1, ranklist.get(i)));
        }
        result.replace(result.length() - 2, result.length() - 1, "");

        System.out.println(result.toString().trim());
    }

}
