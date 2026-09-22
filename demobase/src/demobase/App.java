package demobase;

public class App {

	public static void main(String[] args) {
		var c = new Dummy();
		
		System.out.println(c.suma(2, 2)); // 4 
		System.out.println(c.suma(1, 2)); // 3
		System.out.println(c.suma(0.1, 0.2));
		System.out.println(c.suma(1, -0.9));
	}

}
