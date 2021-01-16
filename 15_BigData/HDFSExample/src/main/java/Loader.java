public class Loader {
    public static void main(String[] args) {
        //"Сначала запустить Main оттуда file.txt читает "
        String hdfsRootPath = "hdfs://2b3eb249ae8a:8020";
        String creatFileName = "/test/fileCreate.txt";
        String fileName = "/test/file.txt";
        String dirName = "/test/testDir";

        FileAccess fileAccess = new FileAccess(hdfsRootPath);

        fileAccess.create(creatFileName);
        fileAccess.create(dirName);
        fileAccess.append(creatFileName, "First line append to file.\n");
        fileAccess.append(creatFileName, "Second line for append to file.\n");
        fileAccess.append(creatFileName, "Third line for append to file.\n");

        System.out.println(fileAccess.read(fileName));

        System.out.println("Path " + fileName + " is a directory: " + fileAccess.isDirectory(fileName));
        System.out.println("Path " + dirName + " is a directory: " + fileAccess.isDirectory(dirName));

        for (String name : fileAccess.list("/")) {
            System.out.println(name);
        }
    }
}
