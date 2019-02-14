import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String args[]){
		//build thesaurus
		Thesaurus thesaurus = new Thesaurus();
		thesaurus.buildThesaurusFromFile(args[0]);
		
		ArrayList<String> baseWords = new ArrayList<String>();
		baseWords.add("happy");
		baseWords.add("sad");
				
		testAssociations(thesaurus, new Parser(args[1]).getWords(), baseWords, 1); // args[1] contains absolute file path to short story
	}
	
	public static void testAssociations(Thesaurus thesaurus, List<String> candidateWords, List<String> baseWords,int levels){
		//test associations
		WordProcessor process = new WordProcessor(thesaurus);
		Map<String, Integer> baseWordAssociationsCount = process.countAssociations(candidateWords, baseWords, levels);
		for (String baseWord : baseWords ) { 
			System.out.println(baseWord + ": " + baseWordAssociationsCount.get(baseWord) );
		}
	}
	
	public static void print(Collection<String> collection){
		for (String x : collection){
			System.out.println(x);
		}
	}
	
	public static int count(Collection<String> lines){
		int count = 0;
		for(String line : lines){
			String[] words = line.split(",");
			count+=(words.length);
		}return count;
	}
	
	public static int distinctCount(Collection<String> lines){
		HashSet<String> set = new HashSet<String>();
		for(String line : lines){
			String[] words = line.split(",");
			for(String word : words){
				set.add(word);
			}
		}return set.size();
	}
}
