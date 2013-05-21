package li.scrabb;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GADDAGTest {
    private GADDAG gaddag = new GADDAG();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAdd() throws Exception {
        gaddag.add("abs");
        assertEquals(gaddag.getRoot(), new Node('~'));
        Node node = gaddag.getRoot();
        assertThat(node.getChildren().keySet(), hasItems('a', 'b', 's'));
    }

    @Test
    public void testFindWordsWithRackAndHook() throws Exception {

    }
}
