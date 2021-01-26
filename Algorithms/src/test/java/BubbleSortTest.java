import bubble_sort.*;
import junit.framework.TestCase;
import org.junit.Assert;

public class BubbleSortTest extends TestCase {
    private int[] array;

    @Override
    protected void setUp() throws Exception {
        array = new int[]{5, 2, 3, 4, 1, 6};
    }

    public void testSort() {
        int[] actual = new int[]{1, 2, 3, 4, 5, 6};
        BubbleSort.sort(array);
        int[] expected = array;
        Assert.assertArrayEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}