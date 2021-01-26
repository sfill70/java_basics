package array_max_value;

public class ArrayMaxValue {
    public static void main(String[] args) {
        int[] array = new int[0];
        try {
            System.out.println(getMaxValue(array));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getMaxValue(int[] values) throws Exception {
        if (values.length == 0) {
            throw new EmptyArrayException();
        }
        int maxValue = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}