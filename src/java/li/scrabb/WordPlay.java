package li.scrabb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("MethodWithMultipleLoops")
public class WordPlay implements Cloneable, Comparable<WordPlay> {
    private final Tile hook;
    private List<Tile> wordRight = new ArrayList<>();
    private List<Tile> wordLeft = new ArrayList<>();

    public WordPlay(Tile hook){
        this.hook = hook;
    }

    public WordPlay(WordPlay wp){
        hook = wp.hook;
        for (Tile t : wp.wordLeft) {
            this.wordLeft.add(t);
        }
        for (Tile t : wp.wordRight) {
            this.wordRight.add(t);
        }
    }

    public Object clone(){
        return new WordPlay(this);
    }

    static Comparator<WordPlay> scoreComparator(){
        return (w1, w2) -> w1.getScore() - w2.getScore();
    }

    static Comparator<WordPlay> alphaComparator(){
        return (w1, w2) -> w2.toString().compareTo(w2.toString());
    }

    public void addRight(Tile t){
        if (t.getLabel() != GADDAG.separator) { //We need to skip adding the separator to our li.scrabb.WordPlay word
            wordRight.add(t);
        }
    }

    public void addLeft(Tile t){
        if (t.getLabel() != GADDAG.separator) { //We need to skip adding the separator to our li.scrabb.WordPlay word
            wordLeft.add(t);
        }
    }

    /**
     * Return the cumulative score of the word
     * @return an int of the score
     */
    public int getScore(){
        int sum = hook.getValue();
        for (Tile word : wordLeft) sum += word.getValue();
        for (Tile word : wordRight) sum += word.getValue();
        return sum;
    }

    public Tile getHook(){
        return hook;
    }

    @Override
    public String toString(){
        String word = hook.toString();
        for (Tile t : wordLeft)
            word = t.toString() + word;
        for (Tile t: wordRight)
            word += t.toString();
        return word;
    }

    /**
     * Equality checker for li.scrabb.WordPlay
     * @param o the Object to check against
     * @return a boolean, whether or not our states are equivalent
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof WordPlay)) //first check if we're even an instance
            return false;
        WordPlay wp = (WordPlay)o; //then we can cast
        if (!hook.equals(wp.hook)) //if our hook's aren't equivalent
            return false;

        //then check our sub-set sizes
        if (wordLeft.size() != wp.wordLeft.size() || wordRight.size() != wp.wordRight.size())
            return false;

        // now check our sub-sets' contents
        boolean equality = true;
        for (int i = 0; i < wordLeft.size(); i++)
            equality &= wordLeft.get(i).equals( wp.wordLeft.get(i) );
        for (int i = 0; i < wordRight.size(); i++)
            equality &= wordRight.get(i).equals( wp.wordRight.get(i) );
        return equality;
    }

    /**
     * A hashing function which takes letter position in the word into consideration
     * @return an int representation of the li.scrabb.WordPlay object
     */
    @Override
    public int hashCode(){
        int product = hook.hashCode();
        for (int i = 0; i < wordLeft.size(); i++)
            product *= wordLeft.get(i).hashCode() + i;
        for (int i = 0; i < wordRight.size(); i++)
            product *= wordRight.get(i).hashCode() + i;
        return product;
    }

    @Override
    public int compareTo(WordPlay o) {
        return this.toString().compareTo(o.toString());
    }
}
