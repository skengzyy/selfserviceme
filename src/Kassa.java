import java.util.HashSet;
import java.util.Set;
/**
 * This is Kassa
 */ 
public class Kassa {
  private final Set<Integer> fertigeBestellungen = new HashSet<>();

  public synchronized void abgeben(Bestellung b) {

    fertigeBestellungen.add(b.getTicketNr());
    System.out.println(">>> KASSA: Bestellung #" + b.getTicketNr() + " für " + b.getName() + " ist abholbereit!");
    this.notifyAll();

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      //TODO: handle exception
      Thread.currentThread().interrupt();
    }
  }

  public synchronized void abholen(Bestellung b) {

    int meineNummer = b.getTicketNr();

    while (!fertigeBestellungen.contains(meineNummer)) {
      try {
        System.out.println("Kunde " + b.getName() + " wartet passiv auf #" + meineNummer);
        this.wait();
      } catch (InterruptedException e) {
        //TODO: handle exception
        Thread.currentThread().interrupt();
        return;
      }
    }

    fertigeBestellungen.remove(meineNummer);
    System.out.println("Kunde "+ b.getName() + " nimmt Bestellung #" + meineNummer + " mit.");
  }
}
