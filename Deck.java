import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Deck
{
	ArrayList<String> mainDeck, extraDeck, sideDeck;
	public Deck(){
		mainDeck = new ArrayList<>();
		extraDeck = new ArrayList<>();
		sideDeck = new ArrayList<>();
	}
	public Deck(String filename) throws FileNotFoundException{
		mainDeck = new ArrayList<>();
		extraDeck = new ArrayList<>();
		sideDeck = new ArrayList<>();
		Scanner scan = new Scanner(new File(filename));
		int state = 0;
		while(scan.hasNextLine()){
			String tmp = scan.nextLine();
			switch(tmp){
				case "#main":
					state = 1;
					continue;
				case "#extra":
					state = 2;
					continue;
				case "!side":
					state = 3;
					continue;
			}
			switch(state){
				case 1:
					mainDeck.add(tmp);
					break;
				case 2:
					extraDeck.add(tmp);
					break;
				case 3:
					sideDeck.add(tmp);
					break;
			}
		}
		scan.close();
	}

	public void addMain(String card){
		mainDeck.add(card);
	}
	public void addExtra(String card){
		extraDeck.add(card);
	}
	public void addSide(String card){
		sideDeck.add(card);
	}
	public void removeMain(String card){
		mainDeck.remove(card);
	}
	public void removeExtra(String card){
			extraDeck.remove(card);
	}
	public void removeSide(String card){
			sideDeck.remove(card);
	}

	ArrayList<String> getMainDeck(){
		return mainDeck;
	}
	ArrayList<String> getExtraDeck(){
		return extraDeck;
	}
	ArrayList<String> getSideDeck(){
		return sideDeck;
	}
	public Deck subtract(Deck a){
		Deck out = copy();
		for(String s:a.getMainDeck()){
			out.removeMain(s);
		}
		for(String s:a.getExtraDeck()){
			out.removeExtra(s);
		}
		for(String s:a.getMainDeck()){
			out.removeSide(s);
		}
		return out;
	}

	public Deck copy(){
		Deck out = new Deck();
		for(String s:getMainDeck()){
			out.addMain(s);
		}
		for(String s:getExtraDeck()){
			out.addExtra(s);
		}
		for(String s:getSideDeck()){
			out.addSide(s);
		}
		return out;
	}

	public void writeTo(String filename){
		File outfile = new File(filename);
		if(outfile.exists()){
			outfile.delete();
		}
		try{
			outfile.createNewFile();
			FileWriter fw = new FileWriter(outfile);
			fw.write("#created by YGODeckSubtract v1.0\n");
			fw.write("#main\n");
			for(String i:getMainDeck()){
				fw.write(i + "\n");
			}
			fw.write("#extra\n");
			for(String i:getExtraDeck()){
				fw.write(i + "\n");
			}
			fw.write("!side\n");
			for(String i:getSideDeck()){
				fw.write(i + "\n");
			}
			fw.close();
		} catch(IOException e){
			// do nothing lmao
		}
	}

	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("Main\n");
		for(int i = 0; i < mainDeck.size(); i++){
			s.append(mainDeck.get(i) + "\n");
		}
		s.append("Extra:\n");
		for(int i = 0; i < extraDeck.size(); i++){
			s.append(extraDeck.get(i) + "\n");
		}
		s.append("Side:\n");
		for(int i = 0; i < sideDeck.size(); i++){
			s.append(sideDeck.get(i) + "\n");
		}
		return s.toString();
	}
}