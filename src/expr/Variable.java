

package expr;

import java.util.Hashtable;


public class Variable extends Expr {
    private static Hashtable variables = new Hashtable();
    
   
    static public synchronized Variable make(String name) {
	Variable result = (Variable) variables.get(name);
	if (result == null)
	    variables.put(name, result = new Variable(name));
	return result;
    }

    private String name;
    private double val;

  
    public Variable(String name) { 
	this.name = name; val = 0; 
    }

    
    public String toString() { return name; }

   
    public double value() { 
	return val; 
    }
   
    public void setValue(double value) { 
	val = value; 
    }
}
