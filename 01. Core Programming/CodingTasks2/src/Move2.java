import java.util.Scanner;

public class Move2 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int index = Integer.parseInt(scanner.nextLine());

            String[] input = scanner.nextLine().split(",");
            int[] array = new int[input.length];

            for (int i = 0; i < input.length; i++) {
                array[i] = Integer.parseInt(input[i]);
            }

            long forwardSum = 0;
            long backwardSum = 0;

            String command = scanner.nextLine();

            while (!command.equals("exit")) {
                String[] commandArguments = command.split(" ");
                int steps = Integer.parseInt(commandArguments[0]);
                int size = Integer.parseInt(commandArguments[2]);

                if (commandArguments[1].equals("backwards")) {
                    size *= -1;
                }

                for (int i = 0; i < steps; i++) {
                    index += size;

                    if (index >= array.length) {
                        index = index % array.length;
                    }
                    if (index < 0) {
                        index = (array.length - (Math.abs(index) % array.length)) % array.length;
                    }

                    if (commandArguments[1].equals("forward")) {
                        forwardSum += array[index];
                    } else {
                        backwardSum += array[index];
                    }
                }

                command = scanner.nextLine();
            }

            System.out.printf("Forward: %d%n", forwardSum);
            System.out.printf("Backwards: %d", backwardSum);
        }
    }

