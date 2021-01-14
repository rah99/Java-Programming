package producer_Consumer;

/**
 * Abstract Producer implementation
 * 
 * @author mvanbesien (mvaawl@gmail.com)
 *
 * @param <O>
 *            type of the objects produced
 */
public abstract class Producer<O> implements Runnable {

	/**
	 * Instance of buffer to produce to from
	 */
	private Buffer<O> buffer;

	/**
	 * Sets buffer instance
	 * 
	 * @param buffer
	 * @return this
	 */
	final Producer<O> setBuffer(Buffer<O> pipe) {
		if (this.buffer == null) {
			this.buffer = pipe;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (this.buffer.isProductionAlive()) {
			this.buffer.push(this.produce());
		}
	}

	/**
	 * Produces one object
	 * 
	 * @return
	 */
	protected abstract O produce();

}
