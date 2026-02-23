/**
 * This is Kunde
 */ 
public class Kunde implements Runnable {
  private final String name;
  private final Counter counter;
  private Kassa kassa;

  public Kunde(String name, Counter counter, Kassa kassa) {
    this.name = name;
    this.counter = counter;
    this.kassa = kassa;
  }

  @Override
  public void run() {
    System.out.println("Kunde " + name + " betrifft den Laden.");

    Bestellung meineBestellung = counter.bestellen(this.name);
    System.out.println("Kunde " + name + " hat bestellt: Ticket #" + meineBestellung.getTicketNr());

    System.out.println("Kunde " + name + " wartet auf Nummer " + meineBestellung.getTicketNr());

    kassa.abholen(meineBestellung);

    System.out.println("Kunde " + name + " hat sein Essen und verlässt den Laden.");
  }
}
