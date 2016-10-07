import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	
	//The input expected input is text doc of roots and synonyms. Each line is a comma delimited list of words where the root 
	//is the first word and all other words on the line are synonyms.
	//For example: "root,synonym1,synonym2,synonym3..."
	public static ArrayList<String> readInLines(String fileName){
		ArrayList<String> lines = new ArrayList<String>();
		File file = new File(fileName);
		Scanner scan;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return lines;
		}
		while( scan.hasNextLine() ){
			lines.add(scan.nextLine());
		}
		scan.close();
		return lines;
	}
}


