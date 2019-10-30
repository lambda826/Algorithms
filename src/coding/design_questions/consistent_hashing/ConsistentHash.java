/**
 *  @author: Yunxiang He
 *  @date  : 2018-11-06
 */

package coding.design_questions.consistent_hashing;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash {

    private final TreeMap<Long, String> virtualNodes;
    private final int replicaNumber;
    private final int identityHashCode;
    private static ConsistentHash consistentHash;

    public ConsistentHash(List<String> nodes) {
        this.virtualNodes = new TreeMap<>();
        this.identityHashCode = identityHashCode(nodes);
        this.replicaNumber = 160;
        for (String node : nodes) {
            for (int i = 0; i < replicaNumber / 4; i++) {
                byte[] digest = md5(node.toString() + i);
                for (int h = 0; h < 4; h++) {
                    long m = hash(digest, h);
                    virtualNodes.put(m, node);
                }
            }
        }
    }

    private static int identityHashCode(List<String> nodes) {
        Collections.sort(nodes);
        StringBuilder sb = new StringBuilder();
        for (String s : nodes) {
            sb.append(s);
        }
        return sb.toString().hashCode();
    }

    public static String select(Trigger trigger, List<String> nodes) {
        int _identityHashCode = identityHashCode(nodes);
        if (consistentHash == null || consistentHash.identityHashCode != _identityHashCode) {
            synchronized (ConsistentHash.class) {
                if (consistentHash == null || consistentHash.identityHashCode != _identityHashCode) {
                    consistentHash = new ConsistentHash(nodes);
                }
            }
        }
        return consistentHash.select(trigger);
    }

    public String select(Trigger trigger) {
        String key = trigger.toString();
        byte[] digest = md5(key);
        String node = sekectForKey(hash(digest, 0));
        return node;
    }

    private String sekectForKey(long hash) {
        String node;
        Long key = hash;
        if (!virtualNodes.containsKey(key)) {
            SortedMap<Long, String> tailMap = virtualNodes.tailMap(key);
            if (tailMap.isEmpty()) {
                key = virtualNodes.firstKey();
            } else {
                key = tailMap.firstKey();
            }
        }
        node = virtualNodes.get(key);
        return node;
    }

    private long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24) | ((long) (digest[2 + number * 4] & 0xFF) << 16) | ((long) (digest[1 + number * 4] & 0xFF) << 8) | (digest[0 + number * 4] & 0xFF)) & 0xFFFFFFFFL;
    }

    private byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes;
        try {
            bytes = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.update(bytes);
        return md5.digest();
    }

}
