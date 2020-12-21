import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int NUMBER_CORES = Runtime.getRuntime().availableProcessors();
    private static final int REGION_NUMBER = 100;
    private static final String RESULT_PATH = String.join(File.separator, System.getProperty("user.dir"), "res", "");

    public static void main(String[] args){

        System.out.println(RESULT_PATH);
        long start = 0;
        try {
            start = System.currentTimeMillis();

            ExecutorService taskExecutor = Executors.newFixedThreadPool(NUMBER_CORES);

            for (int i = 1; i <= REGION_NUMBER; i++) {
                taskExecutor.execute(new RunNumberGenerator(RESULT_PATH, i));
            }
            taskExecutor.shutdown();
            try {
                taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
