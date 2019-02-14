import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordProcessor {
	public Thesaurus thesaurus;
	
	WordProcessor(Thesaurus thesaurus){
		this.thesaurus = thesaurus;
	}
	
	private Set<String> getAssociationForBaseWord(String baseWord, int levels){
		HashSet<String> associations = new HashSet<String>();
		Set<String> rootsAtLevel = new HashSet<String>();
		Set<String> nextLevel = new HashSet<String>();
		Set<String> synonyms;
		
		rootsAtLevel.add(baseWord); //seed the list of roots with our base
		for (int i = 0 ; i <= levels ; i++){ //search the specified number of levels
			for(String root : rootsAtLevel){
				synonyms = this.thesaurus.getSynonyms(root);
				if (synonyms == null){ //if less common word then it may not be a root in the thesaurus
					continue;
				}
				for(String synonym : synonyms){
					if(!associations.contains(synonym)){
						associations.add(synonym);
						nextLevel.add(synonym);
					}
				}
			}
			rootsAtLevel.clear();
			rootsAtLevel.addAll(nextLevel);
			nextLevel.clear();
		}
		return associations;
	}
	
	// input a list of words like a story, input a list of base words like "happy,sad", define degree of looseness in regards to association to base words and count them up
	public HashMap<String, Integer> countAssociations(List<String> candidateWords, List<String> baseWords, int levels){
		HashMap<String, HashSet<String>> associationsMap = new HashMap<String, HashSet<String>>();
		HashMap<String, Integer> baseWordAssociationsCount = new HashMap<String, Integer>();
		// get associated words for each base word we input
		for (String word : baseWords){
			associationsMap.put(word, (HashSet<String>) getAssociationForBaseWord(word,levels));
		}
		
		String baseWord;
		int count;
		
		for (String candidateWord : candidateWords){
			baseWord = getAssociatedBaseWord(candidateWord, associationsMap);
			if( !baseWord.equals("")){
				// increment our count if it matches a base word
				if(baseWordAssociationsCount.containsKey(baseWord)){
					count = baseWordAssociationsCount.get(baseWord);
				}else{
					count = 0;
				}
				count++;
				baseWordAssociationsCount.put(baseWord, count);
			}
		}
		return baseWordAssociationsCount;
	}
	
	// given a base set of words and their associations in associations map, does the input word relate to any of them? If it does, returns the base word.
	public String getAssociatedBaseWord(String word, HashMap<String, HashSet<String>> associationsMap){
		for (String key : associationsMap.keySet()){
			if (associationsMap.get(key).contains(word)){
				return key;
			}
		}return "";
	}
}
