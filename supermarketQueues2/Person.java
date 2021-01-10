package supermarketQueues2;

public class Person {
    
    private int position;
    
    public Person next;
    public Person prev;
    
    public Person (int position) {
        this.position = position;
        this.next = null;
        this.prev = null;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    
    public Person getNext() {
        return next;
    }
    public Person getPrev() {
        return prev;
    }
    public int getPosition() {
        return position;
    }
}
