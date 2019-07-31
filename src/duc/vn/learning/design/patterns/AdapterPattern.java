package duc.vn.learning.design.patterns;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Random;

public class AdapterPattern {
	public static void main(String[] args) {
		Ducks adapter = new TurkeyAdapter(new WildTurkey());
		adapter.fly();
		adapter.quack();
	}
}

interface Ducks {
	void fly();
	void quack();
}

class MallardDuck implements Ducks {

	@Override
	public void fly() {
		System.out.println("Im flying");
	}

	@Override
	public void quack() {
		System.out.println("quack !");
	}
	
}

interface Turkey {
	void gobble();
	void fly();
}

class WildTurkey implements Turkey {

	@Override
	public void gobble() {
		System.out.println("gobble gobble");
	}

	@Override
	public void fly() {
		System.out.println("Im flying a short distance");
	}
	
}

/*
 *  this adapter will use composition with Turkey object. Even though we have fly() and quack(),
 *  inside the implementation, we will use gobble() and fly() methods of Turkey object
 */

class TurkeyAdapter implements Ducks {
	private Turkey turkey;
	
	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			turkey.fly();
		}
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		turkey.gobble();
	}
	
}

class DuckAdapter implements Turkey {
	private Ducks duck;
	
	public DuckAdapter(Ducks duck) {
		this.duck = duck;
	}
	@Override
	public void gobble() {
		// TODO Auto-generated method stub
		duck.quack();
	}

	@Override
	public void fly() {
		Random rand = new Random();
		if (rand.nextInt(5) == 0) {
			duck.fly();
		}
	}
	
}

class ArrayListAdapter implements Enumeration<Object> {
	Iterator<?> iterator;
	
	@Override
	public boolean hasMoreElements() {
		// TODO Auto-generated method stub
		return iterator.hasNext();
	}

	@Override
	public Object nextElement() {
		// TODO Auto-generated method stub
		return iterator.next();
	}
	
}