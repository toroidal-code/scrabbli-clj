

package li.scrabb;


import java.util.Collection;
import java.util.HashMap;


public class Node extends Tile {


    private boolean terminal;
    private HashMap<Character, Node> children;


    public Node(char label) {
        super(label);
        this.terminal = false;
        this.children = new HashMap<Character, Node>();
    }
    public Node(Tile tile) {
        this(tile.getLabel());
    }


    public void addChild(Node node) {
        this.children.put(node.getLabel(), node);
    }
    public void addChild(char label) {
        this.children.put(label, new Node(label));
    }

    public boolean hasChild(char label) {
        return this.getChildren().containsKey(label);
    }
    public boolean hasChild(Tile tile) {
        return this.hasChild(tile.getLabel());
    }

    public Node getChild(char label){
        return children.get(label);
    }
    public Node getChild(Tile tile){
        return this.getChild(tile.getLabel());
    }


    public boolean getTerminal() {
        return this.terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public HashMap<Character, Node> getChildren() {
        return this.children;
    }

    public Collection<Node> getChildValues() {
        return this.children.values();
    }
}
