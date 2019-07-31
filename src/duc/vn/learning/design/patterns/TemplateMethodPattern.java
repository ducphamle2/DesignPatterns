package duc.vn.learning.design.patterns;

public class TemplateMethodPattern {

}

abstract class Coffee {
	void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		hook();
		addCondiment();
	}
	
	public void boilWater() {
		System.out.println("boil water");
	}
	
	public void pourInCup() {
		System.out.println("Pouring into cup");
	}
	
	abstract void brew();
	
	abstract void addCondiment();
	
	public void hook() {
		
	}
}

class FirstCoffee extends Coffee {

	@Override
	void brew() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void addCondiment() {
		// TODO Auto-generated method stub
		
	}
	
}