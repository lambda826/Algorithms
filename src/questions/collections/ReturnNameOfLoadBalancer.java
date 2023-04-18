package questions.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 第一问：有一群 servers，每台 server 可以处理 requests 的 qps 存在 machine_qps 字典里，例如 machine_qps = { "host1": 200, "host2": 200, "host3": 100 }。
 * 现在写一个load balancer, 每个 request 都会 call 这个 load balancer 程序从而得到对应 server 的名字，它的 typo 是 def machine_for_request() -> string;
 * <p>
 * 第二问：在第一问的基础上，现在我们有一个 router table，例如: machine_route = { "root": ["host1", "host2"], "host1": ["host3", "host4"] }，
 * 同样的，我们也有每个 machine 的 qps，例如: machine_qps = { "root": 0, "host1": 200, "host2": 200, "host3": 100, "host4": 50 }.
 * 现在需要写一个 routing process, 它的 typo 是这样: def routing_request(string host) -> string，来返回最终的 server。
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

    public String machineForRequest(Map<String, Integer> machineQPS, Map<String, List<String>> routeTable) {
        Map<String, Map<Integer, String>> map = new HashMap<>();
        for (String host : routeTable.keySet()) {
            map.put(host, new HashMap<>());
            int k = 0;
            for (String nextHost : routeTable.get(routeTable.keySet())) {
                for (int i = 0; i < machineQPS.get(nextHost); ++i) {
                    map.get(host).put(k++, nextHost);
                }
            }
        }
        String request = "root";
        while (routeTable.get(request) != null) {
            Map<Integer, String> next = map.get(request);
            request = next.get(new Random(next.keySet().size()));
        }
        return request;
    }
}
