import java.util.concurrent.Semaphore;

class Main {
    public static void main(String[] args) {
        Bus_stop busStop = new Bus_stop(3);
        for (int i = 0; i <= 4; i++) {
            Bus bus = new Bus("Автобус "+ (i+1), busStop);
            bus.start();
        }
    }
}
class Bus_stop{
    private Semaphore semaphore;
    public Bus_stop(int busesAllowed) {
        semaphore = new Semaphore(busesAllowed, true);
    }
    public void busArrives(String busName) {
        try {
            semaphore.acquire();
            System.out.println(busName + " прибыл на остановку.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(busName + " уходит с остановки.");
            semaphore.release();
        }
    }
}
class Bus extends Thread{
    private String busName;
    private Bus_stop stop;
    public Bus(String busName, Bus_stop stop) {
        this.busName = busName;
        this.stop = stop;
    }
    @Override
    public void run() {
        stop.busArrives(busName);
    }
}