import java.util.concurrent.*;

class Main {
  static int[] ticket;
  static boolean[] choosing;
  static int N = 10;

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

  static void unlock(int i) {
    ticket[i] = 0;
  }

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
