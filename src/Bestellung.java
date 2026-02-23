/**
 * This is Bestellung
 * Lebendige Hose
 */ 
public class Bestellung {
  private final int ticketNr;
  private final String name;

  public Bestellung (int ticketNr, String name){
    this.ticketNr = ticketNr;
    this.name = name;
  }

  public int getTicketNr(){
    return this.ticketNr;
  }

  public String getName(){
    return this.name;
  }

}
