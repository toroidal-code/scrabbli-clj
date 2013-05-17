import java.util.HashMap;

public class Tile implements Comparable<Tile> {
    public final static HashMap<Character,Integer> tileValueMap = new HashMap<Character,Integer>(){{
        put('a', 1); put('b', 3); put('c', 3); put('d', 2); put('e', 1);
        put('f', 4); put('g', 2); put('h', 4); put('i', 1); put('j', 8);
        put('k', 5); put('l', 1); put('m', 3); put('n', 1); put('o', 1);
        put('p', 3); put('q', 10);put('r', 1); put('s', 1); put('t', 1);
        put('u', 1); put('v', 4); put('w', 4); put('x', 8); put('y', 4);
        put('z', 10);
    }};

    protected int value;
    protected char content;

    public Tile(){}

    public Tile(char c, boolean blank){
        content = c;
        if (blank)
            value = 0;
        else if (tileValueMap.containsKey(c))
            value = tileValueMap.get(c);
        else
            value = 0;
    }

    public Tile(char c){
        content = c;
        if (tileValueMap.containsKey(c))
            value = tileValueMap.get(c);
        else value = 0;
    }

    public char getContent(){
        return content;
    }

    public int getValue(){
        return value;
    }

    public boolean equals(Object o){
        return o instanceof Tile && content == ((Tile) o).content;
    }

    @Override
    public int compareTo(Tile t) {
        return content - t.content;
    }

    /**
     * A hashCode for our Tile. Using value in addition to content allows
     * distinction between blanks and regular tiles
     * @return
     */
    @Override
    public int hashCode(){
        return (value + 1) * content;
    }

    /**
     * A basic toString, only returns our contents
     * @return the character the Tile holds
     */
    public String toString(){
        String letter = String.valueOf(content);
        if (value == 0)
            return letter.toLowerCase();
        else return letter;
    }

}
