package midtermpackage;

import java.util.List;
import java.util.Scanner;
import java.text.*;

public class Checkout {
	static void checkout(Scanner scnr, List<Toy> cart) {
		// If this app were to be used multiple times, it would be advantageous to use a payment class, but we assume
		//this will only be run once.....
		DecimalFormat df = new DecimalFormat("###.##");
		double subTotal = 0;
		for (Toy toy : cart) {
			subTotal += toy.getPrice() * toy.getQuantity();
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
		// df.format(doubleToBeFormatted) formats a double in a print statement
		System.out.println("Your total is $" + df.format(grandTotal) );
		String paymentMethod = Validator.getString(scnr, "Payment type: Cash, credit or check?").toLowerCase();
		if (paymentMethod.startsWith("cas")) {
			amountTendered = Validator.getDouble(scnr, "Input cash amount:");
			countCash(grandTotal, amountTendered, scnr, tax, subTotal, cart, df);
		}
		
		double change = amountTendered - grandTotal;
		if (paymentMethod.startsWith("cred")) {
			getCCDetails(grandTotal, scnr, tax, subTotal, cart, change, df);
		}
		
		if (paymentMethod.startsWith("che")) {
			getCheckDetails(grandTotal, scnr, tax, subTotal, cart, change, df, amountTendered);
		}
		
	}
	 private static void getCheckDetails(double grandTotal, Scanner scnr, double tax, double subTotal, List<Toy> cart,
			double change, DecimalFormat df, double amountTendered) {
		
		 String checkNumber = Validator.getStringMatchingRegex(scnr, "Enter your account number", "[1-3][0-9]{8}");
		 printReceipt(grandTotal, scnr, tax, subTotal, cart, amountTendered, df);
		
	}
	 
	private static void getCCDetails(double grandTotal, Scanner scnr, double tax, double subTotal, List<Toy> cart,
			double amountTendered, DecimalFormat df) {
		    
		 	String name = Validator.getStringMatchingRegex(scnr, "Enter name as it appears on your card", "[a-zA-Z]+ [a-zA-Z]+");
		 	String CCnum = Validator.getStringMatchingRegex(scnr,
		 	"Enter your credit card number. Use the following format XXXX-XXXX-XXXX-XXXX", "\\d{4}-\\d{4}-\\d{4}-\\d{4}");
		    String expDate = Validator.getStringMatchingRegex(scnr, "Enter the expiration date", "(0[1-9]|10|11|12)/[0-9]{2}$");
			String CVV = Validator.getStringMatchingRegex(scnr, "Enter the CVV number", "\\d{3}");
			 printReceipt(grandTotal, scnr, tax, subTotal, cart, amountTendered, df);
		
	}
	public static void countCash(double total, double cash, Scanner scnr, double tax, double subTotal, List<Toy> cart, DecimalFormat df) {
		    if (Math.abs(total-cash) < 0.009) {
	            System.out.println("Perfect change!");  
	        }
		    else if (total > cash) {
	            total -= cash;
	            double newCash = Validator.getDouble(scnr, "You still owe " + df.format(total) + ". How would you like to pay for the remainder?");
	            countCash(total, newCash, scnr, newCash, newCash, cart, df);
	        }  else if (total < cash) {
	        	double change = cash-total;
	            System.out.println("Thank you! Your change is $" + df.format(change));
	            printReceipt(total, scnr, tax, subTotal, cart, cash, df);
	        } 
	 }
	 
	 public static void printReceipt(Double total, Scanner scnr, Double tax, Double subTotal, List<Toy> cart, Double amountTendered, DecimalFormat df) {
		 System.out.println("Thank you for shopping at JCB Toy's");
		 System.out.printf("%-15s","%-15s","%-15s", "Name", "Quantity", "Price" );
	 	}
	 }
