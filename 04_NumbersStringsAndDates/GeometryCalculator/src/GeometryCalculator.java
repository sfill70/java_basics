import java.util.Arrays;

public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        radius = Math.abs(radius);
        return Math.sqrt(radius) * Math.PI;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        radius = Math.abs(radius);
        return Math.pow(radius, 3.0) * Math.PI * 3 / 4;
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        double[] sides = new double[]{a, b, c};
        Arrays.sort(sides);
        return sides[0] + sides[1] > sides[2];
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (!isTriangleRightAngled(a, b, c)) {
            return -1.0;
        }
        double p = (a + b + c) / 2;
        return Math.pow(p * (p - a) * (p - b) * (p - c), 0.5);
    }
}
