import java.util.List;
import java.util.ArrayList;
/**
 * This is the Main class 
 */ 
public class Main {
  public static void main(String[] args) {
    Counter counter = new Counter();
    Kassa kassa = new Kassa();

    Küchenkraft k1 = new Küchenkraft("Gordon", counter, kassa);
    Küchenkraft k2 = new Küchenkraft("Jamie", counter, kassa);

    Thread tKüche1 = new Thread(k1);
    Thread tKüche2 = new Thread(k2);

    tKüche1.start();
    tKüche2.start();

    List<Thread> kundenThreads = new ArrayList<>();
    String[] namen = {"Lukas", "Marie", "Elias", "Sara", "Tom"};

    for (String n : namen) {
      Kunde k = new Kunde(n, counter, kassa);
      Thread t = new Thread(k);
      kundenThreads.add(t);
      t.start();

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {}
    }

    for (Thread t : kundenThreads) {
      try {
        t.join();
      } catch (InterruptedException e) {}
    }

    System.out.println("--- Alle Kunden bedient. Feierabend! ---");
    k1.stopRunning();
    k2.stopRunning();

    tKüche1.interrupt();
    tKüche2.interrupt();
  }
}
