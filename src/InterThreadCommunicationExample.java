class SharedBuffer {
    int data;
    boolean empty = true;

    synchronized void  produce(int value) throws InterruptedException {
        while(!empty) {
            wait();
        }
        data = value;
        empty = false;
        System.out.println("Produced Value: " + data);
//        Thread.sleep(1000);
        notify();
    }

    synchronized void consume() throws InterruptedException {
        while (empty){
            wait();
        }
        empty = true;
        System.out.println("Consumed Value: " + data);
        notify();
    }
}

public class InterThreadCommunicationExample {
    public static void main(String[] args) {
        SharedBuffer sharedBuffer = new SharedBuffer();
//        SharedBuffer sharedBuffer2 = new SharedBuffer();

        Thread producerThread = new Thread(() -> {
            try {
                sharedBuffer.produce(10);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"ProducerThread");

        Thread consumerThread = new Thread(() -> {
            try {
                sharedBuffer.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "ConsumerThread");


        consumerThread.setDaemon(true);
        producerThread.start();
        consumerThread.start();
    }
}
