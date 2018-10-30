package midtermpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.text.*;

public class Checkout {
	static void checkout(Scanner scnr, List<Toy> cart, List<Toy> toys) throws IOException {
		// If this app were to be used multiple times, it would be advantageous to use a payment class, but we assume
		//this will only be run once.....
		DecimalFormat df = new DecimalFormat("#####.00");
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
		String[] coupons = {"GCISCOOL20", "JCB20", "HIDAVID20"};
		boolean hasCoupon = false;
		Double couponTotal = grandTotal*.2;
		String getCoupons = Validator.getStringMatchingRegex(scnr, "Do you have any JCB coupons?", "[Yy]+[eE]*[sS]*|[Nn]+[oO]*");
		if (getCoupons.matches("[Yy]+[eE]*[sS]*")){
			String enterCoupon = Validator.getStringMatchingRegex(scnr, "Enter Coupon", "[A-Z0-9]+");
			for (String c: coupons) {
				if (c.equals(enterCoupon)) {
					hasCoupon = true;
				}
			}
		}
		if (hasCoupon == true) {
			grandTotal -= couponTotal;
			System.out.println("Your discounted total is " + df.format(grandTotal) + ".");
		}
		String paymentMethod = Validator.getString(scnr, "Payment type: Cash, credit or check?").toLowerCase();
		if (paymentMethod.startsWith("cas")) {
			amountTendered = Validator.getDouble(scnr, "Input cash amount:");
			countCash(grandTotal, amountTendered, scnr, tax, subTotal, cart, df, toys, hasCoupon, couponTotal);
		}
		
		double change = amountTendered - grandTotal;
		if (paymentMethod.startsWith("cre")) {
			getCCDetails(grandTotal, scnr, tax, subTotal, cart, change, df, toys, hasCoupon, couponTotal);
		}
		
		if (paymentMethod.startsWith("che")) {
			getCheckDetails(grandTotal, scnr, tax, subTotal, cart, change, df, amountTendered, toys, hasCoupon, couponTotal);
		} else {
			checkout(scnr, cart, toys);
		}
		
	}
	 private static void getCheckDetails(double grandTotal, Scanner scnr, double tax, double subTotal, List<Toy> cart,
			double change, DecimalFormat df, double amountTendered, List<Toy> toys, boolean hasCoupon, double couponTotal) throws IOException {
		
		 String checkNumber = Validator.getStringMatchingRegex(scnr, "Enter your account number", "[1-3][0-9]{8}");
		 String name = Validator.getStringMatchingRegex(scnr, "Enter your name as it appears on the check:", "[a-zA-Z]+ [a-zA-Z]+");
		 printReceipt(grandTotal, scnr, tax, subTotal, cart, amountTendered, df, toys, hasCoupon, couponTotal);
		
	}
	 
	private static void getCCDetails(double grandTotal, Scanner scnr, double tax, double subTotal, List<Toy> cart,
			double amountTendered, DecimalFormat df, List<Toy> toys, boolean hasCoupon, double couponTotal) throws IOException {
		    
		 	String name = Validator.getStringMatchingRegex(scnr, "Enter name as it appears on your card:", "[a-zA-Z]+ [a-zA-Z]+");
		 	String CCnum = Validator.getStringMatchingRegex(scnr,
		 	"Enter your credit card number. Use the following format: XXXX-XXXX-XXXX-XXXX", "\\d{4}-\\d{4}-\\d{4}-\\d{4}");
		    String expDate = Validator.getStringMatchingRegex(scnr, "Enter the expiration date in the following format: MM/YY.", "(0[1-9]|10|11|12)/[0-9]{2}$");
			String CVV = Validator.getStringMatchingRegex(scnr, "Enter the CVV number:", "\\d{3}");
			 printReceipt(grandTotal, scnr, tax, subTotal, cart, amountTendered, df, toys, hasCoupon, couponTotal);
		
	}
	public static void countCash(double total, double cash, Scanner scnr, double tax, double subTotal, List<Toy> cart, DecimalFormat df, List<Toy> toys,
			boolean hasCoupon, double couponTotal) throws IOException {
		    if (Math.abs(total-cash) < 0.009) {
	            System.out.println("Perfect change!"); 
	            printReceipt(total, scnr, tax, subTotal, cart, cash, df, toys, hasCoupon, couponTotal);
	        }
		    else if (total > cash) {
	            total -= cash;
	            double newCash = Validator.getDouble(scnr, "You still owe " + df.format(total) + ". How would you like to pay for the remainder?");
	            countCash(total, newCash, scnr, newCash, newCash, cart, df, toys, hasCoupon, newCash);
	            
	        }  else if (total < cash) {
	        	double change = cash-total;
	            System.out.println("Thank you! Your change is $" + df.format(change));
	            printReceipt(total, scnr, tax, subTotal, cart, cash, df, toys, hasCoupon, couponTotal);
	        } 
	 }
	 
	 public static void printReceipt(Double total, Scanner scnr, 
			 Double tax, Double subTotal, List<Toy> cart, Double amountTendered, DecimalFormat df, List<Toy> toys, boolean hasCoupon, double couponTotal) throws IOException {
		 System.out.println("");
		 System.out.println("Thank you for shopping at JCB Toy's.");
		 System.out.printf("%-15s%-15s%-15s%-15s", "Quantity","Name", "Price", "Price Per Item");
		 System.out.println("");
		 System.out.printf("%-15s%-15s%-15s%-15s","|======|", "|======|", "|======|", "|===========|");
		 System.out.println("");
		 for (Toy toy: cart) {
			 System.out.printf("%-15s%-15s%-15s%-15s", toy.getQuantity(), toy.getName(), 
					 toy.getPrice(), df.format(toy.getPrice()*toy.getQuantity())) ;
			 System.out.println("");
		 }
		 System.out.println("Subtotal " + df.format(subTotal));
		 System.out.println("Taxes " + df.format(tax));
		 System.out.println("Total "+ df.format(total));
		 if(hasCoupon) {
			 System.out.println("Your coupon saved you " + df.format(couponTotal));
		 }
		 System.out.println("Thanks for shopping at JCB Toy Store!");
		 shopAgain(cart, scnr, toys);
	 	}
	private static void shopAgain(List<Toy> cart, Scanner scnr, List<Toy> toys) throws IOException {
		String answer = Validator.getStringMatchingRegex(scnr, 
				"Would you like to shop again?", "[Yy]+[eE]*[sS]*|[Nn]+[oO]*");
		
		if (answer.matches("[Yy]+[eE]*[sS]*")) {
			
			POSApp.initialize();
			
		}
	}
	 }
