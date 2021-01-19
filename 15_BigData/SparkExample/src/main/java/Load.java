import java.io.IOException;

public class Load {

    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = new ProcessBuilder("bash", "-c", "docker run --name had -d " +
                "--hostname had011 --rm -ti -p 4040:4040 -p 8020:8020 -p 8032:8032 -p 8088:8088 -p 9000:9000 " +
                "-p 10020:10020 -p 19888:19888 -p 50010:50010 -p 50020:50020 -p 50070:50070 -p 50075:50075 " +
                "-p 50090:50090 harisekhon/hadoop").start();

        Thread.sleep(12000);

        Process p1 = new ProcessBuilder("bash", "-c",
                "docker stop $(docker ps -aqf \"name=had\")").start();
        Thread.sleep(20000);
        /*Process p2 = new ProcessBuilder("gnome-terminal", "-e",
                "docker ps -a").start();
        Thread.sleep(12000);*/
    }


}
