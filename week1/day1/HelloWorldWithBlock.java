public class HelloWorldWithBlock {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread() {
      public void run() {
        System.out.println("Hello from new thread");
      }
    };
    thread.start();
    Thread.sleep(1);
    System.out.println("Hello from main thread");
  }
};
