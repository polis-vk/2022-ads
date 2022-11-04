package company.vk.polis.ads.bst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic binary search tree invariants.
 */
class AvlBinarySearchTreeTest {

    BinarySearchTree<String, String> newAvlBst() {
        return new AvlBinarySearchTree<>();
    }

    @Test
    void emptyBst() {
        BinarySearchTree<String, String> bst = newAvlBst();
        assertNull(bst.get(""));
        assertNull(bst.get("some key"));
        assertEquals(0, bst.size());
        assertEquals(0, bst.height());
    }

    @Test
    void orderedOnEmpty() {
        BinarySearchTree<String, String> bst = newAvlBst();
        assertNull(bst.ceil("some key"));
        assertNull(bst.floor("some key"));

        assertNull(bst.min());
        assertNull(bst.max());

        assertNull(bst.minValue());
        assertNull(bst.maxValue());
    }

    @Test
    void put() {
        BinarySearchTree<String, String> bst = newAvlBst();
        bst.put("foo", "bar");

        assertEquals("bar", bst.get("foo"));

        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
    }

    @Test
    void replace() {
        BinarySearchTree<String, String> bst = newAvlBst();
        bst.put("foo", "bar");
        bst.put("foo", "bee");

        assertEquals("bee", bst.get("foo"));

        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
    }

    @Test
    void morePut() {
        BinarySearchTree<String, String> bst = newAvlBst();

        int size = 0;
        assertEquals(bst.size(), size);
        assertNull(bst.max());
        assertNull(bst.min());
        assertNull(bst.get("testStringKey1"));

        bst.put("testStringKey1", "testStringValue1");

        assertEquals(bst.size(), ++size);
        assertEquals(bst.min(), "testStringKey1");
        assertEquals(bst.max(), "testStringKey1");
        assertEquals(bst.get("testStringKey1"), "testStringValue1");

        bst.put("testStringKey2", "testStringValue2");

        assertEquals(bst.size(), ++size);
        assertEquals(bst.min(), "testStringKey1");
        assertEquals(bst.max(), "testStringKey2");
        assertEquals(bst.maxValue(), "testStringValue2");
        assertEquals(bst.get("testStringKey2"), "testStringValue2");

        bst.put("testStringKey2", "case with same value");

        assertEquals(bst.size(), size);
        assertEquals(bst.min(), "testStringKey1");
        assertEquals(bst.max(), "testStringKey2");
        assertEquals(bst.maxValue(), "case with same value");
        assertEquals(bst.get("testStringKey2"), "case with same value");

        bst.put("testStringKey3", "testStringValue3");

        assertEquals(bst.size(), ++size);
        assertEquals(bst.min(), "testStringKey1");
        assertEquals(bst.max(), "testStringKey3");
        assertEquals(bst.get("testStringKey3"), "testStringValue3");

        bst.put("testStringKey", "testStringValue");

        assertEquals(bst.size(), ++size);
        assertEquals(bst.min(), "testStringKey");
        assertEquals(bst.max(), "testStringKey3");
        assertEquals(bst.get("testStringKey"), "testStringValue");
    }

    @Test
    void remove() {
        BinarySearchTree<String, String> bst = newAvlBst();
        assertNull(bst.remove("case when bst is empty"));
        assertTrue(bst.isEmpty());

        bst.put("testStringKey3", "testStringValue3");
        bst.put("testStringKey4", "testStringValue4");
        bst.put("testStringKey2", "testStringValue2");
        bst.put("testStringKey5", "testStringValue5");
        bst.put("testStringKey1", "testStringValue1");
        bst.put("testStringKey0", "testStringValue0");

        assertFalse(bst.isEmpty());
        int size = bst.size();

        assertEquals(bst.remove("testStringKey4"), "testStringValue4");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("testStringKey4"));

