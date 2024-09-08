/**
 * @author: Yunxiang He
 * @date : 2018-10-18
 */

package questions.problems;

public class Edges_In_TwoD_Matrix {

    public int getEdges(int[][] matrix) {
        int[] preRow = matrix[0];
        int edges = preRow.length - 1;
        for (int i = 1; i < matrix.length; i++) {
            edges += Math.min(preRow.length, matrix[i].length) + matrix[i].length - 1;
            preRow = matrix[i];
        }
        return edges;
    }

}
