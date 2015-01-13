/*
 * Author: Daniel Wester
 *
 * Date: 02.25.12
 *
 * Course: 2243-01, Spring 2012
 *
 * Assignment: PGM2
 *
 * Description: This program will input ask the user to input a account number, account type (Checking or Savings),
 *  minimum balance, and starting balance. It will then add the interest ammount or subtract a service charge. It will
 *  then end with final screen telling the user all the information they input and the new ending balance.
 *
 */

import java.awt.*;
import javax.swing.*;

public class MonthlyAccountUpdate {
	
	//Constants
	static final double SAVINGS_INTEREST = 4;
	static final double CHECKING_INTEREST_ABOVE = 5;
	static final double CHECKING_INTEREST_BELOW = 3;
	static final double CHECKING_INTEREST_HIGHER_RATE = 5000;
	static final double SAVINGS_CHARGE = 10.00;
	static final double CHECKING_CHARGE = 25.00;
	static final double MINIMUM_BALANCE_REQUIRED_ERROR = 0;
	
	public static void main (String[] args) {
		
		//Variables
		int accountNumber = 0;
		String accountTypeInput = "";
		char accountType = 0;
		String accountTypeFullName = "";
		double minimumBalance = 0;
		double startingBalance = 0;
		double endingBalance = 0;
		double percentAmount = 0;
		double serviceCharge = 0;
		double interestEarned = 0;
		String exitProgramInput = "";
		char exitProgram = 0;
		boolean exitCheckingSavingsLoop = true;
		boolean exitMinimumBalanceLoop = true;
		boolean exitProgramLoop = true;

	do {  //Start of the program
	
			//Input
			accountNumber = Integer.parseInt(JOptionPane.showInputDialog("Welcome,\n\nThis program will update an accounts interest,or apply any service charges.\n" +
				"It will then end showing all information entered and any changes that were made.\n\n" +
				"What is the account number?"));
	
			accountTypeInput = JOptionPane.showInputDialog("What is the account type?\n\nc for Checking\ns for Savings \n");
			accountType = accountTypeInput.toLowerCase().charAt(0);
	
		do {  //Savings/Checking Switch
			switch (accountType) {
			default: {
					accountTypeInput = JOptionPane.showInputDialog("You entered " + accountTypeInput + " please enter a valid choice.\n\n" +
						"What is the account type?\n\nc for Checking\ns for Savings \n").toLowerCase();
					accountType = accountTypeInput.charAt(0);
				}
				break;
			case 'c': {
				minimumBalance = Double.parseDouble(JOptionPane.showInputDialog("What is the checking accounts minimum amount?"));
				while (exitMinimumBalanceLoop) {
					if (minimumBalance < MINIMUM_BALANCE_REQUIRED_ERROR)
						minimumBalance = Double.parseDouble(JOptionPane.showInputDialog(null,"You entered " + minimumBalance + " please enter a positive number.\n\nWhat is the checking accounts minimum amount?","Error",JOptionPane.ERROR_MESSAGE));
					else
						exitMinimumBalanceLoop = false;
				}
				startingBalance = Double.parseDouble(JOptionPane.showInputDialog("What is the checking accounts current balance?"));
					if (startingBalance > minimumBalance + CHECKING_INTEREST_HIGHER_RATE) {
						endingBalance = (startingBalance * ((CHECKING_INTEREST_ABOVE / 100) + 1));
						percentAmount = (CHECKING_INTEREST_ABOVE);
					} else if (startingBalance >= minimumBalance) {
						endingBalance = (startingBalance * ((CHECKING_INTEREST_BELOW / 100) + 1));
						percentAmount = (CHECKING_INTEREST_BELOW);
					} else {
						endingBalance = (startingBalance - CHECKING_CHARGE);
						serviceCharge = CHECKING_CHARGE;
					}
				}
				accountTypeFullName = "Checking";
				exitCheckingSavingsLoop = false;
				break;
			case 's': {
				minimumBalance = Double.parseDouble(JOptionPane.showInputDialog("What is the savings accounts minimum amount?"));
					while (exitMinimumBalanceLoop) {
					if (minimumBalance < MINIMUM_BALANCE_REQUIRED_ERROR)
						minimumBalance = Double.parseDouble(JOptionPane.showInputDialog(null,"You entered " + minimumBalance + " please enter a positive number.\n\nWhat is the savings accounts minimum amount?","Error",JOptionPane.ERROR_MESSAGE));
					else
						exitMinimumBalanceLoop = false;
				}
				startingBalance = Double.parseDouble(JOptionPane.showInputDialog("What is the savings accounts current balance?"));
					if (startingBalance >= minimumBalance){
						endingBalance = (startingBalance * ((SAVINGS_INTEREST / 100) + 1));
						percentAmount = (SAVINGS_INTEREST);
					} else {
						endingBalance = (startingBalance - SAVINGS_CHARGE);
						serviceCharge = SAVINGS_CHARGE;
					}
				}
				accountTypeFullName = "Savings";
				exitCheckingSavingsLoop = false;
				break;
			}
		} while (exitCheckingSavingsLoop);
	
		//Formatting
		String totalEnding = String.format("Account number: %6d%nAccount type: %s%nMinimum balance: $%,.2f%nBeginning balance: $%,.2f%n",
			accountNumber, accountTypeFullName, minimumBalance, startingBalance);
			
		if (startingBalance >= minimumBalance) {
			interestEarned = (endingBalance - startingBalance);
			totalEnding = totalEnding + String.format("Interest rate applied: %.0f%%%nInterest earned: $%,.2f%nEnding balance: $%,.2f", percentAmount, interestEarned, endingBalance);
		} else
			totalEnding = totalEnding + String.format("Service charge applied: $-%,.2f%nEnding balance: $%,.2f", serviceCharge, endingBalance);
			
		JOptionPane.showMessageDialog(null,totalEnding,"Monthly Account Update",JOptionPane.PLAIN_MESSAGE);
	
		exitProgramInput = JOptionPane.showInputDialog("Thank you for using this program.\n\nIf you would like to enter another account type y for yes.\nType anything else to quit.");
		exitProgram = exitProgramInput.toLowerCase().charAt(0);
		if (exitProgram == 'y') {
			exitCheckingSavingsLoop = true;
			exitMinimumBalanceLoop = true;
		} else
			exitProgramLoop = false;
	} while (exitProgramLoop);
	}
}