package questions.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 有一群 servers，每台 server 可以处理 requests 的 qps 存在 machine_qps 字典里，例如 machine_qps = { "host1": 200, "host2": 200, "host3": 100 }
 * 现在写一个 load balancer, 每个 request 都会 call 这个 load balancer 程序从而得到对应 server 的名字
 */
public class ReturnNameOfLoadBalancer {

    public String machineForRequest(Map<String, Integer> machineQPS) {
        Map<Integer, String> requestToHost = new HashMap<>();
        int k = 0;
        int sum = 0;
        for (String host : machineQPS.keySet()) {
            sum += machineQPS.get(host);
            for (int i = 0; i < machineQPS.get(host); ++i) {
                requestToHost.put(k++, host);
            }
        }
        return requestToHost.get(new Random().nextInt(sum));
    }
}
