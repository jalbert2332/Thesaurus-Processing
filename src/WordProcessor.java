import java.util.HashSet;
import java.util.Set;

public class WordProcessor {
	public Thesaurus thesaurus;
	
	WordProcessor(Thesaurus thesaurus){
		this.thesaurus = thesaurus;
	}
	
	public Set<String> getAssociations(String baseWord, int levels){
		HashSet<String> associations = new HashSet<String>();
		Set<String> rootsAtLevel = new HashSet<String>();
		Set<String> nextLevel = new HashSet<String>();
		Set<String> synonyms;
		
		rootsAtLevel.add(baseWord); //seed the list of roots with our base
		for (int i = 1 ; i <= levels ; i++){ //search the specified number of levels
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
}
