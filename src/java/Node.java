import java.util.Collection;
import java.util.HashMap;

public class Node extends Tile{
    public static char root = '~';
    private boolean finite = false;
    private HashMap<Character, Node> children = new HashMap<>();

    public void setFinite(boolean value){ finite = value; }
    public boolean getFinite(){ return finite; }

    public Node(char c){
        super(c);
        this.finite = false;
    }

    public Node(char c, boolean noValue){
        super(c, noValue);
        this.finite = false;
    }

    public Node(Tile t){
        super(t.content, (t.value == 0));
        this.finite = false;
    }


    /**
     * Get our child nodes from our Map
     * @return Some collection of child nodes, or null if empty;
     */
    public Collection<Node> getChildren(){
        return children.values();
    }

    /**
     * Does this node have a child that matches a tile?
     * @param tile the tile to check
     * @return true if child list contains such Node
     */
    public boolean hasChild(Tile tile) {
        return children.containsKey(tile.getContent());
    }

    /**
     * Does this node have a child that matches a character?
     * @param c the character to check
     * @return true if child list contains such Node
     */
    public boolean hasChild(char c) {
        return children.containsKey(c);
    }

    /**
     * return the child that corresponds to the content c2
     * @param tile the character to check against
     * @return a Node or null
     */
    public Node getChild(Tile tile){
        return children.get(tile.getContent());
    }

    /**
     * return the child that corresponds to the content c
     * @param c the character to check against
     * @return a Node or null
     */
    public Node getChild(char c){
        return children.get(c);
    }

    /**
     * Add a child to our Map of child Nodes
     * @param c the character to add
     */
    public void addChild(char c){
        if (c == GADDAG.separator) // if we're a separator node, let's use our GADDAG's separatorTile
            children.put(c, new Node(GADDAG.separatorTile));
        else
            children.put(c, new Node(c));
    }
}
