import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Main{
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(System.in);
		String fn1 = "";
		String fn2 = "";
		String fn3 = "";
		if(args.length == 3){
			fn1 = args[0];
			fn2 = args[1];
			fn3 = args[3];
		} else{
			System.out.println("A - B = C");
			System.out.print("Path to Deck A:");
			fn1 = s.nextLine();
			System.out.print("Path to Deck B:");
			fn2 = s.nextLine();
			System.out.print("Path to Deck C (output deck):");
			fn3 = s.nextLine();
		}
		Deck a = new Deck(fn1);
		Deck b = new Deck(fn2);
		Deck c = a.subtract(b);
		System.out.println("Output deck:");
		System.out.println(c);
		System.out.println("Would you like to write this deck to: " + fn3 + " (y/N)");
		String choice = s.next().toLowerCase();
		if(choice.equals("y")){
			c.writeTo(fn3);
		} else{
			System.out.println("Aborted.");
		}
	}
}