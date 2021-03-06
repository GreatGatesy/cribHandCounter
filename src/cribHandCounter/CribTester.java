package cribHandCounter;
/*
 * Tests functionality of crib counter classes
 * takes user input for what cards are in hand
 * @author Alexander Gates
 * @date Nov.06.2019
 */
import java.util.Scanner;


public class CribTester {
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		Hand hand = new Hand();
		
		System.out.println("Enter Cards in Your Hand In The Format:1,2,3,4, ...");
		
		//split the entered string at the comma
		String[] inArray = in.nextLine().split(",");
		for(String s: inArray) {
			//split the string at a space to separate suit and name
			Card c = new Card(Integer.parseInt(s));
			hand.addCard(c);
		}
	
	
		
		PointCount points = new PointCount(hand);
		//these do not require sorting
		System.out.println("Nobs? (true/false)");
		boolean nobs = in.nextBoolean();
		System.out.println("Same Suit? (true/false)");
		boolean sameSuit = in.nextBoolean();
		
		points.jackPoints(nobs);
		points.fullSuitPoints(sameSuit,false);
		
		//make sure hand is sorted before calculating points that require sorting
		hand.sort();
		
		points.findRuns();
		points.pairsPoints();
		
		//15's need just an int array
		int[] handArray1 = hand.toIntArray();
		int[] handArray2 = new int[handArray1.length];
		points.fifteenCombination(handArray1,handArray2,0,-1);
		points.addFifteenPoint();


		System.out.println(points);
		System.out.println(hand);
		
		in.close();
	}

}
