import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.util.stream.Collectors.toList;

public class CopingFullDirectoryNio {
    private int count = 0;
   Path errFile;

    public static void main(String[] args) {
        try {
            copyingDir(UtilCopingDir.parseDir("i:/bp7"), UtilCopingDir.parseDir("i:/301"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyingDirectory(String from, String where, List<Path> list) throws IOException {
        Path fromPath = Paths.get(from);
        Path wherePath = Paths.get(where);
        Path copyPath;
        for (Path path : list
        ) {
            printPoint();
            copyPath = takeCopyPath(fromPath, wherePath, path);
            if (path.toString().contains("System Volume Information")) {
                errFile = path;
                System.out.println(path);
                continue;
            }
            try {
                Files.copy(path, copyPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error");
            }
        }
        System.out.println();
        System.out.println(errFile.toAbsolutePath());
    }

    private static Path takeCopyPath(Path fromPath, Path wherePath, Path filePath) {
        return wherePath.toAbsolutePath().resolve(fromPath.toAbsolutePath().relativize(filePath));
    }

    private void printPoint() {
        if (count % 50 == 0) {
            String point = ".";
            System.out.print(point);
        }
        count++;
        if (count % 10000 == 0) {
            System.out.println();
        }
    }

    // При записи раздела java.io.UncheckedIOException: java.nio.file.AccessDeniedException:
    public static void copyingDir(String from, String where) throws IOException {
        Path source = Paths.get(from);
        Path destination = Paths.get(where);
        List<Path> sources = Files.walk(source).collect(toList());
        List<Path> destinations = sources.stream()
                .map(source::relativize)
                .map(destination::resolve)
                .collect(toList());

        for (int i = 0; i < sources.size(); i++) {
            Files.copy(sources.get(i), destinations.get(i));
        }
    }

    /*private static void copyingFile(File original, File copied)
            throws IOException {
        try (InputStream in = new BufferedInputStream(
                new FileInputStream(original));
             OutputStream out = new BufferedOutputStream(
                     new FileOutputStream(copied))) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
    }*/

}
