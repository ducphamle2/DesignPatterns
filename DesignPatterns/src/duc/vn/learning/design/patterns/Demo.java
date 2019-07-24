package duc.vn.learning.design.patterns;

interface FlyBehaviour {
	public void fly();
}

interface QuackBehaviour {
	public void quack();
}

class temp extends Duck {

	@Override
	public void display() {
		
	}
}

/*
 * Applying Strategy Pattern
 */
public class Demo {
	public static void main(String[] args) {
		Duck duck = new NoobDuck();
		
		duck.display();
		
		Duck ducky = new temp();
		
		ducky.setFlyBehaviour(new FlyNoWings());
		
		ducky.fly();
	}
}

abstract class Duck {
	private FlyBehaviour flyInstance;
	private QuackBehaviour quackInstance;
	
	Duck() {
		flyInstance = new FlyWithWings();
		quackInstance = new DuckQuacking();
	}
	
	public void fly() {
		flyInstance.fly();
	}
	
	public void quack() {
		quackInstance.quack();
	}
	
	public void setFlyBehaviour(FlyBehaviour flyInstance) {
		this.flyInstance = flyInstance;
	}
	
	public void setQuackBehaviour(QuackBehaviour quackInstance) {
		this.quackInstance = quackInstance;
	}
	
	public abstract void display();
}

class NoobDuck extends Duck {

	@Override
	public void display() {
		fly();
		quack();
	}
	
	@Override
	public void fly() {
		System.out.println("Noob duck");
	}
	
}

class FlyWithWings implements FlyBehaviour {

	@Override
	public void fly() {
		System.out.println("I am flying with my wings !!");
	}
}

class FlyNoWings implements FlyBehaviour {
	@Override
	public void fly() {
		System.out.println("No wings");
	}
}

class DuckQuacking implements QuackBehaviour {

	@Override
	public void quack() {
		System.out.println("QUACK QUACK !!");
	}
	
}
