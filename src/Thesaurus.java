import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Thesaurus {
	private HashMap<String, Synonyms> rootSynonymMap;
	
	Thesaurus(){
		this.rootSynonymMap = new HashMap<String, Synonyms>();
	}
	
	//The input expected is the absolute path to a file of lines where each line is a mapping of a root word to its descendants
    //Each line is a comma delimited list where the first word is the root and all others synonyms
	//For example: "root,synonym1,synonym2,synonym3..."
	public void buildThesaurusFromFile(String absoluteFilePath) {
		this.rootSynonymMap.clear();
		Parser parsedFile = new Parser(absoluteFilePath);
		buildThesaurus(parsedFile.getLines());
	}
	
	//The input expected is a list of lines where each line is a mapping of a root word to its descendants
	//Each line is a comma delimited list where the first word is the root and all others synonyms
	//For example: "root,synonym1,synonym2,synonym3..."
	private void buildThesaurus(List<String> lines){
		// for each word, map it to its synonyms
		for(String line : lines ){
			this.addMapping(line);
		}
	}
	
	//add a root to synonym mapping to the thesaurus
	 void addMapping(String line){
		String words[] = line.split(",");
		String root = words[0];
		this.rootSynonymMap.put(root, this.addSynonyms(words));
	}
	
	//go from list of synonyms to a set of synonyms
	private Synonyms addSynonyms(String[] words){
		Synonyms synonyms = new Synonyms();
		for( int i = 1 ; i < words.length ; i++ ){
			if ( synonyms.contains(words[i]) ){
				continue;
			}
			synonyms.add(words[i]);
		}
		return synonyms;
	}
	
	//return an unmodifiable set of synonyms that can be iterated over
	public Set<String> getSynonyms(String root){
		if(this.rootSynonymMap.containsKey(root)){
			return this.rootSynonymMap.get(root).getSynonyms();
		}return null;
	}
	
	public class Synonyms{
		private HashSet<String> synonyms;
		
		Synonyms(){
			this.synonyms = new HashSet<String>();
		}
		
		private void add(String synonym){
			this.synonyms.add(synonym);
		}
		
		private boolean contains(String synonym){
			return this.synonyms.contains(synonym);
		}
		
		public Set<String> getSynonyms(){
			return Collections.unmodifiableSet(this.synonyms);
		}
	}
	
}
