/**
 * @author Yunxiang He
 * @date 11/22/2018
 */

package questions.problems;

/*

有一些磁盘被分成了若干个sectors，设计两个api：
save(index): 检查该sector是否free，是的话标记成occuiped，返回true。否则返回false
find(index): 如果该sector是free，并且有更高位的sector也是free，返回更高位的free sectors中的最小index。否则返回-1.
第二个api的要求有点绕，举个例子：
    [0, 0, 1, 1, 0, 1, 1, 1] 0: free, 1: occuiped
    find(0) return 1
    find(1) return 4
    find(4) return -1
    find(2) return -1

*/

public class Check_Sectors_Free {

    int[] sectors;

    public Check_Sectors_Free(int n) {
        sectors = new int[n];
    }

    public Check_Sectors_Free(int[] sectors) {
        this.sectors = sectors;
    }

    public boolean save(int index) {
        if (sectors[index] == 1) {
            return false;
        } else {
            sectors[index] = 1;
            return true;
        }
    }

    public int find(int index) {
        if (sectors[index] == 1) {
            return -1;
        } else {
            while (index + 1 < sectors.length) {
                if (sectors[++index] == 0) {
                    return index;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Check_Sectors_Free test = new Check_Sectors_Free(new int[] { 0, 0, 1, 1, 0, 1, 1, 1 });
        System.out.println(test.find(0));
        System.out.println(test.find(1));
        System.out.println(test.find(4));
        System.out.println(test.find(2));
    }
}
