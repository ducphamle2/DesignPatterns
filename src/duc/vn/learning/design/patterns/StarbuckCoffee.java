package duc.vn.learning.design.patterns;

import duc.vn.learning.design.patterns.Beverage.Size;

/*
 * Applying Decorator Pattern
 */
public class StarbuckCoffee {
	public static void main(String[] args) {
		Beverage beverage = new Espresso(Size.Venti);
		beverage = new Milk(beverage);
		beverage = new Soy(beverage);
		System.out.println("beverage description: " + beverage.getDescription());
		System.out.println("beverage cost: " + beverage.cost());
	}
}

abstract class Beverage {
	
	public enum Size { Tall, Grande, Venti };
	Size size = Size.Tall;
	String description = "Unknown beverage";
	double cost = 0;
	public String getDescription() {
		return description;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public Size getSize() {
		return this.size;
	}
	
	public abstract double cost();
}

class Espresso extends Beverage {
	
	public Espresso(Size size) {
		description = "Espresso";
		cost = 2.0;
		this.setSize(size);
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return cost;
	}
}

class Tea extends Beverage {
	public Tea() {
		description = "Tea";
		cost = 3.0;
	}

	@Override
	public double cost() {
		return cost;
	}
}

class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "Dark Roast";
		cost = 4.0;
	}

	@Override
	public double cost() {
		return cost;
	}
}

abstract class CondimentDecorator extends Beverage{
	Beverage beverage;
	public abstract String getDescription();
}

class Milk extends CondimentDecorator implements CondimentType, CondimentCost {
	
	Milk(Beverage beverage) {
		cost = MILK_PRICE;
		description = MILK;
		this.setSize(beverage.getSize());
		this.beverage = beverage;
	}
	
	public Beverage setBeverage(Beverage beverage) {
		this.beverage = beverage;
		return this.beverage;
	}
	
	private Beverage getBeverage() {
		return this.beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return getBeverage().getDescription() + " + " + description;
	}

	@Override
	public double cost() {
		Size size = getBeverage().getSize();
		cost = size.equals(Size.Tall) ? 1.0 : size.equals(Size.Grande) ? 2.0 : 3.0;
		// TODO Auto-generated method stub
		return getBeverage().cost() + cost;
	}
}

interface CondimentType {
	String MILK = "milk";
	String SOY = "soy";
}

interface CondimentCost {
	double MILK_PRICE = 1;
	double SOY_PRICE = 1;
}

interface BeverageBehaviour {
	Beverage setBeverage(Beverage beverage);
}

class Soy extends CondimentDecorator implements CondimentType, CondimentCost {
	
	Soy(Beverage beverage) {
		cost = SOY_PRICE;
		description = SOY;
		this.beverage = beverage;
		this.setSize(beverage.getSize());
	}
	
	public Beverage setBeverage(Beverage beverage) {
		this.beverage = beverage;
		return this.beverage;
	}
	
	private Beverage getBeverage() {
		return this.beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return getBeverage().getDescription() + " + " + description;
	}

	@Override
	public double cost() {
		Size size = getBeverage().getSize();
		cost = size.equals(Size.Tall) ? 1.0 : size.equals(Size.Grande) ? 2.0 : 3.0;
		// TODO Auto-generated method stub
		return getBeverage().cost() + cost;
	}
}


