import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Thesaurus {
	private HashMap<String, Synonyms> rootSynonymMap;
	
	Thesaurus(){
		this.rootSynonymMap = new HashMap<String, Synonyms>();
	}
	
	//build thesaurus from lines of root -> synonym mappings
	public static Thesaurus buildThesaurus(ArrayList<String> lines){
		Thesaurus thesaurus = new Thesaurus();
		
		for(String line : lines ){
			thesaurus.addMapping(line);
		}
		return thesaurus;
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
		
		public void add(String synonym){
			this.synonyms.add(synonym);
		}
		
		public void remove(String synonym){
			this.synonyms.remove(synonym);		
		}
		
		public boolean contains(String synonym){
			return this.synonyms.contains(synonym);
		}
		
		public Set<String> getSynonyms(){
			return Collections.unmodifiableSet(this.synonyms);
		}
	}
	
}
