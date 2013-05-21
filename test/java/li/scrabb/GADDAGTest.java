package li.scrabb;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GADDAGTest {
    private GADDAG gaddag = new GADDAG();

    @Test
    public void testAdd() throws Exception {
        assertEquals(gaddag.getRoot(), new Node('~'));
        gaddag.add("abs");
        Node node = gaddag.getRoot();
        assertThat(node.getChildren().keySet(), hasItems('a', 'b', 's'));
    }

    @Test
    public void testFindWordsWithRackAndHook() throws Exception {

    }
}
