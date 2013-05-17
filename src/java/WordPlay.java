import java.util.ArrayList;

public class WordPlay {
    private Tile hook;
    private ArrayList<Tile> wordRight = new ArrayList<>();
    private ArrayList<Tile> wordLeft = new ArrayList<>();

    public WordPlay(Tile hook){
        this.hook = hook;
    }

    public WordPlay(WordPlay wp){
        this.hook = wp.hook;
        this.wordLeft = wp.wordLeft;
        this.wordRight = wp.wordRight;
    }

    public void addRight(Tile t){
        wordRight.add(t);
    }

    public void addLeft(Tile t){
        wordLeft.add(t);
    }

    /**
     * Return the cumulative score of the word
     * @return an int of the score
     */
    public int getScore(){
        int sum = hook.value;
        for (Tile word : wordLeft) sum += word.getValue();
        for (Tile word : wordRight) sum += word.getValue();
        return sum;
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
     * Equality checker for WordPlay
     * @param o the Object to check against
     * @return a boolean, whether or not our states are equivalent
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof WordPlay)) return false; //first check if we're even an instance
        WordPlay wp = (WordPlay)o; //then we can cast
        if (!hook.equals(wp.hook)) //if our hook's aren't equivalent
            return false;

        if (wordLeft.size() != wp.wordLeft.size() || wordRight.size() != wp.wordRight.size()) //then check our sub-set sizes
            return false;

        // now check our sub-sets' contents
        boolean equality = true;
        for (int i = 0; i < wordLeft.size(); i++)
            equality &= ( wordLeft.get(i).equals( wp.wordLeft.get(i) ) );
        for (int i = 0; i < wordRight.size(); i++)
            equality &= ( wordRight.get(i).equals( wp.wordRight.get(i) ) );
        return equality;
    }

    /**
     * A hashing function which takes letter position in the word into consideration
     * @return an int representation of the WordPlay object
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
}
