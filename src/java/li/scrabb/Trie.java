

package li.scrabb;


import java.lang.Iterable;
import java.util.Collection;
import java.util.ArrayList;


public class Trie {

    public static char ROOT_LABEL = '~';

    protected Node root;
    protected long nodeCount;
    protected int wordCount;
    protected int maxDepth;

    private boolean _mem_clean_;
    private ArrayList<String> words;


    public Trie() {
        this.root = new Node(ROOT_LABEL);
        this.nodeCount = 1;
        this.wordCount = 0;
        this.maxDepth = 0;
        this._mem_clean_ = false;
    }


    public void add(String word) {
        Node current = this.getRoot();

        for (char c : word.toLowerCase().toCharArray()) {
            if (current.hasChild(c))
                current = current.getChild(c);
            else {
                current.addChild(c);
                current = current.getChild(c);
                this.nodeCount++;
            }
        }

        current.setTerminal(true);

        if (word.length() > this.getMaxDepth())
            this.setMaxDepth(word.length());

        this._mem_clean_ = false;
        this.wordCount++;
    }
    public void add(Iterable<String> words) {
        for (String word : words)
            this.add(word);
    }


    public Node find(String prefix) {
        Node node = this.getRoot();
        for (char c: prefix.toLowerCase().toCharArray()) {
            if (node.hasChild(c))
                node = node.getChild(c);
            else
                return null;
        }

        return node;
    }
    public boolean contains(String word) {
        Node node = this.find(word);
        return node != null && node.getTerminal();
    }


    public Collection<String> getWords() {
        if (this._mem_clean_)
            return this.words;

        ArrayList words = new ArrayList<String>(this.getWordCount());
        getWords("", this.getRoot(), words);

        this.words = words;
        this._mem_clean_ = true;

        return words;
    }
    private void getWords(String word, Node current, ArrayList<String> words) {
        if (current.getTerminal())
            words.add(word);

        if (current.getChildren() != null)
            for (Node node : current.getChildValues())
                getWords(word + node.getLabel(), node, words);
    }


    public Node getRoot() {
        return this.root;
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
}
