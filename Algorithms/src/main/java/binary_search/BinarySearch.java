package binary_search;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch
{
    private ArrayList<String> list;

    public ArrayList<String> getList() {
        return list;
    }

    public BinarySearch(ArrayList<String> list)
    {
        this.list = list;
        Collections.sort(list);
    }

    public int search(String query)
    {

        return search(query, 0, list.size() - 1);
//        return searchRecursive(query, list.size() - 1);
    }

    private int search(String query, int from, int to)
    {
        while (from <= to) {
            int middle = (from + to) / 2;
            String element = list.get(middle);
            if (query.equals(element)) {
                return middle;
            } else if (query.compareTo(element) > 0) {
                from = middle + 1;
            } else {
                to = middle - 1;
            }
        }
        return -1;
    }

    private int searchRecursive(String query, int to)
    {
        int middle = to / 2;
        int compare = list.get(middle).compareTo(query);
        if (compare == 0) {
            return middle;
        } else {
            if (0 != to) {
                if (compare < 0) {
                    return search(query, middle + 1, list.size() - 1);
                } else {
                    return search(query, 0, middle - 1);
                }
            }
        }
        return -1;
    }
}
