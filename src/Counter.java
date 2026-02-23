import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * This is Counter
 */ 
public class Counter {
  private final AtomicInteger ticketGenerator = new AtomicInteger(1);
  private final BlockingQueue<Bestellung> küchenQueue = new LinkedBlockingQueue<>();

  public Bestellung bestellen(String kundenName) {
    int nummer = ticketGenerator.getAndIncrement();
    Bestellung neueBestellung = new Bestellung(nummer, kundenName);

    try {
      küchenQueue.put(neueBestellung);
    } catch (InterruptedException e) {
      //TODO: handle exception
      Thread.currentThread().interrupt();
    }
    return neueBestellung;
  }

  public Bestellung nächsteBestellungHolen() throws InterruptedException {
    return küchenQueue.take();
  }
}
