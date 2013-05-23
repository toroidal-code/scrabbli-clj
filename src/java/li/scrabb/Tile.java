

package li.scrabb;


/**
 * Tile is an object representation of the basic Scrabble tile. It contains a
 * character and a score value associated with it. This covers all cases, as
 * once blank tiles are played, they immediately have a character association,
 * but no score value. Before they are played, they are represented with a Tile
 * containing '_'.  Tile implements Comparable to make natural ordering
 * alphabetical.
 */
public class Tile implements Comparable<Tile> {

    protected int value; // The value of the tile (its' score)
    protected char label; // The character of the tile [a-z]

    /**
     * The default constructor
     * @param label The character to represent
     */
    public Tile(char label){
        this.label = label;
        this.value = 0; // FIXME
    }

    public Tile(char label, boolean blank){
        this(label);
        if (blank)
            this.value = 0;
    }

    /**
     * Comparison function for Tiles
     * @param tile The tile to compare to
     * @return a signed integer representation of the difference between the two
     * Tiles
     */
    @Override
    public int compareTo(Tile tile) {
        return this.label - tile.getLabel();
    }

    /**
     * Equality checker for Tiles
     * @param object the object to compare to
     * @return true or false
     */
    @Override
    public boolean equals(Object object){
        return object instanceof Tile &&
            this.label == ((Tile)object).getLabel() &&
                this.value == ((Tile)object).getValue();
    }

    /**
     * The hashing function for Tile objects
     * @return an integer representation of the Tile
     */
    @Override
    public int hashCode(){
        return this.label * this.value + 1;
    }

    /**
     * A simple toString function
     * @return a String representation of the Tile
     */
    @Override
    public String toString(){
        if (value == 0)
            return String.valueOf(this.label).toLowerCase();
        else
            return String.valueOf(this.label).toUpperCase();
    }

    /**
     * Getter for the value of a Tile
     * @return the value (score)
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Getter for the label of the tile
     * @return the character of the tile
     */
    public char getLabel() {
        return this.label;
    }
}
