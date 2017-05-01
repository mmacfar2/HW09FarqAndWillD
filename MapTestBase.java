import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Iterator;

//Matthew MacFarquhar mmacfar2 mmacfar2@jhu.edu

/**
 *Testing implementation for maps.
 */

public abstract class MapTestBase {
    protected Map<String, Integer> m;
    protected abstract Map<String, Integer> createMap();
    protected final String KEY_ONE = "A";
    protected final Integer VAL_ONE = 39;
    protected final String KEY_TWO = "B";
    protected final Integer VAL_TWO = 75;
    protected final String KEY_THREE = "C";
    protected final Integer VAL_THREE = 42;
    protected final String KEY_FOUR = "D";
    protected final Integer VAL_FOUR = 11;
    protected final String KEY_FIVE = "E";
    protected final Integer VAL_FIVE = 22;
    protected final String KEY_SIX = "F";
    protected final Integer VAL_SIX = 102;
    protected final String KEY_SEVEN = "G";
    protected final Integer VAL_SEVEN = 219;
    protected final String KEY_EIGHT = "H";
    protected final Integer VAL_EIGHT = 7;
    protected final String KEY_NINE = "I";
    protected final Integer VAL_NINE = 89;
    
    @Before
    public void setUpMap() {
        m = createMap();
    }

    @Test
    public void sizeIsZero() {
	assertEquals(0, m.size());
    }

    @Test
    public void doesntHaveNonExistentKey() {
        assertEquals(false, m.has(KEY_ONE));
    }

    @Test
    public void doesntHaveNullKey() {
        assertEquals(false, m.has(null));
    }

    @Test
    public void hasAfterInserted() {
        m.insert(KEY_ONE, VAL_ONE);
    }

    @Test
    public void hasDoesntAlterSize() {
        m.insert(KEY_ONE, VAL_ONE);
	m.has(KEY_ONE);
	assertEquals(1, m.size());
    }

    @Test
    public void getsRightValue() {
	m.insert(KEY_ONE, VAL_ONE);
	assertEquals(VAL_ONE, m.get(KEY_ONE));
    }

    @Test(expected=IllegalArgumentException.class)
    public void getWhenNotMapped() {
        m.get(KEY_ONE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void getWhenNull() {
        m.get(null);
    }

    @Test
    public void getDoesntAlterSize() {
        m.insert(KEY_ONE, VAL_ONE);
	m.get(KEY_ONE);
	assertEquals(1, m.size());
    }

    @Test
    public void putChangesValue() {
	m.insert(KEY_ONE, VAL_ONE);
	assertEquals(VAL_ONE, m.get(KEY_ONE));
	m.put(KEY_ONE, VAL_TWO);
	assertEquals(VAL_TWO, m.get(KEY_ONE));
    }

    @Test(expected=IllegalArgumentException.class)
    public void putWhenNotMapped() {
        m.put(KEY_ONE, VAL_TWO);
    }

    @Test(expected=IllegalArgumentException.class)
    public void putWhenNull() {
        m.put(null, VAL_TWO);
    }

    @Test
    public void putDoesntAlterSize() {
        m.insert(KEY_ONE, VAL_ONE);
	m.put(KEY_ONE, VAL_TWO);
	assertEquals(1, m.size());
    }

    @Test
    public void keyDecrement() {
	m.insert(KEY_ONE, VAL_ONE);
	m.remove(KEY_ONE);
	assertEquals(0, m.size());
    }

    @Test (expected=IllegalArgumentException.class)
    public void nullKey() {
        m.insert(KEY_ONE, VAL_ONE);
	m.remove(null);
    }

    @Test (expected=IllegalArgumentException.class)
    public void nonExistentKey() {
        m.remove(KEY_ONE);
    }

    @Test
    public void removeGetsRightVal() {
        m.insert(KEY_ONE, VAL_ONE);
	assertEquals(VAL_ONE, m.remove(KEY_ONE));
    }

    @Test
    public void keyIsRemoved() {
        m.insert(KEY_ONE, VAL_ONE);
	m.remove(KEY_ONE);
	assertEquals(false, m.has(KEY_ONE));
    }

    @Test
    public void insertIncrements() {
        m.insert(KEY_ONE, VAL_ONE);
	assertEquals(1, m.size());
    }

    @Test
    public void insertHasRightValue() {
        m.insert(KEY_ONE, VAL_ONE);
	assertEquals(VAL_ONE, m.get(KEY_ONE));
    }

    @Test
    public void insertsKey() {
        m.insert(KEY_ONE, VAL_ONE);
	assertEquals(true, m.has(KEY_ONE));
    }

    @Test (expected=IllegalArgumentException.class)
    public void insertNullKey() {
        m.insert(null, VAL_ONE);
    }

    @Test (expected=IllegalArgumentException.class)
    public void keyALreadyInMap() {
        m.insert(KEY_ONE, VAL_ONE);
	m.insert(KEY_ONE, VAL_TWO);
    }
}
