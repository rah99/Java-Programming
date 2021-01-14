package producer_Consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract Buffer Implementation.
 * 
 * Cornerstone of the Producer-Consumer pattern as it holds most of the logic of
 * the pattern (Fixed queue wrapping, Thread Management, Synchronization, ...).
 * 
 * @author mvanbesien (mvaawl@gmail.com)
 *
 * @param <O>
 *            Type of the Objects produced and consumed within this pattern
 */
public abstract class Buffer<O> {

	/**
	 * Number of consumers the buffer will handle
	 */
	private final int consumers;

	/**
	 * Number of produces the buffer with handle
	 */
	private final int producers;

	/**
	 * Effective queue
	 */
	private final ArrayBlockingQueue<O> queue;

	/**
	 * Thread pool that manages the producers
	 */
	private final ExecutorService producersExecutorService;

	/**
	 * Thread pool that manages the consumers
	 */
	private final ExecutorService consumersExecutorService;

	/**
	 * Set to true if the open() method has been called (to prevent multiple
	 * openings)
	 */
	private final AtomicBoolean opened = new AtomicBoolean(false);

	/**
	 * Set to true if the close() method has been called (to prevent multiple
	 * closings)
	 */
	private final AtomicBoolean stopped = new AtomicBoolean(false);

	/**
	 * Creates a new Buffer instance
	 * 
	 * @param producers
	 *            number of producers that will fill the buffer
	 * @param consumers
	 *            number of consumers that will empty the buffer
	 * @param queueSize
	 *            max number of elements that the buffer can contain
	 */
	public Buffer(int producers, int consumers, int queueSize) {
		this.producers = producers;
		this.consumers = consumers;
		this.queue = new ArrayBlockingQueue<O>(queueSize);
		this.consumersExecutorService = Executors.newFixedThreadPool(this.consumers);
		this.producersExecutorService = Executors.newFixedThreadPool(this.producers);
	}

	/**
	 * @return new consumer instance that will empty the buffer
	 */
	protected abstract Consumer<O> newConsumer();

	/**
	 * @return new producer instance that will fill the buffer
	 */
	protected abstract Producer<O> newProducer();

	/**
	 * Opens the buffer.
	 * 
	 * This starts the production consumption process and it internally starts
	 * the producers and consumers.
	 * 
	 * @return current instance (for fluent API usage)
	 */
	public final Buffer<O> open() {
		if (!this.opened.get()) {
			this.opened.set(true);
			try {
				for (int i = 0; i < this.consumers; i++) {
					this.consumersExecutorService.submit(newConsumer().setBuffer(this));
				}
				for (int i = 0; i < this.producers; i++) {
					this.producersExecutorService.submit(newProducer().setBuffer(this));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	/**
	 * Closes the buffer by launching a termination signal to the process.
	 * 
	 * Internally, it requests producers to stop consuming, and when all
	 * producers are stopped, it requests consumers to empty the stack and then
	 * stop.
	 */
	public final void close() {
		if (!this.stopped.get() && this.opened.get()) {
			this.stopped.set(true);
			// Stops the producers and wait for termination.
			this.producersExecutorService.shutdown();
			while (!this.producersExecutorService.isTerminated()) {
				try {
					this.producersExecutorService.awaitTermination(60, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
				}
			}
			// When done, stops the producers
			this.consumersExecutorService.shutdown();
			synchronized (this) {
				this.notifyAll();
			}
		}
	}

	/**
	 * @return true if the consumers are still consuming.
	 */
	public final boolean isConsumptionAlive() {
		return !this.consumersExecutorService.isShutdown();
	}

	/**
	 * @return true if the producers are still producing.
	 */
	public final boolean isProductionAlive() {
		return !this.producersExecutorService.isShutdown();
	}

	/**
	 * Pushes new element in the buffer. If full, this method will block until
	 * there is new space in the buffer
	 * 
	 * @param object
	 *            object to push
	 */
	final synchronized void push(O object) {
		if (this.queue.remainingCapacity() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.queue.offer(object);
		this.notifyAll();
	}

	/**
	 * Pulls new element from the buffer. If empty, this method will block until
	 * there are new elements in
	 * 
	 * @return object pulled from buffer
	 */
	final synchronized O pull() {
		if (this.queue.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
		return this.queue.poll();
	}

	/**
	 * @return number of elements waiting for consumption
	 */
	public final int size() {
		return this.queue.size();
	}
}
