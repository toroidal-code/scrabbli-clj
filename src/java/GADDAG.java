import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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

    public HashSet<WordPlay> findWordsWithRackAndHook(ArrayList<Tile> rackList, Tile hook){
        HashSet<WordPlay> words = new HashSet<>();
        Collections.sort(rackList);
        WordPlay word = new WordPlay(hook);

        if (hook.getContent() == 32){
            Tile tile;
            while (rackList.size() > 1){
                tile = rackList.remove(0);
                word = new WordPlay(tile);
                findWordsRecurse(words, word,  rackList, tile, root, true);
            }
        } else {
            findWordsRecurse(words, word, rackList, hook, root, true);
        }
        return words;
    }

    private void findWordsRecurse(HashSet<WordPlay> words, WordPlay word, ArrayList<Tile> rack, Tile next, Node cur, boolean direction){
        if (rack.contains(new Tile('_'))) {
            char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            ArrayList<Tile> newRack;
            for (char c : ch){
                newRack = (ArrayList<Tile>)rack.clone();
                newRack.remove(new Tile('_', true)); //remove our '_' placeholder
                newRack.add(new Tile(c, true)); //add a blank tile
                findWordsRecurse(words, word, newRack, next, root, true);
            }
        }

        Node nextNode = cur.getChild(next); //grab the Node representation of our next Tile in our diving

        //Base case
        if (nextNode == null)
            return;

        //The filtering to skip adding the separator to our WordPlay word is now done in WordPlay
        if (direction)
            word.addLeft(nextNode); //add in reverse
        else
            word.addRight(nextNode); //add forward

        //if we've reached the end a word, add the word to output
        if (nextNode.getFinite())
            words.add(word);

        for (Node node : nextNode.getChildren()) {
            if (node.getContent() == separator)
                findWordsRecurse(words, word, rack, node, nextNode, false);
            else if (rack.contains(node)){
                //boolean duplicate = (rack.size() > 0 && (rack.get(nodeKey) == rack.get(rack.indexOf(nodeKey) - 1)));
                ArrayList<Tile> newRack = (ArrayList<Tile>) rack.clone();
                newRack.remove(new Tile(node.getContent()));
                findWordsRecurse(words, word, newRack, node, nextNode, direction);
            }
        }
    }
}
