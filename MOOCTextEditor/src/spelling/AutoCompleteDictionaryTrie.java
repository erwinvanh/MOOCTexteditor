package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
	private List completions;

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
        if (word == null) return false;
				
		TrieNode t;
		t = root;
		for (int i=0; i<word.length(); i++) {
			char c = word.toLowerCase().charAt(i);
			if (t.getChild(c) != null) {
				t = t.getChild(c);
			}
			else { t.insert(c); 
			       t = t.getChild(c);
			}
			}
        // We are at the end, is this a word. If not mark it as a word ? 
		if (!t.endsWord()) {
			t.setEndsWord(true);
			size ++;
			return true;
		}
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		// No point in searching if string is null or if dictionary is empty
        if (s == null || size == 0) return false;
				
		TrieNode t;
		t = root;
		for (int i=0; i<s.length(); i++) {
			char c = s.toLowerCase().charAt(i);
			if (t.getChild(c) != null) {
				t = t.getChild(c);
			}
			else { return false;
			}
			}
       	return t.endsWord();
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // Create empty list
    	 List<String> completions = new ArrayList<String>();
    	 // No point in searching when input is empty
    	 if (prefix == null || size == 0) return completions;
         TrieNode t;
         t = root;
         int returnedCompletions = 0;
 		 for (int i=0; i<prefix.length(); i++) {
 		  	 char c = prefix.toLowerCase().charAt(i);
 		 	 if (t.getChild(c) != null) {
 			 	t = t.getChild(c);
 			 }
 			 else { return completions;
 			 }
 	     }

 		 LinkedList<TrieNode> queue; 
 		 queue = new LinkedList<TrieNode>();
         // Add the first element to the list
 		 queue.add(t);

 		 while (returnedCompletions < numCompletions) {
 			 if (queue.size() > 0) {
 				 TrieNode h = queue.removeFirst();
 				 // Add all children to the queue
 				 for (Character c : h.getValidNextCharacters()) {
 					 queue.add(h.getChild(c)); }
 				 // if this is a word, at it to the list
 	 			 if (h.endsWord()){
 	 				 completions.add(h.getText());
 	 				 returnedCompletions ++;
 	 			 }
 			 } else return completions;
 		 }
        return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}