

package li.scrabb;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Node is a subclass of Tile that is used for construction of a Trie (and thus, the li.scrabb.GADDAG). Because of this,
 * it adds a boolean terminal (a representation of an End Of Word), and a Collection of children that reside below
 * itself.
 */
public class Node extends Tile {


    private boolean terminal;  // The indicator if this Node is the end of a word
    private HashMap<Character, Node> children; //The list of children, in HashMap form for O(1) access time

    /**
     * Default initializer, sets terminal to False
     * @param label the letter the Node represents
     */
    public Node(char label) {
        super(label);
        this.terminal = false;
        this.children = new HashMap<>();
    }

    /**
     * Create a new Node based off of an input tile's content
     * @param tile the tile to use as a reference
     */
    public Node(Tile tile) {
        this(tile.getLabel());
    }

    /**
     * Add a Node to the Map of children
     * @param node the node to add
     */
    public void addChild(Node node) {
        this.children.put(node.getLabel(), node);
    }

    /**
     * Add a character (new Node) to the Map of children
     * @param label the character of the node to add
     */
    public void addChild(char label) {
        this.children.put(label, new Node(label));
    }

    /**
     * Checks for existance of a character in the children Map
     * @param label the character to check using
     * @return true or false
     */
    public boolean hasChild(char label) {
        return this.getChildren().containsKey(label);
    }

    /**
     * Check for existance of a Tile in the children Map
     * @param tile the Tile to check using
     * @return true or false
     */
    public boolean hasChild(Tile tile) {
        return this.hasChild(tile.getLabel());
    }

    /**
     * Retrieve a Node
     * @param label the character to get
     * @return a Node from the children Map corresponding to the character
     */
    public Node getChild(char label){
        return children.get(label);
    }

    /**
     * Retrieve a Node
     * @param tile the Tile to use as reference
     * @return a Node from the children Map whose label matches that of the input Tile
     */
    public Node getChild(Tile tile){
        return this.getChild(tile.getLabel());
    }

    /**
     * A query function whether this is a terminal node or not
     * @return true or false
     */
    public boolean getTerminal() {
        return this.terminal;
    }

    /**
     * A setter for the terminal flag on this Node
     * @param terminal the value to set to (true or false)
     */
    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    /**
     * Get the children Map
     * @return An unmodifiable version of the children Map
     */
    public Map<Character, Node> getChildren() {
        return Collections.unmodifiableMap(this.children);
    }

    /**
     * Get the children Nodes
     * @return A Collection (List) of the children Nodes
     */
    public Collection<Node> getChildValues() {
        return this.children.values();
    }
}
