import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

import static java.lang.System.out;

public class RStorage {
    private RedissonClient redisson;
    private RKeys rKeys;
    private RScoredSortedSet<String> datingUsers;

    private final static String KEY = "DATING_USERS";

    public void listKeys() {
        Iterable<String> keys = rKeys.getKeys();
        for(String key: keys) {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
        }
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        datingUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    public RScoredSortedSet<String> getDatingUsers() {
        return datingUsers;
    }

    void shutdown() {
        redisson.shutdown();
    }

    void changePriority(double value, String user_id)
    {
/*//        если время ожидания меньше 1 секунды
        datingUsers.add(value + 20.0, user_id);*/
        datingUsers.add(getTs(), user_id);
    }

    void logPageVisit(int user_id)
    {
        //ZADD ONLINE_USERS
        datingUsers.add(getTs(), String.valueOf(user_id));
    }

    public double getTs() {
        return new Date().getTime() / 1000.0;
    }

    double getFirstValue(){
        return datingUsers.getScore(datingUsers.first());
    }

    String popFirst() {
        return datingUsers.pollFirst();
    }
}
