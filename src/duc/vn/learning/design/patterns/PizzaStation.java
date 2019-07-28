package duc.vn.learning.design.patterns;

/*
 * Applying Factory Pattern
 */
public class PizzaStation {
	public static void main(String[] args) {
		PizzaStore pizzaStore = new NYPizzaStore();
		Pizza pizza = pizzaStore.orderPizza("Pepperoni");
		System.out.println("pizza name: " + pizza.getName());
	}
}

/*
 * This is the top component, where the methods should be either abstract or applied to all subclasses
 * Here orderPizza class is applied identically, so we can safely implement it
 */
abstract class PizzaStore {
	abstract Pizza createPizza(String type);
	public Pizza orderPizza(String type) {
		Pizza pizza;
		
		pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
}

/*
 * Different concrete store classes are created, and which one is called depends on the user
 * For example, if user buys in NY, then we will instantiate PizzaStore = new NYPizzaStore();
 * From this, createPizza will be applied in NYPizzaStore with different ingredients
 * When creating Pepperoni Pizza, we use ingredients from NY factory, not other cities.
 */
class NYPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		if (type.equals("Pepperoni")) {
			return new PepperoniPizza(new NYPizzaIngredientFactory(), "pepperoni pizza");
		}
		else {
			return null;
		}
	}
	
}

/*
 * The factory should create ingredient objects through implementing a factory interface
 */
class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Cheese createCheese() {
		// TODO Auto-generated method stub
		return new SpecificCheese();
	}

	@Override
	public Sauce createSauce() {
		// TODO Auto-generated method stub
		return new SpecificSauce();
	}
	
}

class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Cheese createCheese() {
		// TODO Auto-generated method stub
		return new SpecificCheese();
	}

	@Override
	public Sauce createSauce() {
		// TODO Auto-generated method stub
		return new SpecificSauce();
	}
	
}

class ChicagoPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		if (type.equals("Noob")) {
			return new NoobPizza();
		}
		return null;
	}
	
}

/*
 * This class is also a top component class, which should be abstract
 * Because there are a number of types of pizza, so other types will need to be concrete, 
 * and implement this class so they can have different ingredients and methods of doing differently
 * for example, one pizza can have a different way of baking (different timing)
 * Moreover, here Pizza does not care what type of ingredients it has, it knows the most basic types only.
 */
abstract class Pizza {
	String name;
	Cheese cheese;
	Sauce sauce;
	abstract void prepare();
	
	public void bake() {
		
	}
	
	public void cut() {
		
	}
	
	public void box() {
		
	}
	
	public String getName() {
		return name;
	}
}

/*
 * a specific pizza type as class 
 * By using instance variable of pizza factory, we do not depend on any concrete factory classes
 * In contrast, it will follow what factory the user chooses to put in which makes a lot of senses.
 */
class PepperoniPizza extends Pizza {
	private PizzaIngredientFactory ingredientFactory;
	public PepperoniPizza(PizzaIngredientFactory ingredientFactory, String name) {
		this.name = name;
		this.ingredientFactory = ingredientFactory;
	}

	@Override
	void prepare() {
		cheese = ingredientFactory.createCheese();
		sauce = ingredientFactory.createSauce();
	}
}

class NoobPizza extends Pizza {
	public NoobPizza() {
		this.name = "noob pizza";
	}

	@Override
	void prepare() {
	}
}

/*
 * An interface of pizza factory for abstraction.
 */
interface PizzaIngredientFactory {
	public Cheese createCheese();
	public Sauce createSauce();
}

/*
 * Each ingredient should also be an abstract class, because they may have different types, for example, of cheese
 * and for the design pattern too, where pizza may have a number of ingredients (should include all)
 * but when we create a specific one, we just need a subset of ingredients.
 * By doing so, if we need a specific type of ingredient, we create a concrete class that extends our
 * abstract class and then instantiate it.
 */
abstract class Cheese {
}

abstract class Sauce {
}

/*
 * a concrete class that extends our base Cheese class. This class will be instantiated, not Cheese
 * because we have different types of cheese, suitable in each cities, so we cannot just create a 
 * concrete class of Cheese !!
 */
class SpecificCheese extends Cheese{
	public SpecificCheese() {
		System.out.println("Cheese ingredient has been put");
	}
}

class SpecificSauce extends Sauce{
	public SpecificSauce() {
		System.out.println("Sauce ingredient has been put");
	}
}
