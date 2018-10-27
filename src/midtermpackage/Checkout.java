package midtermpackage;

import java.util.List;
import java.util.Scanner;

public class Checkout {
	static void checkout(Scanner scnr, List<Toy> cart) {
		// If this app were to be used multiple times, it would be advantageous to use a payment class, but we assume
		//this will only be run once.....
		double subTotal = 0;
		for (Toy toy : cart) {
			subTotal += toy.getPrice();
		}
		double tax = subTotal * .06;
		double grandTotal = subTotal + tax;
		double amountTendered = 0;
		// We used strings for some of the variables here because we're not doing anything with them afterwards. 
		String name = "";
		long CCnum = 0;
		String expDate = "";
		String CVV = "";
		String checkNum = "";
		String paymentMethod = Validator.getString(scnr, "Payment type: Cash, credit or check?").toLowerCase();
		if (paymentMethod.startsWith("cas")) {
			amountTendered = Validator.getDouble(scnr, "Input cash amount:");
		}
		double change = amountTendered - grandTotal;
		if (paymentMethod.startsWith("cred")) {
			CCnum = Validator.getLong(scnr, "Enter your credit card number.");
		}
		
	}
}