        assertEquals(bst.remove("testStringKey1"), "testStringValue1");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("testStringKey1"));

        assertNull(bst.remove("testStringKey1"), "testStringValue1");
        assertEquals(bst.size(), size);
        assertFalse(bst.isEmpty());
        assertFalse(bst.containsKey("testStringKey1"));

        assertEquals(bst.remove("testStringKey3"), "testStringValue3");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("testStringKey3"));

        assertEquals(bst.remove("testStringKey0"), "testStringValue0");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("testStringKey0"));

        assertEquals(bst.remove("testStringKey2"), "testStringValue2");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("testStringKey2"));

        assertEquals(bst.remove("testStringKey5"), "testStringValue5");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("testStringKey5"));

        assertTrue(bst.isEmpty());
    }

    @Test
    void max() {
        BinarySearchTree<String, String> bst = newAvlBst();

        assertNull(bst.max());
        assertNull(bst.maxValue());

        bst.put("testStringKey2", "testStringValue2");

        assertEquals(bst.max(), "testStringKey2");
        assertEquals(bst.maxValue(), "testStringValue2");

        bst.put("testStringKey5", "testStringValue5");

        assertEquals(bst.max(), "testStringKey5");
        assertEquals(bst.maxValue(), "testStringValue5");

        bst.put("testStringKey0", "testStringValue0");

        assertEquals(bst.max(), "testStringKey5");
        assertEquals(bst.maxValue(), "testStringValue5");

        bst.put("testStringKey7", "testStringValue7");

        assertEquals(bst.max(), "testStringKey7");
        assertEquals(bst.maxValue(), "testStringValue7");

        bst.remove("testStringKey5");

        assertEquals(bst.max(), "testStringKey7");
        assertEquals(bst.maxValue(), "testStringValue7");

        bst.remove("testStringKey7");

        assertEquals(bst.max(), "testStringKey2");
        assertEquals(bst.maxValue(), "testStringValue2");

        bst.remove("testStringKey0");

        assertEquals(bst.max(), "testStringKey2");
        assertEquals(bst.maxValue(), "testStringValue2");

        bst.remove("testStringKey2");

        assertNull(bst.max());
        assertNull(bst.maxValue());
    }

    @Test
    void min() {
        BinarySearchTree<String, String> bst = newAvlBst();

        assertNull(bst.min());
        assertNull(bst.minValue());

        bst.put("testStringKey5", "testStringValue5");

        assertEquals(bst.min(), "testStringKey5");
        assertEquals(bst.minValue(), "testStringValue5");

        bst.put("testStringKey3", "testStringValue3");

        assertEquals(bst.min(), "testStringKey3");
        assertEquals(bst.minValue(), "testStringValue3");

        bst.put("testStringKey9", "testStringValue9");

        assertEquals(bst.min(), "testStringKey3");
        assertEquals(bst.minValue(), "testStringValue3");

        bst.put("testStringKey0", "testStringValue0");

        assertEquals(bst.min(), "testStringKey0");
        assertEquals(bst.minValue(), "testStringValue0");

        bst.remove("testStringKey5");

        assertEquals(bst.min(), "testStringKey0");
        assertEquals(bst.minValue(), "testStringValue0");

        bst.remove("testStringKey0");

        assertEquals(bst.min(), "testStringKey3");
        assertEquals(bst.minValue(), "testStringValue3");

        bst.remove("testStringKey9");

        assertEquals(bst.min(), "testStringKey3");
        assertEquals(bst.minValue(), "testStringValue3");

        bst.remove("testStringKey3");

        assertNull(bst.min());
        assertNull(bst.minValue());
    }

    @Test
    void contains() {
        BinarySearchTree<String, String> bst = newAvlBst();

        assertFalse(bst.containsKey("testStringKey"));
        assertFalse(bst.containsKey("testStringKey1"));

        bst.put("testStringKey", "testStringValue");
        assertTrue(bst.containsKey("testStringKey"));
        assertFalse(bst.containsKey("testStringKey1"));

        bst.put("testStringKey1", "testStringValue1");
        assertTrue(bst.containsKey("testStringKey1"));
        assertTrue(bst.containsKey("testStringKey"));

        bst.remove("testStringKey");
        assertTrue(bst.containsKey("testStringKey1"));
        assertFalse(bst.containsKey("testStringKey"));

        bst.remove("testStringKey1");
        assertFalse(bst.containsKey("testStringKey"));
        assertFalse(bst.containsKey("testStringKey1"));
    }

    @Test
    void empty() {
        BinarySearchTree<String, String> bst = newAvlBst();

        assertTrue(bst.isEmpty());

        bst.put("testStringKey", "testStringValue");
        assertFalse(bst.isEmpty());

        bst.put("testStringKey1", "testStringValue1");
        assertFalse(bst.isEmpty());

        bst.remove("testStringKey");
        assertFalse(bst.isEmpty());

        bst.remove("testStringKey1");
        assertTrue(bst.isEmpty());
    }

    @Test
    void ceil() {
        BinarySearchTree<String, String> bst = newAvlBst();

        bst.put("1", "testStringValue3");
        bst.put("3", "testStringValue4");
        bst.put("5", "testStringValue2");
        bst.put("7", "testStringValue5");
        bst.put("8", "testStringValue1");
        bst.put("9", "testStringValue0");
        bst.put("2", "testStringValue0");

        assertEquals(bst.min(), "1");
        assertEquals(bst.max(), "9");
        assertEquals(bst.ceil("5"), "5");
        assertEquals(bst.ceil("2"), "2");
        assertEquals(bst.ceil("8"), "8");
        assertEquals(bst.ceil("0"), "1");
        assertEquals(bst.ceil("9"), "9");
        assertNull(bst.ceil("99"));
    }

    @Test
    void floor() {
        BinarySearchTree<String, String> bst = newAvlBst();

        bst.put("1", "testStringValue3");
        bst.put("3", "testStringValue4");
        bst.put("5", "testStringValue2");
        bst.put("7", "testStringValue5");
        bst.put("8", "testStringValue1");
        bst.put("9", "testStringValue0");
        bst.put("2", "testStringValue0");

        assertEquals(bst.min(), "1");
        assertEquals(bst.max(), "9");
        assertEquals(bst.floor("5"), "5");
        assertEquals(bst.floor("4"), "3");
        assertEquals(bst.floor("8"), "8");
        assertEquals(bst.floor("1"), "1");
        assertEquals(bst.floor("99"), "9");
        assertNull(bst.floor(""));
    }

    @Test
    void moreReplace() {
        BinarySearchTree<String, String> bst = newAvlBst();

        assertNull(bst.get("1"));

        bst.put("1", "testStringValue3");
        assertEquals(bst.get("1"), "testStringValue3");

        bst.put("1", "testStringValue4");
        assertEquals(bst.get("1"), "testStringValue4");

        bst.put("1", "testStringValue2");
        assertEquals(bst.get("1"), "testStringValue2");

        bst.put("7", "testStringValue5");
        assertEquals(bst.get("7"), "testStringValue5");
        assertEquals(bst.get("1"), "testStringValue2");
    }
}