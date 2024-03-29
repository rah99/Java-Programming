package producer_consumer_semaphore;

public class ProducerAndConsumerTest {

	public static void main(String[] args) {
		// creating buffer queue
        MyQueue myQueue = new MyQueue();
       
        Producer producer = new Producer(myQueue);
        Consumer consumer = new Consumer(myQueue);
        Thread producerThread = new Thread(producer);
        
        // starting producer thread
        producerThread.start();
        
        Thread consumerThread = new Thread(consumer);
        // starting consumer thread
        consumerThread.start();
	}

}
