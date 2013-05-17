import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class GADDAG extends Trie {
    public static Tile separatorTile = new Tile('>', true);
    public static char separator = '>';

    public GADDAG(){ root = new Node(Node.root); }

    /**
     * Add a word to our GADDAG. This involves splicing and reversing
     * subsections of the word to add them into the structure.
     * @param word the word to add
     */
    @Override
    public void add(String word){
        if (word.length() == 0) return;

        word = word.toLowerCase();

        String prefix;
        char[] ch;
        int i;
        for (i = 1; i < word.length(); i++){
            prefix = word.substring(0,i);
            ch = prefix.toCharArray();
            reverse(ch);
            super.add(new String(ch) + separator + word.substring(i));
        }
        ch = word.toCharArray();
        reverse(ch);
        super.add(new String(ch) + separator + word.substring(i));
    }

    /**
     * Reverse a character array in-place
     * @param chars the array to reverse
     */
    private void reverse(char[] chars){
        if (chars == null) return;
        for(int i = 0; i < chars.length/2; i++)
        {
            int temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = (char)temp;
        }
    }

    public static ArrayList<Tile> stringToTileArray(String s){
        ArrayList<Tile> rack = new ArrayList<>();
        for (char c : s.toCharArray())
            rack.add(new Tile(c));
        return rack;
    }

    public TreeSet<WordPlay> findWordsWithRackAndHook(ArrayList<Tile> rackList, Tile hook){
        TreeSet<WordPlay> words = new TreeSet<>();
        Collections.sort(rackList);
        WordPlay word = new WordPlay(hook);

        if (hook.getContent() == 32){
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

    private void findWordsRecurseCheckBlanks(TreeSet<WordPlay> words, WordPlay word, ArrayList<Tile> rack, Tile hook){
        if (rack.contains(new Tile('_')) || hook.getContent() == '_') {
            char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            ArrayList<Tile> newRack;
            WordPlay newWord;
            for (char c : ch){
                newRack = (ArrayList<Tile>)rack.clone();
                newRack.remove(new Tile('_', true)); //remove our '_' placeholder
                Tile blank = new Tile(c, true);
                if (hook.getContent() != '_') //if we don't already have a blank as our hook
                    newRack.add(blank); //add the blank tile to our rack
                newWord = (word.getHook().getContent() == '_') ? new WordPlay(blank) : new WordPlay(word); //don't have the last word object modified by our new state.
                findWordsRecurse(words, newWord , newRack, (hook.getContent() == '_' ? blank : hook), root, true);
            }
        } else {
            findWordsRecurse(words, word, rack, hook, root, true);
        }
    }

    private void findWordsRecurse(TreeSet<WordPlay> words, WordPlay word, ArrayList<Tile> rack, Tile next, Node cur, boolean direction){
        Node nextNode = cur.getChild(next); //grab the Node representation of our next Tile in our diving

        //Base case
        if (nextNode == null)
            return;

        //The filtering to skip adding the separator to our WordPlay word is now done in WordPlay
        if (nextNode != root.getChild(nextNode)) // if we're not a hook (cause we're already in our WordPlay)
            if (direction)
                word.addLeft(nextNode); //add in reverse
            else
                word.addRight(nextNode); //add forward

        //if we've reached the end a word, add the word to output
        if (nextNode.getFinite())
            words.add(word);

        WordPlay newWord ;
        for (Node node : nextNode.getChildren()) {
            newWord = new WordPlay(word); //we need to not have the 'word' object filter up scope
            if (node.getContent() == separator)
                findWordsRecurse(words, newWord, rack, node, nextNode, false);
            else if (rack.contains(node)){
                //boolean duplicate = (rack.size() > 0 && (rack.get(nodeKey) == rack.get(rack.indexOf(nodeKey) - 1)));
                ArrayList<Tile> newRack = (ArrayList<Tile>) rack.clone();
                newRack.remove(new Tile(node.getContent()));
                findWordsRecurse(words, newWord, newRack, node, nextNode, direction);
            }
        }
    }
}
