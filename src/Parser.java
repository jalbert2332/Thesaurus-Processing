import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Parser {
	ArrayList<String> lines;
	//The input expected input is text doc of roots and synonyms. Each line is a comma delimited list of words where the root 
	//is the first word and all other words on the line are synonyms.
	//For example: "root,synonym1,synonym2,synonym3..."
	Parser(){
		this.lines = new ArrayList<String>();
	}
	
	public ArrayList<String> readInLines(String fileName){
		File file = new File(fileName);
		Scanner scan;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.lines;
		}
		while( scan.hasNextLine() ){
			this.lines.add(scan.nextLine());
		}
		scan.close();
		return this.lines;
	}
	
	public void removeEmptyLines(){
		for (Iterator<String> iterator = this.lines.iterator(); iterator.hasNext();) {
		    String line = iterator.next();
		    if (line.isEmpty()) {
		        // Remove the current element from the iterator and the list.
		        iterator.remove();
		    }
		}
	}
	
	public boolean writeToFile(String fileName){
		try(Writer writer = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(fileName), "utf-8"))) {
				for(String line : this.lines){
					writer.write(line);
					//((BufferedWriter) writer).newLine();
				}
				writer.close();
				return true;
			} catch (UnsupportedEncodingException e) {
				return false;
			} catch (FileNotFoundException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
	}
	
	
}


