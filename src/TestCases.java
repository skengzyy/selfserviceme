import java.util.ArrayList;
import java.util.List;

public class TestCases {

    public static void main(String[] args) {
        String testCase = args.length > 0 ? args[0] : "standard";

        try {
            switch (testCase) {
                case "no_customers":
                    testNoCustomers();
                    break;
                case "stress_test":
                    testStress();
                    break;
                default:
                    System.out.println("Unbekannter Testfall: " + testCase);
            }
        } catch (InterruptedException e) {
            System.err.println("Test unterbrochen!");
            Thread.currentThread().interrupt();
        }
    }

    public static void testNoCustomers() throws InterruptedException {
        System.out.println("--- START: Test No Customers ---");
        Counter c = new Counter();
        Kassa k = new Kassa();
        
        Küchenkraft gordon = new Küchenkraft("Gordon", c, k);
        Thread t = new Thread(gordon);
        t.start();

        Thread.sleep(1000);
        gordon.stopRunning();
        t.interrupt(); 
        
        t.join(2000);
        
        if (!t.isAlive()) {
            System.out.println("PASS: Küche hat sauber beendet.");
        } else {
            System.out.println("FAIL: Küche hängt!");
        }
    }

    public static void testStress() throws InterruptedException {
    System.out.println("--- START: Stress Test (20 Kunden) ---");
    Counter counter = new Counter();
    Kassa kassa = new Kassa(); // <--- Hier hieß es vorher nur "k"
    
    // Küchenkräfte
    List<Thread> kitchenThreads = new ArrayList<>();
    for (int i = 1; i <= 3; i++) {
        // Jetzt findet er "kassa"
        Thread t = new Thread(new Küchenkraft("Chef-" + i, counter, kassa));
        kitchenThreads.add(t);
        t.start();
    }

    // 20 Kunden
    List<Thread> customerThreads = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
        // Jetzt findet er "kassa" auch hier
        Thread t = new Thread(new Kunde("Kunde-" + i, counter, kassa));
        customerThreads.add(t);
        t.start();
    }

    // Warten bis alle Kunden fertig sind
    for (Thread t : customerThreads) {
        t.join();
    }

    System.out.println("--- Stress Test: Alle Kunden bedient! ---");
  }
}
