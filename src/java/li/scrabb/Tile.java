

package li.scrabb;


public class Tile implements Comparable<Tile> {

    protected int value;
    protected char label;


    public Tile(char label){
        this.label = label;
        this.value = 0; // FIXME
    }


    @Implements
    public int compareTo(Tile tile) {
        return this.content - tile.getContent();
    }


    public boolean equals(Object object){
        return object instanceof Tile &&
            this.label == ((Tile)o).getLabel();
    }

    public int hashCode(){
        return this.content * this.value + 1;
    }

    public String toString(){
        return String.valueOf(this.content);
    }

    public int getValue() {
        return this.value;
    }
    public char getLabel() {
        return this.label;
    }
}
