package duc.vn.learning.design.patterns;

import java.util.ArrayList;

public class CommandPattern {
	public static void main(String[] args) {
		CeilingFanHighCommand command = new CeilingFanHighCommand(new CeilingFan());
		command.executeHigh();
		System.out.println("speed now: " + command.getFan().getSpeed());
		command.undo();
		System.out.println("speed after undo: " + command.getFan().getSpeed());
		command.executeHigh();
		command.executeLow();
		command.executeLow();
		command.executeHigh();
		command.undo();
		command.undo();
		command.undo();
		command.undo();
		command.undo();
		command.executeHigh();
		command.executeLow();
		command.undo();
		command.undo();
		System.out.println("final speed: " + command.getFan().getSpeed());
	}
}

class CeilingFan {
	
	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;
	private int speed;
	private int prevSpeed;
	ArrayList<Integer> prevSpeedStack = new ArrayList<Integer>();
	
	public CeilingFan() {
		speed = OFF;
	}
	
	public void high() {
		prevSpeedStack.add(speed);
		prevSpeed = speed;
		speed = HIGH;
	}
	
	public void medium() {
		prevSpeedStack.add(speed);
		prevSpeed = speed;
		speed = MEDIUM;
	}
	
	public void low() {
		prevSpeedStack.add(speed);
		prevSpeed = speed;
		speed = LOW;
	}
	
	public void off() {
		prevSpeedStack.add(speed);
		prevSpeed = speed;
		speed = OFF;
	}
	
	public void undo() {
		if (prevSpeedStack.size() - 1 <= 0) {
			speed = OFF;
		}
		else {
			speed = prevSpeedStack.get(prevSpeedStack.size() - 1);
			prevSpeedStack.remove(prevSpeedStack.size() - 1);	
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
}

class CeilingFanHighCommand {
	CeilingFan ceilingFan;
	public CeilingFanHighCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}
	
	public void executeHigh() {
		ceilingFan.high();
	}
	
	public void executeLow() {
		ceilingFan.low();
	}
	
	public void undo() {
		ceilingFan.undo();
	}
	
	public CeilingFan getFan() {
		return ceilingFan;
	}
}