package questions._10_tree.segmenttree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class _3161_Block_Placement_Queries {

    public class Solution {

        /**
         * 主函数
         */
        public List<Boolean> blockPlacementQueries(int[][] queries) {
            // 找到所有查询中出现的最大坐标，作为线段树的范围，可以优化空间
            long maxCoord = 0;
            for (int[] q : queries) {
                maxCoord = Math.max(maxCoord, q[1]);
            }
            final long LIMIT = maxCoord > 0 ? maxCoord : 1;

            // 使用 TreeSet 存储障碍物位置，并加入左右边界
            TreeSet<Integer> obstacles = new TreeSet<>();
            obstacles.add(0);
            obstacles.add((int) LIMIT + 1); // 保证每个点右侧都有障碍物

            // 初始化线段树
            SegmentTree segTree = new SegmentTree(LIMIT);
            List<Boolean> result = new ArrayList<>();

            for (int[] query : queries) {
                int type = query[0];
                int x = query[1];

                if (type == 1) { // 添加障碍物
                    segTree.update(x);
                    obstacles.add(x);
                } else { // 回答查询
                    int sz = query[2];

                    // 找到 x 左侧最近的障碍物
                    int leftObstacle = obstacles.floor(x);

                    // 情况 A: 木块右边界在 x，其可用长度
                    long lenAtX = x - leftObstacle;

                    // 情况 B: 木块完全在 leftObstacle 的左侧
                    // 查询 [1, leftObstacle] 区间的最大空闲长度
                    long maxLenBefore = segTree.query(1, leftObstacle);

                    if (Math.max(lenAtX, maxLenBefore) >= sz) {
                        result.add(true);
                    } else {
                        result.add(false);
                    }
                }
            }
            return result;
        }

        /**
         * 线段树的节点
         */
        static class Node {
            long maxFree;      // 区间内的最大连续空闲长度
            long prefixFree;   // 从区间左端点开始的连续空闲长度
            long suffixFree;   // 到区间右端点结束的连续空闲长度
            Node left = null;
            Node right = null;

            // 为一个全新的、完全空闲的区间创建节点
            public Node(long len) {
                this.maxFree = len;
                this.prefixFree = len;
                this.suffixFree = len;
            }
        }

        /**
         * 用于在查询中传递和合并结果的辅助类
         */
        static class QueryResult {
            long maxFree;
            long prefixFree;
            long suffixFree;

            public QueryResult(long maxFree, long prefixFree, long suffixFree) {
                this.maxFree = maxFree;
                this.prefixFree = prefixFree;
                this.suffixFree = suffixFree;
            }
        }

        /**
         * 动态开点线段树
         */
        static class SegmentTree {
            private final Node root;
            private final long LIMIT; // 坐标系的最大范围

            public SegmentTree(long limit) {
                this.LIMIT = limit;
                this.root = new Node(limit);
            }

            // 公共接口：在 pos 位置设置障碍物
            public void update(int pos) {
                update(root, 1, LIMIT, pos);
            }

            // 递归实现 update
            private void update(Node node, long l, long r, int pos) {
                if (l == r) { // 到达叶子节点
                    node.maxFree = 0;
                    node.prefixFree = 0;
                    node.suffixFree = 0;
                    return;
                }

                long mid = l + (r - l) / 2;
                // 动态开点：如果子节点不存在，则创建。此时子节点代表完全空闲的区间。
                if (node.left == null) {
                    node.left = new Node(mid - l + 1);
                    node.right = new Node(r - mid);
                }

                if (pos <= mid) {
                    update(node.left, l, mid, pos);
                } else {
                    update(node.right, mid + 1, r, pos);
                }

                // 从子节点回溯时，合并信息到父节点
                pull(node, l, r);
            }

            // 公共接口：查询 [queryL, queryR] 范围内的最大连续空闲长度
            public long query(long queryL, long queryR) {
                if (queryL > queryR) {
                    return 0; // 无效区间
                }
                return query(root, 1, LIMIT, queryL, queryR).maxFree;
            }

            // 递归实现 query
            private QueryResult query(Node node, long l, long r, long queryL, long queryR) {
                // 如果节点为 null，说明这个区间从未被分割过，是完全空闲的
                if (node == null) {
                    long len = r - l + 1;
                    return new QueryResult(len, len, len);
                }
                // 查询范围与当前节点范围无交集
                if (r < queryL || l > queryR) {
                    return new QueryResult(0, 0, 0);
                }
                // 查询范围完全包含当前节点范围
                if (queryL <= l && r <= queryR) {
                    return new QueryResult(node.maxFree, node.prefixFree, node.suffixFree);
                }

                long mid = l + (r - l) / 2;
                QueryResult resLeft = query(node.left, l, mid, queryL, queryR);
                QueryResult resRight = query(node.right, mid + 1, r, queryL, queryR);

                // 被查询的左子区间部分的长度
                long leftLen = Math.max(0, mid - Math.max(l, queryL) + 1);
                // 被查询的右子区间部分的长度
                long rightLen = Math.max(0, Math.min(r, queryR) - (mid + 1) + 1);

                return merge(resLeft, resRight, leftLen, rightLen);
            }

            // 合并两个子节点信息到父节点
            private void pull(Node node, long l, long r) {
                long mid = l + (r - l) / 2;
                long lenLeft = mid - l + 1;
                long lenRight = r - mid;

                // 如果子节点为 null，则视其为完全空闲
                long leftPrefix = (node.left == null) ? lenLeft : node.left.prefixFree;
                long leftSuffix = (node.left == null) ? lenLeft : node.left.suffixFree;
                long leftMax = (node.left == null) ? lenLeft : node.left.maxFree;

                long rightPrefix = (node.right == null) ? lenRight : node.right.prefixFree;
                long rightSuffix = (node.right == null) ? lenRight : node.right.suffixFree;
                long rightMax = (node.right == null) ? lenRight : node.right.maxFree;

                // 合并前缀
                node.prefixFree = (leftPrefix == lenLeft) ? lenLeft + rightPrefix : leftPrefix;
                // 合并后缀
                node.suffixFree = (rightSuffix == lenRight) ? lenRight + leftSuffix : rightSuffix;
                // 合并最大值
                node.maxFree = Math.max(leftSuffix + rightPrefix, Math.max(leftMax, rightMax));
            }

            // 合并两个查询结果
            private QueryResult merge(QueryResult leftRes, QueryResult rightRes, long leftLen, long rightLen) {
                long prefix = (leftRes.prefixFree == leftLen) ? leftLen + rightRes.prefixFree : leftRes.prefixFree;
                long suffix = (rightRes.suffixFree == rightLen) ? rightLen + leftRes.suffixFree : rightRes.suffixFree;
                long max = Math.max(leftRes.suffixFree + rightRes.prefixFree, Math.max(leftRes.maxFree, rightRes.maxFree));

                return new QueryResult(max, prefix, suffix);
            }
        }
    }
}
