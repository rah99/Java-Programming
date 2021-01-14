package producer_Consumer;

/**
 * Abstract Consumer implementation
 * 
 * @author mvanbesien (mvaawl@gmail.com)
 *
 * @param <O>
 *            type of the Objects consumed
 */
public abstract class Consumer<O> implements Runnable {

	/**
	 * Instance of buffer to consume from
	 */
	private Buffer<O> buffer;

	/**
	 * Sets buffer instance
	 * 
	 * @param buffer
	 * @return this
	 */
	final Consumer<O> setBuffer(Buffer<O> pipe) {
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
		while (this.buffer.isConsumptionAlive() || this.buffer.size() > 0) {
			O poll = this.buffer.pull();
			if (poll != null) {
				this.consume(poll);
			}
		}
	}

	/**
	 * Consumes one object
	 * 
	 * @param o
	 */
	protected abstract void consume(O object);

}
