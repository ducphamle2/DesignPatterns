/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duc.vn.learning.design.patterns;

/**
 *
 * @author dell
 */
public class DecoratorDesign {
    public static void main(String[] args) {

        Bevarage order2 = new HouseBlend();
        order2 = new Mocha(order2);
         order2 = new Whip(order2);
        //order2 = new SoyMilk(order2);
        System.out.println(order2.getDescription() + " $"+ order2.cost());
    }
}
//base class 1
abstract class Bevarage {
    String description = "Unknown description";
    
    //a frame to override
    public String getDescription(){
        return description;
    }
    
    public abstract double cost();
}

//wrapper
//has the same type as what it wraps around.
abstract class Condiments extends Bevarage{
    //so that its subclass can override this method
    @Override
    public abstract String getDescription();
}


class Expresso extends Bevarage{
    
    public Expresso(){
        description = "Expresso";
    }
    @Override
    public double cost(){
        return 1.99;
    }
}

class HouseBlend extends Bevarage{
    public HouseBlend(){
        description = "House Blend";
    }
    @Override
    public double cost(){
        return 0.89;
    }
}

class Decaf extends Bevarage{
    public Decaf(){
        description = "Decaf";
    }
    public double cost(){
        return 1.05;
    }
}

//Condiments Decorator 
//Concrete Implementations 

class Mocha extends Condiments{
    Bevarage bevarage;
    
    public Mocha(Bevarage bevarage){
        this.bevarage = bevarage;
    }
    @Override
    public String getDescription(){
        return (bevarage.getDescription() + " Mocha");
    }
    @Override
    public double cost(){
        return 0.2 + bevarage.cost();
    }
}

class Whip extends Condiments{
    Bevarage bevarage;
    public Whip(Bevarage bevarage){
        this.bevarage = bevarage;
    }
    @Override
    public String getDescription(){
        return (bevarage.description + " Whip");
    }
    @Override
    public double cost(){
        return 0.1 + bevarage.cost();
    }
}

class SoyMilk extends Condiments{
    Bevarage bevarage;
    public SoyMilk(Bevarage bevarage){
        this.bevarage = bevarage;
    }
    @Override
     public String getDescription(){
        return (bevarage.description + " Soy Milk");
    }
    @Override
    public double cost(){
        return bevarage.cost() +  0.15;
    }
}

class SteamedMilk extends Condiments{
    Bevarage bevarage;
    public SteamedMilk(Bevarage bevarage){
        this.bevarage = bevarage;
    }
    @Override
    public String getDescription(){
        return bevarage.description + " Steamed Milk";
    }
    @Override
    public double cost(){
        return 0.1 + bevarage.cost();
    }
}