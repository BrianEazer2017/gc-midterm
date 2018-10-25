package midtermpackage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;



public class POSApp {

	public static void main(String[] args) throws IOException {
		//Project Goals: Create POS JCB Toy Shop
		//Midterm Project from Grand Circus by:
		//Christa Stephens, Jacob Miller & Brian Eazer
		
		Toy toy = new Toy ("Awesome-O", "Action Figure" ,10.88 ,"Boss" );
		
		//System.out.println(toy); testing to see if it works
		
		Scanner scnr = new Scanner (System.in);
		//validator - replace prompt to what we want to output to user
		//then min & max is the amount we have in list
		// int choice = Validator.getInt(scnr, "Enter the toy by number.", 1, 12);
		
		//System.out.println(choice); //testing to see if it works
		List<Toy> toys = POSTextFile.readFile();
		for (Toy t : toys) {
			System.out.println(t.getName() + " " + t.getCategory() + " " + t.getPrice() + " " + t.getDescription());
		}
		//System.out.println(POSTextFile.readFile());
 
	}

}
