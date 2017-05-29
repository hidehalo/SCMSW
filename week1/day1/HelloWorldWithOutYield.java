public class HelloWorldWithOutYield {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread() {
      public void run() {
        System.out.println("Hello from new thread");
      }
    };
    thread.start();
    System.out.println("Hello from main thread");
  }
};
