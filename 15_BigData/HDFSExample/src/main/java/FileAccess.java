import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAccess {

    private FileSystem hdfs;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     *                 for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        configuration.setInt("dfs.replication", 1);
        configuration.setBoolean("dfs.support.append", true);
        System.setProperty("HADOOP_USER_NAME", "root");
        try {
            hdfs = FileSystem.get(new URI(rootPath), configuration);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates empty file or directory
     *
     * @param "path
     */
    public void create(String pathName)
    {
        try {
            Path path = new Path(pathName);
            if(hdfs.exists(path)) {
                System.out.println("This file or directory has already exist.");
                return;
            }
            if(pathName.contains(".")){
                hdfs.createNewFile(path);
                System.out.println("Created new file.");
            }
            else {
                hdfs.mkdirs(path);
                System.out.println("Created new directory.");
            }

        } catch (Exception e) {
            System.out.println("Wrong path!");
            e.printStackTrace();
        }
    }


    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) {
        Path hdfsPath = new Path(path);
        try (FSDataOutputStream hdfs_append = hdfs.append(hdfsPath);
             PrintWriter writer = new PrintWriter(hdfs_append)) {
            writer.append(content);
            writer.flush();
            hdfs_append.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) {
        Path hdfsPath = new Path(path);
        String result = null;

        byte[] buffer = new byte[1024];
        try (FSDataInputStream inputStream = hdfs.open(hdfsPath);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int bufferSize;
            while ((bufferSize = inputStream.read(buffer)) > 0) {
                baos.write(buffer, 0, bufferSize);
            }
            result = baos.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) {
        Path hdfsPath = new Path(path);
        try {
            if (hdfs.exists(hdfsPath)) {
                hdfs.delete(hdfsPath, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) {
        Path hdfsPath = new Path(path);
        boolean result = false;
        try {
            result = hdfs.isDirectory(hdfsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) {
        Path hdfsPath = new Path(path);
        List<String> result = new ArrayList<>();
        try {
            FileStatus[] statuses = hdfs.listStatus(hdfsPath);
            for (FileStatus status : statuses) {
                result.add(status.getPath().toString());
                if (status.isDirectory()) {
                    result.addAll(this.list(status.getPath().toString()));
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}

