package li.scrabb;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TrieTest {
    private Trie trie = new Trie();

    @Before
    public void beforeTest(){
       trie = new Trie();
    }

    @Test
    public void testGetRoot() throws Exception {
        assertThat("The root content is ~", trie.getRoot().getLabel(), is(equalTo('~')));
    }

    @Test
    public void testAdd() throws Exception {
        String test = "abs";
        trie.add(test);
        Node node = trie.getRoot();
        assertThat("The root content is ~", node.getLabel(), is(equalTo('~')));
        for (char c : test.toCharArray()){
            node = node.getChild(c);
            assertThat("The next content is " + c, node.getLabel(), is(equalTo(c)));
        }
    }

    @Test
    public void testFind() throws Exception {
        trie.add("abs");
        Node node = new Node('s');
        node.setTerminal(true);
        assertThat("The test node and the ending node in the trie are equal", trie.find("abs"), is(equalTo(node)));
    }

    @Test
    public void testContains() throws Exception {
        trie.add("abs");
        trie.add("absolute");
        trie.add("impervious");
        assertTrue("The trie contains abs", trie.contains("abs"));
        assertTrue("The trie contains absolute", trie.contains("absolute"));
        assertTrue("The trie contains impervious", trie.contains("impervious"));
    }

    @Test
    public void testGetWords() throws Exception {

    }

    @Test
    public void testGetWordCount() throws Exception {
        trie.add("abs");
        trie.add("absolute");
        trie.add("impervious");
        assertThat("The word count of the Trie is equal to the number of words added",
                trie.getWordCount(), is(equalTo(3)));
    }

    @Test
    public void testGetMaxDepth() throws Exception {
        String word = "somelongword";
        trie.add(word);
        assertThat("The depth of a Trie is equal to the longest word", trie.getMaxDepth(), is(equalTo(word.length())));
    }
}
