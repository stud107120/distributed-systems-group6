package Assignment1;

/**
 * Assignment1 class containing implementations for simple threads
 * and thread synchronization demonstrations.
 */
public class Assignment1 {

    /**
     * A Runnable task that outputs numbered messages to the console
     * to demonstrate simple thread interleaving.
     */
    public static class ConsoleCounter implements Runnable {
        int n;
        int threadNumber;

        /**
         * Constructs a ConsoleCounter task.
         *
         * @param num          the number of iterations to print
         * @param threadNumber the identifier for the thread
         */
        public ConsoleCounter(int num, int threadNumber) {
            this.n = num;
            this.threadNumber = threadNumber;
        }

        /**
         * Executes the printing loop, outputting the thread number and current index.
         */
        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                System.out.println("T" + threadNumber + " N= " + i);
            }
        }
    }

    /**
     * Creates and starts two concurrent threads to observe output interleaving
     */
    public void twoThreads() {
        //Simple Thread Programs
        System.out.println("Simple Thread Programs!");
        new Thread(new ConsoleCounter(100, 1)).start();
        new Thread(new ConsoleCounter(100, 2)).start();

    }

    /**
     * Demonstrates thread interference using an un-synchronized counter,
     * and subsequently demonstrates how synchronization solves the issue
     */
    public void Synchronization() {
        System.out.println("--- 1. Testing Un-synchronized Counter (Interference) ---");
        Counter counter = new Counter();

        // Thread 1 increments 50,000 times
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter.increment();
            }
        });

        // Thread 2 decrements 50,000 times
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter.decrement();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join(); // Wait for threads to complete
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Due to thread interference, this will rarely be 0!
        System.out.println("Final Un-synchronized Value (Expected: 0): " + counter.value());


        System.out.println("\n--- 2. Testing Synchronized Counter ---");
        SynchronizedCounter syncCounter = new SynchronizedCounter();

        Thread st1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                syncCounter.increment();
            }
        });

        Thread st2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                syncCounter.decrement();
            }
        });

        st1.start();
        st2.start();

        try {
            st1.join();
            st2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Thanks to synchronized methods, this will consistently and reliably be 0!
        System.out.println("Final Synchronized Value (Expected: 0): " + syncCounter.value());
    }

    /**
     * An un-synchronized counter implementation susceptible to thread interference
     */
    static class Counter {
        private int c = 0;

        /**
         * Increments the counter value using a read-modify-write sequence
         */
        public void increment() {
            int b = c;
            b = b + 1;
            c = b;
        }

        /**
         * Decrements the counter value using a read-modify-write sequence
         */
        public void decrement() {
            int d = c;
            d = d - 1;
            c = d;
        }

        /**
         * Returns the current counter value.
         *
         * @return the current value of c
         */
        public int value() {
            return c;
        }
    }

    /**
     * A thread-safe counter implementation using synchronized methods
     * to prevent thread interference.
     */
    static class SynchronizedCounter {
        private int c = 0;

        /**
         * Safely increments the counter value using mutual exclusion
         */
        public synchronized void increment() {
            int b = c;
            b = b + 1;
            c = b;
        }

        /**
         * Safely decrements the counter value using mutual exclusion
         */
        public synchronized void decrement() {
            int d = c;
            d = d - 1;
            c = d;
        }

        /**
         * Returns the current counter value safely
         *
         * @return the current value of c
         */
        public synchronized int value() {
            return c;
        }
    }
}