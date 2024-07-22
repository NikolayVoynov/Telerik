import java.util.Scanner;

public class Move {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int currentIndex = Integer.parseInt(scanner.nextLine());
        String[] inputArr = scanner.nextLine().split(",");

        int[] numbers = new int[inputArr.length];


        for (int i = 0; i < inputArr.length; i++) {
            numbers[i] = Integer.parseInt(inputArr[i]);
        }

        long forwardSum = 0;
        long backwardsSum = 0;

        String input = scanner.nextLine();

        while (!input.equals("exit")) {
            String[] token = input.split("\\s+");
            int numberOfSteps = Integer.parseInt(token[0]);
            String command = token[1];
            int lengthOfStep = Integer.parseInt(token[2]);

            switch (command) {
                case "forward":
                    for (int i = 0; i < numberOfSteps; i++) {
                        currentIndex += lengthOfStep;

                        if (currentIndex >= numbers.length) {
                            currentIndex = currentIndex % numbers.length;
                        }

                        forwardSum += numbers[currentIndex];
                    }
                    break;
                case "backwards":
                    lengthOfStep *= -1;
                    for (int i = 0; i < numberOfSteps; i++) {
                        currentIndex += lengthOfStep;

                        if (currentIndex < 0) {
                            currentIndex = (numbers.length - (Math.abs(currentIndex) % numbers.length)) % numbers.length;
                        }

                        backwardsSum += numbers[currentIndex];
                    }
                    break;
            }

            input = scanner.nextLine();
        }

        System.out.printf("Forward: %d%n", forwardSum);
        System.out.printf("Backwards: %d", backwardsSum);
    }

}
