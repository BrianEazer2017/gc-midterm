package midtermpackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class POSApp {

	private static List<Toy> cart = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		//Project Goals: Create POS JCB Toy Shop
		//Midterm Project from Grand Circus by:
		//Christa Stephens, Jacob Miller & Brian Eazer
		Scanner scnr = new Scanner (System.in);
		
		
		//System.out.println(toy); testing to see if it works
		
		
		//validator - replace prompt to what we want to output to user
		//then min & max is the amount we have in list
		// int choice = Validator.getInt(scnr, "Enter the toy by number.", 1, 12);
		
		//System.out.println(choice); //testing to see if it works
		List<Toy> toys = POSTextFile.readFile();
		
		//System.out.println(POSTextFile.readFile());
		startMenu(scnr, toys);
	}

	private static void startMenu(Scanner scnr, List<Toy> toys) {
		
		System.out.println("1. View list of toys: ");
		System.out.println("2. Select a toy: ");
		System.out.println("3. View cart: "); 
		System.out.println("4. Checkout: ");
		
		int answer = Validator.getInt(scnr, "Enter a choice:", 1, 4);
		
		if (answer == 1) {
			viewToys(scnr, toys);
		}else if (answer ==2) {
			selectToy(toys, scnr, cart);
		}else if (answer == 3) {
			viewCart(cart, scnr);
		}else { 
			checkout();
			//remem refresh cart
			
		}
		
		
	}


	private static void viewToys(Scanner scnr, List<Toy> toys) {
		int count = 1;
		for (Toy t : toys) {

			System.out.println(count+". "+ t.getName());
			count++;
	
		}
		startMenu(scnr, toys);
	}


	private static void selectToy(List<Toy> toys, Scanner scnr, List<Toy> cart) {
		int answer = Validator.getInt(scnr, "Choose a toy by number:", 1, 12);
		answer--;
		Toy toy = toys.get(answer);
		System.out.println(toy.getName()  + " " + toy.getCategory()
		+ " " + toy.getPrice() + " " + toy.getDescription() );
		
		toyMenu(scnr, toys, answer, cart);
	}


	
	private static void toyMenu(Scanner scnr, List<Toy> toys, int toyChoice, List<Toy> cart) {
		String answer = Validator.getStringMatchingRegex(scnr, "Add to cart?", "[Yy]+[eE]*[sS]*|[Nn]+[oO]*");
		if (answer.equals("yes")) { 
			addToCart(toys.get(toyChoice), cart, scnr, toys);
			
		}
		else if (answer.equals("no")) {
			startMenu(scnr, cart);
		}
		
	}

	private static void addToCart(Toy toy, List<Toy> cart, Scanner scnr, List<Toy> toys) {
		cart.add(toy);
		POSTextFile.rewritetxtFile(toys);
		startMenu( scnr, cart);
		
	}

	private static void viewCart(List<Toy> cart, Scanner scnr) {
		System.out.println("Your cart:");
		for(Toy toy : cart) {
			System.out.println(toy.toString());
		}
	}
	
	private static void checkout() {
		// TODO Auto-generated method stub
		
	}
}
