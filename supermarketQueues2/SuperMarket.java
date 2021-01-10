package supermarketQueues2;


public class SuperMarket {
  
  private MeasureablePersonQueue queueOne = new SuperMarketQueue();
  
  public static void main(String[] args) {
      
      SuperMarket asda = new SuperMarket();
      asda.launch();
      
  }
  
  public void launch() {
      for (int i = 0; i < 7; i++) {
          Person person = new Person(i);
          this.addPerson(person);
          int n = queueOne.getLength();
          System.out.println("The queue at ASDA is " + n +
                             " People long.");
      }
      this.servePerson();
      System.out.println("The queue at ASDA is " + (queueOne.getLength()) +
                             " People long.");
  }
  
  public void addPerson(Person person) {
      this.queueOne.insert(person);
  }
  
  public Person servePerson() {
      return this.queueOne.retrieve();
  }

}
