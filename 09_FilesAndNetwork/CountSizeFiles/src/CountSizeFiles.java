import java.io.File;

public interface CountSizeFiles {

    void countSizeFiles(File file);

    void print();

    void zeroingVariable();

    default void printFiles(String path) {
        path = parseDir(path);
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("Директории не существует");
            return;
        }
        countSizeFiles(file);
        print();
        System.out.println();
        zeroingVariable();
    }

    private static String parseDir(String st) {
        String os = System.getProperty("os.name").toLowerCase();
        StringBuilder sb = new StringBuilder();
        String[] arrayPath = st.trim().split("\\|//|/|\\\\");
        if(!os.startsWith("windows")){
            sb.append(System.getProperty("file.separator"));
        }
        for (String s : arrayPath) {
            sb.append(s);
            sb.append(System.getProperty("file.separator"));
        }
        return sb.toString();
    }
}
