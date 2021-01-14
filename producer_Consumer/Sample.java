package producer_Consumer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Sample {

	private static class MyProducer extends Producer<String> {

		private AtomicInteger integer = new AtomicInteger(0);

		@Override
		protected String produce() {
			String integer = Thread.currentThread().getName() + "-" + this.integer.incrementAndGet();
			try {
				Thread.sleep(new Random().nextInt(3000));
			} catch (InterruptedException e) {
			}
			System.out.println("[" + Thread.currentThread().getName() + "] Produced " + integer);
			return integer;
		}

	}

	private static class MyConsumer extends Consumer<String> {

		@Override
		protected void consume(String integer) {
			try {
				Thread.sleep(new Random().nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[" + Thread.currentThread().getName() + "] \tConsumed " + integer);
		}

	}

	public static void main(String[] args) {
		Buffer<String> buffer = new Buffer<String>(50, 50, 500) {

			@Override
			protected Consumer<String> newConsumer() {
				return new MyConsumer();
			}

			@Override
			protected Producer<String> newProducer() {
				return new MyProducer();
			}

		}.open();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Stop requested.");
		buffer.close();
	}

}
