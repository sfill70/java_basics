import java.io.*;

public class RunNumberGenerator implements Runnable {
    public static final int NUMBERS = 1000;
    private String fileName;
    private int regionCode;
    private StringBuilder region;

    public RunNumberGenerator(String fileName, int regionCode) {
        this.fileName = fileName + regionCode + ".txt";
        this.regionCode = regionCode;
        region = padNumber(regionCode, 2);
    }

    @Override
    public void run() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fileOutputStream)))) {
            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            StringBuilder sb;

            for (int number = 1; number < NUMBERS; number++) {
                sb = new StringBuilder();
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            sb.append(firstLetter)
                                    .append(padNumber(number, 3))
                                    .append(secondLetter)
                                    .append(thirdLetter)
                                    .append(region)
                                    .append('\n');
                        }
                    }
                }
                printWriter.write(sb.toString());
            }
//            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder();
        int padSize = numberLength - Integer.toString(number).length();
        numberStr.append("0".repeat(Math.max(0, padSize)));
        return numberStr.append(number);
    }
}
