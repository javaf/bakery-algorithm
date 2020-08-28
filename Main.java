import java.util.concurrent.*;

// Multiple customers are planning to eat at a
// bakery. For each item, the pick a "latest"
// ticket and wait for their turn to arrive. When
// their turn comes, they place the order, clear
// their ticket, goto eating. When they are hungry
// again, the process repeats.

// Each customer picks a maximum ticket, but since
// this maximum can be the same for two people
// more preference is given to customer with
// smaller id. Also, if picking maximum is not
// made atomic, it is possible for two customers
// to end up ordering at the same time, so a
// "choosing" status is present for each customer.

class Main {
  static int[] ticket;
  static boolean[] choosing;
  static int N = 10;
  // ticket: ticket no. of each customer
  // choosing: tells if customer is chosing ticket
  // N: no. of customers

  // Each customer is here to eat a lot, and there
  // is only on booth:
  // 1. Places an order (see lock)
  // 2. Eats baked item at table
  // ... Done, but still hungry!
  static void customer(int i) {
    new Thread(() -> {
      try {
      while(true) {
        lock(i);
        log(i+": placing order");
        unlock(i);
        log(i+": eating");
        Thread.sleep((long)(Math.random()*1000));
        log(i+": done");
      }
      }
      catch(InterruptedException e) {}
    }).start();
  }

  // To avoid crowding at booth, all customers
  // follow this process:
  // 1. Pick latest ticket
  // 2. For all other customers:
  //   a. Wait if they are choosing
  //   b. Wait if they have early ticket, or
  //   c. Wait if they came before you
  // 3. Goto the booth and place order
  static void lock(int i) {
    try {
    log(i+": choosing ticket");
    choosing[i] = true;
    ticket[i] = max(ticket, 0) + 1;
    choosing[i] = false;
    log(i+": got ticket="+ticket[i]);
    log(i+": waiting for turn");
    for(int p=0; p<N; p++) {
      while(choosing[p]) Thread.sleep(10);
      while(
        (ticket[i] > 0) &&
        (ticket[p] < ticket[i] ||
        (ticket[p] == ticket[i] && p < i)))
        Thread.sleep(10);
    }
    }
    catch(InterruptedException e) {}
  }

  // When done placing the order:
  // 1. Throw away your ticket
  static void unlock(int i) {
    ticket[i] = 0;
  }

  // 1. No customer has a ticket
  // 2. No customer is choosing
  // 3. All customers seated
  public static void main(String[] args) {
    ticket = new int[N];
    choosing = new boolean[N];
    for(int i=0; i<N; i++)
      customer(i);
  }
  static int max(int[] x, int vd) {
    int a = vd;
    for(int i=0; i<x.length; i++)
      a = Math.max(a, x[i]);
    return a;
  }
  static void log(String x) {
    System.out.println(x);
  }
}
