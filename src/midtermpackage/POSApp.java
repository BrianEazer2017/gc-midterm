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
		
		System.out.println(greet());
		//System.out.println(toy); testing to see if it works
		
		
		//validator - replace prompt to what we want to output to user
		//then min & max is the amount we have in list
		// int choice = Validator.getInt(scnr, "Enter the toy by number.", 1, 12);
		
		//System.out.println(choice); //testing to see if it works
		List<Toy> toys = POSTextFile.readFile();
		
		//System.out.println(POSTextFile.readFile());
		startMenu(scnr, toys, cart);
	}

	private static void startMenu(Scanner scnr, List<Toy> toys, List<Toy> cart) throws IOException {
		
		System.out.println("=============");
		System.out.println("1. View list of toys: ");
		System.out.println("2. Select a toy: ");
		System.out.println("3. View cart: "); 
		System.out.println("4. Checkout: ");
		System.out.println("============");
		
		int answer = Validator.getInt(scnr, "\nEnter your choice:", 1, 4);
		
		if (answer == 1) {
			viewToys(scnr, toys);
		}else if (answer ==2) {
			selectToy(toys, scnr, cart);
		}else if (answer == 3) {
			viewCart(scnr, toys, cart);
		}else { 
			Checkout.checkout(scnr, cart);
			//remem refresh cart
			
		}
		
		
	}


	private static void viewToys(Scanner scnr, List<Toy> toys) throws IOException {
		int count = 1;
		for (Toy t : toys) {

			System.out.println(count+". "+ t.getName());
			count++;
	
		}
		startMenu(scnr, toys, cart);
	}


	private static void selectToy(List<Toy> toys, Scanner scnr, List<Toy> cart) throws IOException {
		int answer = Validator.getInt(scnr, "Choose a toy by number:", 1, toys.size());
		answer--;
		Toy toy = toys.get(answer);
		System.out.println(toy.getName()  + " " + toy.getCategory()
		+ " " + toy.getPrice() + " " + toy.getDescription() );
		
		toyMenu(scnr, toys, answer, cart);
	}


	
	private static void toyMenu(Scanner scnr, List<Toy> toys, int toyChoice, List<Toy> cart) throws IOException {
		String answer = Validator.getStringMatchingRegex(scnr, "Add to cart?", "[Yy]+[eE]*[sS]*|[Nn]+[oO]*");
		if (answer.matches("[Yy]+[eE]*[sS]*")) { 
			addToCart(toys.get(toyChoice), cart, scnr, toys);
			
		}
		else {
			startMenu(scnr, toys, cart);
		}
		
	}

	private static void addToCart(Toy toy, List<Toy> cart, Scanner scnr, List<Toy> toys) throws IOException {
		cart.add(toy);
		toys.remove(toy);
		
		POSTextFile.rewritetxtFile(toys);
		startMenu( scnr, toys, cart);
		
	}

	private static void viewCart(Scanner scnr, List<Toy> toys, List<Toy> cart) throws IOException {
		System.out.println("Your cart:");
		int counter = 1;
		for(Toy toy : cart) {
			System.out.println(counter + ". " + toy.toString());
			counter++;
		}
		if (cart.size() > 0) {
		askToRemove(scnr, toys, cart);
		} else {
		System.out.println("Your cart is currently empty.");
		startMenu(scnr, toys, cart);
		}
	}
	
	private static void askToRemove(Scanner scnr, List<Toy> toys, List<Toy> cart) throws IOException {
		String answer = Validator.getStringMatchingRegex(scnr, "Would you like to remove an item?", "[Yy]+[eE]*[sS]*|[Nn]+[oO]*");
		if (answer.matches("[Yy]+[eE]*[sS]*")) {
			int itemIndex = Validator.getInt(scnr, "What item number would you like to remove from the cart?", 1, cart.size());
			itemIndex--;
			cart.remove(itemIndex);
		}
		
		startMenu(scnr, toys, cart);
	}

	private static String greet() {
		return "Welcome to JCB Toys! Where a kid can be a kid, even as an adult."
				+ "\nFor your best shopping expierence, please select \"View list of toys\".\n"
				+ "             ~~~~~~***************~~~~~~            "
				+ "\n";
	}
}
