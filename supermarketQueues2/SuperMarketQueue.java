package supermarketQueues2;

public class SuperMarketQueue implements MeasureablePersonQueue {

	private Person[] personArray;

	private final int INITIAL_ARRAY_SIZE = 5;

	private int queueSize;

	public SuperMarketQueue () {
		personArray = new Person[INITIAL_ARRAY_SIZE];
		queueSize = 0;
	}

	public int getLength() {
		return this.queueSize;
	}


	public void insert(Person person) {
		if (isAlmostFull()) {
			doubleArraySize();
		}

		personArray[queueSize] = person;
		personArray[queueSize].setPosition(queueSize);
		queueSize++;
	}

	public Person retrieve() {
		if (personArray.length == 0) {
			return null;
		}
		Person result = personArray[0];
		for (int position = 0; position < personArray.length-1; position++) {
			if (personArray[position+1] != null) {
				personArray[position] = personArray[position+1];
				personArray[position].setPosition(personArray[position].getPosition()-1);
			}
		}
		queueSize--;

		if (isHalfSize()) {
			halveArraySize();
		}

		return result;
	}



	private boolean isAlmostFull() {
		if (personArray.length - queueSize < 2) {
			return true;
		}
		else {
			return false;
		}
	}

	private void doubleArraySize() {
		Person[] biggerArray = new Person[queueSize*2];
		for (int i = 0; i < queueSize; i++) {
			biggerArray[i] = this.personArray[i];
		}
		this.personArray = biggerArray;
	}


	private boolean isHalfSize() {
		if (queueSize < (personArray.length /2)) {
			return true;
		}
		else {
			return false;
		}
	}

	private void halveArraySize() {
		Person[] smallerArray = new Person[(personArray.length/2)];
		for (int i = 0; i < (personArray.length/2); i++) {
			smallerArray[i] = this.personArray[i];
		}
		this.personArray = smallerArray;
	}
}
