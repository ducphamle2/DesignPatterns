package duc.vn.learning.design.patterns;

import java.util.ArrayList;
import java.util.Iterator;

// this pattern is implemented in combination with CompositePattern.
public class IteratorPattern {
	public static void main(String[] args) {
		MenuComponent breakfastMenu = new Menu("Noob", "this is a noob menu");
		MenuComponent dinerMenu = new Menu("Dube", "Hong Anh");
		
		MenuComponent rootMenu = new Menu("root menu", "root menu");
		rootMenu.add(breakfastMenu);
		rootMenu.add(dinerMenu);
		
		MenuComponent dessertMenu = new Menu("Dessert", "Dessert");
		dinerMenu.add(dessertMenu);
		dinerMenu.add(new MenuItem("Item", "Item", true, 1.59));
		
		Waitress waitress = new Waitress(rootMenu);
		waitress.printMenu();
	}
}

class MenuItem extends MenuComponent {
	Iterator<MenuComponent> iterator = null;
	
	String name;
	String description;
	boolean vegetarian;
	double price;
	
	public MenuItem(String name, String description, boolean vegetarian, double price) {
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
		iterator = new NullIterator(); // here we use null iterator because menu item is leaf node
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public double getPrice() {
		return price;
	}
	
	public void print() {
		System.out.println(" " + getName());
		if (isVegetarian()) {
			System.out.println("{v}");
		}
		System.out.println(", " + getPrice());
		System.out.println("    -- " + getDescription());
	}
}

class Menu extends MenuComponent {
	ArrayList<MenuComponent> menu;
	String name;
	String description;
	Iterator<MenuComponent> iterator = null; // This exists because a menu may contain submenus and / or menu items
	public Menu(String name, String description) {
		menu = new ArrayList<>();
		this.name = name;
		this.description = description;
	}
	
	@Override
	public void add(MenuComponent component) {
		menu.add(component);
	}
	
	@Override
	public void remove(MenuComponent component) {
		menu.remove(component);
	}
	
	public MenuComponent getChild(int index) {
		return menu.get(index);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	public void print() {
		System.out.println("name: " + getName());
		System.out.println("description: " + getDescription());
		Iterator<MenuComponent> menuIterator = menu.iterator();
		// the iterator loop here actually traverses the entire tree structure, since 
		// if it is a node menu => print name, description and then print its leaf.
		while (menuIterator.hasNext()) {
			MenuComponent component = menuIterator.next();
			component.print();
		}
	}
	
	@Override
	public Iterator<MenuComponent> createIterator() {
		if (iterator == null) {
			// composite because it may contain menus or menu items
			iterator = new CompositeIterator(menu.iterator()); 
		}
		return iterator;
	}
}

class Waitress {
	MenuComponent allMenus;
	
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
	
	public void printMenu() {
		allMenus.print();
	}
}