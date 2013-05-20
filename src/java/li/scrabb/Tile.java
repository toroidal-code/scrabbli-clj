

package li.scrabb;


public class Tile implements Comparable<Tile> {

    protected int value;
    protected char label;


    public Tile(char label){
        this.label = label;
        this.value = 0; // FIXME
    }


    public int compareTo(Tile tile) {
        return this.label - tile.getLabel();
    }

    public boolean equals(Object object){
        return object instanceof Tile &&
            this.label == ((Tile)object).getLabel();
    }

    public int hashCode(){
        return this.label * this.value + 1;
    }

    public String toString(){
        return String.valueOf(this.label);
    }

    public int getValue() {
        return this.value;
    }

    public char getLabel() {
        return this.label;
    }
}
