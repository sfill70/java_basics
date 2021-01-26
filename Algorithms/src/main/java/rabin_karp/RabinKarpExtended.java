package rabin_karp;

import java.util.*;

public class RabinKarpExtended {
    private String text;
    private TreeMap<Integer, Integer> number2position;

    public TreeMap<Integer, Integer> getNumber2position() {
        return number2position;
    }

    public RabinKarpExtended(String text) {
        this.text = text;
        createIndex();
    }

    public List<Integer> search(String query) {
        int count = 0;
        int pos = 0;
        ArrayList<Integer> indices = new ArrayList<>();
        int[] pattern = createPattern(query);
        for (Map.Entry<Integer, Integer> entry : number2position.entrySet()) {
            if (count > 0) {
                if (count == query.length()) {
                    indices.add(pos);
                    pos = 0;
                    count = 0;
                }
                if (entry.getValue() == pattern[count]) {
                    count++;
                } else {
                    pos = 0;
                    count = 0;
                }
            }
            if (entry.getValue() == pattern[0] && count == 0) {
                pos = entry.getKey();
                count++;
            }
        }
        return indices;
    }

    private void createIndex() {
        number2position = new TreeMap<>();
        char[] charArray = text.toLowerCase(Locale.ROOT).toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            number2position.put(i, (int) charArray[i]);
        }

    }

    private int[] createPattern(String query) {
        char[] charArray = query.toLowerCase(Locale.ROOT).toCharArray();
        int[] arrayPattern = new int[charArray.length];
        for (int i = 0; i < arrayPattern.length; i++) {
            arrayPattern[i] = charArray[i];
        }
        return arrayPattern;
    }

    public boolean comparisonString(String query, int pos) {
        System.out.println(text.substring(pos, pos + query.length()));
        return query.toLowerCase(Locale.ROOT).equals(text.substring(pos, pos + query.length()).toLowerCase(Locale.ROOT));
    }
}