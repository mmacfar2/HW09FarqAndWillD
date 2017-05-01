//** Instatiate the BSTMap to test*/
//mmacfar2 Matthew MacFarquhar mmacfar2@jhu.edu
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Iterator;
public class HashMapTest extends MapTestBase {
    @Override
    protected Map<String, Integer> createMap() {
        return new HashMap<>();
    }

    @Test
    public void toStringWorks() {
        m.insert(KEY_ONE, VAL_ONE);
	m.insert(KEY_TWO, VAL_TWO);
	m.insert(KEY_THREE, VAL_THREE);
	m.insert(KEY_FOUR, VAL_FOUR);
	assertEquals("{A: 39\nB: 75\nC: 42\nD: 11}", m.toString());
    }

    @Test
    public void iteratorWorks() {
        m.insert(KEY_ONE, VAL_ONE);
	m.insert(KEY_TWO, VAL_TWO);
	m.insert(KEY_THREE, VAL_THREE);
	m.insert(KEY_FOUR, VAL_FOUR);
	Iterator<String> iter = m.iterator();
	assertEquals("A", iter.next());
	assertEquals("B", iter.next());
	assertEquals("C", iter.next());
	assertEquals("D", iter.next());
	
    }
}
