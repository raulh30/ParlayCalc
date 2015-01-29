package com.parlaycalc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/** 
* ParlayCalc.java - a simple parlay calculator. 
* @author  Raul Hidalgo
* @version 1.0
*/

public class ParlayCalc {

	private double wager;
	private ArrayList<Integer> odds; // list to hold the odds

	public ParlayCalc() {
		this.wager = 0;
		this.odds = new ArrayList<>();
	}

	public double getWager() {
		return this.wager;
	}

	public ArrayList<Integer> getOdds() {
		return this.odds;
	}

	public void setWager(double wager) {
		this.wager = wager;
	}

	public void setOdds(ArrayList<Integer> odds) {
		this.odds = odds;
	}

	// adds odds to list
	public void add(int odd) {
		this.odds.add(odd);
	}

	// computes the payout by iterating through list of odds
	public double getPayout() {
		double totalOdds = 1;
		Iterator<Integer> iter = this.odds.iterator();

		while (iter.hasNext()) {
			Integer integer = (Integer) iter.next();
			totalOdds *= this.getDecimalOdds(integer);
		}

		return this.wager * (Math.round(totalOdds * 100) / 100.00);

	}

	// converts the money line to decimal odd in order to calculate total odd
	public double getDecimalOdds(Integer americanOdd) {
		double dec;
		double roundedDec;
		if (americanOdd < 0) {
			dec = 100.00 / (double) (Math.abs(americanOdd));
			roundedDec = (Math.round(dec * 100.00)) / 100.00;

		} else {
			dec = (double) americanOdd / 100.00;
			roundedDec = (Math.round(dec * 100.00)) / 100.00;

		}

		return roundedDec + 1.00;
	}

	public static void main(String[] args) {

		ParlayCalc parlay = new ParlayCalc();
		int numberOfBets = 1;

		System.out.println("Raul's Parlay Calculator");
		System.out.println("------------------------");

		Scanner s = new Scanner(System.in);

		System.out.print("Please enter wager: $");
		parlay.setWager(s.nextDouble());

		System.out.println("\nPlace bets (enter 0 when done betting)");
		while (true) {
			s = new Scanner(System.in);
			System.out.print("Money Line #" + numberOfBets + ": ");
			int moneyLine = 0;
			try {
				moneyLine = s.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Money Line, try again.");
				continue;
			}

			if (moneyLine == 0) {
				break;
			}
			parlay.add(moneyLine);
			numberOfBets++;

		}

		s.close();

		System.out.println("\nPayout = $"
				+ new DecimalFormat("0.00").format(parlay.getPayout()));

	}
}
