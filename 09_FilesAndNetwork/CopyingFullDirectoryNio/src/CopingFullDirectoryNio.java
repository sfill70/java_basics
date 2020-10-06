import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.stream.Collectors.toList;

public class CopingFullDirectoryNio {
    private int count = 0;

    public static void main(String[] args) {
        try {
            copyingDirectoryFull(UtilCopingDir.parseDir("h:/"), UtilCopingDir.parseDir("h:/302"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyingDirectoryFull(String from, String where) throws IOException {
        Path source = Paths.get(from);
        Path destination = Paths.get(where);
       /* Files.walk(source)
                .map(source::relativize)
                .map(destination::resolve)
                .forEach(f ->copyFile(f, destinationDir));*/

        try {
            Files.walk(source)
                    .forEach(f -> copyFile(source, destination, f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(Path from, Path where, Path path) {
        //защита от циклического копирования
        if (path != null && !path.toString().equals(where.toString()) && !path.toString().startsWith(where.toString())) {
            try {
                Files.copy(path, takeCopyPath(from, where, path), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            } catch (IOException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
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
}
