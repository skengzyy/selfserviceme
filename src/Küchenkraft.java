/**
 * This is Küchenkraft
 */ 
public class Küchenkraft implements Runnable {
  private final String name;
  private final Counter counter;
  private final Kassa kassa;
  private volatile boolean running = true;

  public Küchenkraft(String name, Counter counter, Kassa kassa) {
    this.name = name;
    this.counter = counter;
    this.kassa = kassa;
  }
  
  public void stopRunning(){
    this.running = false;
  }

  @Override
  public void run() {
    System.out.println("Küchenkraft " + this.name + " ist bereit");

    while (running) {
      try {
        Bestellung b = counter.nächsteBestellungHolen();

        System.out.println("Küchenkraft " + name + " übernimmt #" + b.getTicketNr());

        Thread.sleep((long) (Math.random() *  2000 + 1000));

        kassa.abgeben(b);

      } catch (InterruptedException e) {
        //TODO: handle exception
        running = false;
      }
    }
    System.out.println("Küchenkraft " + name + " macht Feierabend.");
  }
}
