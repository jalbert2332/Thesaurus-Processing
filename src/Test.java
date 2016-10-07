import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Test {
	public static void main(String args[]){
		//build thesaurus
		ArrayList<String> lines = Parser.readInLines(args[0]);
		Thesaurus thesaurus = Thesaurus.buildThesaurus(lines);
		//test associations
		WordProcessor process = new WordProcessor(thesaurus);
		Set<String> associations = process.getAssociations(args[1],Integer.parseInt(args[2]));
		print(associations);
		System.out.println(associations.size());
		System.out.println(count(lines));
		System.out.println(distinctCount(lines));
	}
	
	public static void print(Collection<String> collection){
		for (String x : collection){
			System.out.println(x);
		}
	}
	
	public static int count(ArrayList<String> lines){
		int count = 0;
		for(String line : lines){
			String[] words = line.split(",");
			count+=(words.length);
		}return count;
	}
	
	public static int distinctCount(ArrayList<String> lines){
		HashSet<String> set = new HashSet<String>();
		for(String line : lines){
			String[] words = line.split(",");
			for(String word : words){
				set.add(word);
			}
		}return set.size();
	}
}
