public class ThreadPractice {

    public static void main(String[] args) {
        String process1 = "Welcome to CSTAD";
        String process2 = "Don't give up with this opportunity, do your best first";
        String process3 = "Downloading";
        int sleep = 300;

        Thread thread1 = new Thread(() -> {
            Greeting.print(process1, sleep);
        });


        Thread thread2 = new Thread(() -> {
            try {
                thread1.join(); // Wait for thread1 to finish
                Greeting.printStars(sleep); // Print stars

                Greeting.print(process2, sleep);
                Greeting.printMinus(sleep); // Print stars
            } catch (InterruptedException ex) {
                System.err.println("Error waiting for thread1: " + ex.getMessage());
            }
        });


        Thread thread3 = new Thread(() -> {
            try {
                thread2.join(); // Wait for thread2 to finish
                Greeting.printProgress(process3, sleep);
            } catch (InterruptedException ex) {
                System.err.println("Error waiting for thread2: " + ex.getMessage());
            }
        });

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Greeting {
    public static void print(String str, int sleep) {

        for (char c : str.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(sleep); // Sleep for the specified duration
            } catch (InterruptedException e) {
                // Log or handle the interruption gracefully
                System.err.println("Interrupted while printing: " + str);
            }
        }
        System.out.println();
    }

    public static void printProgress(String str, int sleep) {
        System.out.print(str);
        for (int i = 0; i < 30; i++) {
            System.out.print(".");
            try {
                Thread.sleep(sleep); // Sleep for the specified duration
            } catch (InterruptedException e) {
                // Log or handle the interruption gracefully
                System.err.println("Interrupted while printing progress: " + str);
            }
        }
        System.out.println(" completed 100%.");
    }
    public static void printStars(int sleep) {
        for (int i = 0; i < 60; i++) {
            System.out.print("*");
            try {
                Thread.sleep(sleep); // Sleep for the specified duration
            } catch (InterruptedException e) {
                // Log or handle the interruption gracefully
                System.err.println("Interrupted while printing stars");
            }
        }
        System.out.println(); // Move to the next line after printing stars
    }
    public static void printMinus(int sleep) {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
            try {
                Thread.sleep(sleep); // Sleep for the specified duration
            } catch (InterruptedException e) {
                // Log or handle the interruption gracefully
                System.err.println("Interrupted while printing stars");
            }
        }
        System.out.println(); // Move to the next line after printing stars
    }
}
