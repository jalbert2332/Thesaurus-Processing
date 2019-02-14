import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Parser {
	private ArrayList<String> lines;
	public List<String> getLines() {
		return Collections.unmodifiableList(this.lines);
	}

	private ArrayList<String> words;
	public List<String> getWords() {
		return Collections.unmodifiableList(this.words);
	}

	//The input expected input is text doc of lines
	// The text document will be read in and an arraylist of all lines(empty lines will be removed) and words will be created
	//For example: "root,synonym1,synonym2,synonym3..."
	Parser(String absoluteFilePath){
		this.lines = new ArrayList<String>();
		this.words = new ArrayList<String>();
		this.readInFile(absoluteFilePath);
	}
	
	private void readInFile(String fileName) {
		File file = new File(fileName);
		// get each line of the file
		this.readInLines(file);
		// clean up text
		this.removeEmptyLines();
		// parse to individual words
		this.readInWords();
	}
	
	private void readInLines(File file){
		Scanner scan;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		while( scan.hasNextLine() ){
			this.lines.add(scan.nextLine());
		}
		scan.close();
	}
	
	private void removeEmptyLines(){
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
					((BufferedWriter) writer).newLine();
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
	
	private void readInWords(){
		if (this.words.isEmpty() && !this.lines.isEmpty()){
			for(String line : this.lines){
				line.replaceAll("\\p{P}", "");
				this.words.addAll(Arrays.asList(line.split(" |,"))); // split on "," and " " Probably need more robust solution one day
			}
		}
	}
}


