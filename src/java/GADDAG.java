import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GADDAG extends Trie {
    private static Tile separator = new Tile('>');

    public GADDAG(){ root = new Node(Node.root); }

    /**
     * Add a word to our GADDAG. This involves splicing and reversing
     * subsections of the word to add them into the structure.
     * @param word the word to add
     */
    @Override
    public void add(String word){
        if (word.length() == 0) return;

        word = word.toUpperCase();

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

    public HashSet<WordPlay> findWordsWithRackAndHook(Tile[] rack, Tile hook){
        HashMap<String, Integer> words = new HashMap<>();
        Arrays.sort(rack);
        ArrayList<Tile> rackList = new ArrayList<>(Arrays.asList(rack));
        ArrayList<Tile> word = new ArrayList<>();

        if (hook.getContent() == ' '){
            Tile tile;
            while (rackList.size() > 1){
                tile = rackList.remove(0);
                findWordsRecurse(words, word,  rackList, tile, root, true);
            }
        } else if (rackList.contains(new Tile('_'))) {
            char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            ArrayList<Character> newRackList;
            for (char c : ch){
                newRackList = (ArrayList<Character>)rackList.clone();
                newRackList.remove('_');
                newRackList.add(c);

            }
        } else {
            findWordsRecurse(words, word, rackList, hook, root, true);
        }
        return words;
    }

    private void findWordsRecurse(HashMap<String, Integer> words, ArrayList<Tile> word, ArrayList<Tile> rack, Tile hook, Node cur, boolean direction){
        Node hookNode = cur.getChild(hook);

        //Base case
        if (hookNode == null)
            return;

        Tile hookCh = (hook == separator ? null : String.valueOf(hook); //Empty character if we're the separator
        word = (direction ? hookCh + word : word + hookCh); //Direction-based concatenation

        //if we've reached the end a word, add the word to output
        if (hookNode.getFinite()){
            Integer sum = 0;
            for (char c : word.toCharArray())
                sum += Tile.tileValueMap.get(c);
            if (words.containsKey(word) && words.get())

            words.put(word, sum);
        }
        for (char nodeKey : hookNode.getKeys()) {
            if (nodeKey == separator)
                findWordsRecurse(words, word, rack, separator, hookNode, false);
            else if (rack.contains(nodeKey)){
                //boolean duplicate = (rack.size() > 0 && (rack.get(nodeKey) == rack.get(rack.indexOf(nodeKey) - 1)));
                ArrayList<Tile> newRack = (ArrayList<Tile>) rack.clone();
                newRack.remove(new Tile(nodeKey));
                findWordsRecurse(words, word, newRack, nodeKey, hookNode, direction);
            }
        }
    }
}
