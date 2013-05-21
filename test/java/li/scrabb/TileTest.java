package li.scrabb;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TileTest {
    private Tile tile = new Tile('a');
    private Tile blank = new Tile('a', true);

    @Test
    public void testCompareTo() throws Exception {
        assertThat("A is greater than B", tile.compareTo(new Tile('b')), is(equalTo(-1)));
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue("A equals A", tile.equals(new Tile('a')));
        //assertFalse("A is not equal to a", tile.equals(blank)); //FIXME
    }

    @Test
    public void testToString() throws Exception {
        //assertThat("A normal prints uppercase", tile.toString(), is(equalTo("A"))); //FIXME
        assertThat("A blank prints lowercase", blank.toString(), is(equalTo("a")));
    }

    @Test
    public void testGetValue() throws Exception {
        //assertThat("A normal tile has value", tile.getValue(), is(not(equalTo(0)))); //FIXME
        assertThat("A blank has no value", blank.getValue(), is(equalTo(0)));
    }

    @Test
    public void testGetLabel() throws Exception {
        assertThat(tile.getLabel(), is(equalTo('a')));
        assertThat(blank.getLabel(), is(equalTo('a')));
    }
}
