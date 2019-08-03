package duc.vn.learning.design.patterns;

import java.util.Iterator;
import java.util.Stack;

public class CompositePattern {

}

abstract class MenuComponent {
	public void add(MenuComponent component) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(MenuComponent component) {
		throw new UnsupportedOperationException();
	}
	
	public MenuComponent addChild(int index) {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new UnsupportedOperationException();
	}
	
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	
	public double getPrice() {
		throw new UnsupportedOperationException();
	}
	
	public boolean isVegetarian() {
		throw new UnsupportedOperationException();
	}
	
	public void print() {
		throw new UnsupportedOperationException();
	}
	
	public Iterator<MenuComponent> createIterator() {
		throw new UnsupportedOperationException();
	}
}

class CompositeIterator implements Iterator<MenuComponent> {
	Stack<Iterator<MenuComponent>> stack = new Stack<>();
	
	public CompositeIterator(Iterator<MenuComponent> component) {
		stack.push(component);
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if (stack.empty()) { // check if stack is empty or not
			return false;
		}
		else {
			Iterator<MenuComponent> iterator = stack.peek(); // get the top component without popping
			if (!iterator.hasNext()) { // if iterator is a menu item aka leaf node => we pop the item
				stack.pop();
				return hasNext(); // here it does not return false because we need to iterate until stack empty
			}
			return true;
		}
	}

	@Override
	public MenuComponent next() {
		// TODO Auto-generated method stub
		if (hasNext()) {
			Iterator<MenuComponent> iterator = stack.peek(); // iterator here represents a node
			// here we dont check if iterator has next() or not cuz we have checked in the hasNext() method
			MenuComponent menu = iterator.next(); // we retrieve a child of the node
			stack.push(menu.createIterator()); // and then that child should be a node also => push in stack
			/*
			 * The point of returning MenuComponent is to print out the elements of that menu
			 * that menu could be a menu item, could be a menu containing menu items.
			 */
			return menu; 
		}
		else {
			return null;
		}
	}
	
}

class NullIterator implements Iterator<MenuComponent> { // this iterator is used for leaf nodes aka menu items

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MenuComponent next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
}