package li.scrabb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GADDAG extends Trie {
    public static char separator = '>';

    public GADDAG(){ root = new Node('~'); }

    /**
     * Add a word to our li.scrabb.GADDAG. This involves splicing and reversing
     * subsections of the word to add them into the structure.
     * @param word the word to add
     */
    @Override
    public void add(String word){
        if (word.length() != 0) {
            word = word.toLowerCase();

            char[] ch;
            int i;
            for (i = 1; i < word.length(); i++) {
                ch = word.substring(0, i).toCharArray();
                reverse(ch);
                super.add(new String(ch) + separator + word.substring(i));
            }
            ch = word.toCharArray();
            reverse(ch);
            super.add(new String(ch) + separator + word.substring(i));
        }
    }

    @Override
    public boolean contains(String word){
        Node node = this.getRoot().getChild(word.charAt(0)); //first we descend into the hook
        word = word.substring(1, word.length() - 1); // splice the word
        if (node != null) node = node.getChild('>'); // then we descend into the reversal character
        else return false;

        for (char c : word.toLowerCase().toCharArray()){
            if (node.hasChild(c))
                node = node.getChild(c);
            else
                return false;
        }
        return false;
    }


    /**
     * Reverse a character array in-place
     * @param chars the array to reverse
     */
    private static void reverse(char[] chars){
        if (chars != null) {
            for (int i = 0; i < chars.length / 2; i++) {
                int temp = chars[i];
                chars[i] = chars[chars.length - i - 1];
                chars[chars.length - i - 1] = (char) temp;
            }
        }
    }

    public static ArrayList<Tile> stringToTileArray(String s){
        ArrayList<Tile> rack = new ArrayList<>();
        for (char c : s.toCharArray())
            rack.add(new Tile(c));
        return rack;
    }

    public Set<WordPlay> findWordsWithRackAndHook(ArrayList<Tile> rackList, Tile hook){
        HashSet<WordPlay> words = new HashSet<>();
        Collections.sort(rackList);
        WordPlay word = new WordPlay(hook);

        if (hook.getLabel() == 32){
            Tile tile;
            while (rackList.size() > 1){
                tile = rackList.remove(0);
                word = new WordPlay(tile);
                findWordsRecurseCheckBlanks(words, word, rackList, tile);
            }
        } else {
            findWordsRecurseCheckBlanks(words, word, rackList, hook);
        }
        return words;
    }

    private void findWordsRecurseCheckBlanks(HashSet<WordPlay> words, WordPlay word, ArrayList<Tile> rack, Tile hook){
        if (rack.contains(new Tile('_')) || hook.getLabel() == '_') {
            char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            ArrayList<Tile> newRack;
            WordPlay newWord;
            for (char c : ch){
                newRack = new ArrayList<>(rack);
                newRack.remove(new Tile('_', true)); //remove our '_' placeholder
                Tile blank = new Tile(c, true);
                if (hook.getLabel() != '_') //if we don't already have a blank as our hook
                    newRack.add(blank); //add the blank tile to our rack
                newWord = word.getHook().getLabel() == '_' ? new WordPlay(blank) : new WordPlay(word); //don't have the last word object modified by our new state.
                findWordsRecurse(words, newWord , newRack, root.getChild(hook.getLabel() == '_' ? blank : hook), true);
            }
        } else {
            findWordsRecurse(words, word, rack, root.getChild(hook), true);
        }
    }

    private void findWordsRecurse(HashSet<WordPlay> words, WordPlay word, ArrayList<Tile> rack, Node cur, boolean direction){

        //Base case
        if (cur == null)
            return;

        //The filtering to skip adding the separator to our li.scrabb.WordPlay word is now done in li.scrabb.WordPlay
        if (!cur.equals(root.getChild(cur))) // if we're not a hook (cause we're already in our li.scrabb.WordPlay)
            if (direction)
                word.addLeft(cur); //add in reverse
            else
                word.addRight(cur); //add forward

        //if we've reached the end a word, add the word to output
        if (cur.getTerminal())
            words.add(word);

        WordPlay newWord ;
        for (Node node : cur.getChildValues()) {
            newWord = new WordPlay(word); //we need to not have the 'word' object filter up scope
            if (node.getLabel() == separator)
                findWordsRecurse(words, newWord, rack, cur.getChild(node), false);
            else if (rack.contains(node)){
                //boolean duplicate = (rack.size() > 0 && (rack.get(nodeKey) == rack.get(rack.indexOf(nodeKey) - 1)));
                ArrayList<Tile> newRack = new ArrayList<>(rack);
                newRack.remove(new Tile(node.getLabel()));
                findWordsRecurse(words, newWord, newRack, cur.getChild(node), direction);
            }
        }
    }
}
