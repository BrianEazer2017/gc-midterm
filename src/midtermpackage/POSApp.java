package midtermpackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class POSApp {

	public static void main(String[] args) throws IOException {
		//Project Goals: Create POS JCB Toy Shop
		//Midterm Project from Grand Circus by:
		//Christa Stephens, Jacob Miller & Brian Eazer
		
		Toy toy = new Toy ("Awesome-O", "Action Figure" ,10.88 ,"Boss" );
		Scanner scnr = new Scanner (System.in);
		
		List<Toy> cart = new ArrayList<>();
		
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
			selectToy(toys, scnr);
		}else if (answer == 3) {
			viewCart();
		}else { 
			checkout();
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


	private static void selectToy(List<Toy> toys, Scanner scnr) {
		int answer = Validator.getInt(scnr, "Choose a toy by number:", 1, 12);
		answer--;
		Toy toy = toys.get(answer);
		System.out.println(toy.getName()  + " " + toy.getCategory()
		+ " " + toy.getPrice() + " " + toy.getDescription() );
		
		toyMenu(scnr);
	}


	
	private static void toyMenu(Scanner scnr) {
		String answer = Validator.getStringMatchingRegex(scnr, "Add to cart?", "[Yy]+[eE]*[sS]*|[Nn]+[oO]*");
		if (answer.equals("yes")) { 
			addToCart();
		}
		
		
	}

	private static void addToCart() {
		// TODO Auto-generated method stub
		
	}

	private static void viewCart() {
		// TODO Auto-generated method stub
		
	}
	
	private static void checkout() {
		// TODO Auto-generated method stub
		
	}
}
