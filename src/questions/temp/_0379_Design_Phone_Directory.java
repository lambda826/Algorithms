/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package questions.temp;

/*

Design a Phone Directory which supports the following operations:
	get: Provide a number which is not assigned to anyone.
	check: Check if a number is available or not.
	release: Recycle or release a number.


Example:
    // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
    PhoneDirectory directory = new PhoneDirectory(3);
    
    // It can return any available phone number. Here we assume it returns 0.
    directory.get();
    
    // Assume it returns 1.
    directory.get();
    
    // The number 2 is available, so return true.
    directory.check(2);
    
    // It returns 2, the only number that is left.
    directory.get();
    
    // The number 2 is no longer available, so return false.
    directory.check(2);
    
    // Release number 2 back to the pool.
    directory.release(2);
    
    // Number 2 is available again, return true.
    directory.check(2);

*/

public class _0379_Design_Phone_Directory {

    int pos;
    boolean[] used;

    public _0379_Design_Phone_Directory(int maxNumbers) {
        pos = 0;
        used = new boolean[maxNumbers];
    }

    public int get() {
        for (int i = pos; i < used.length; ++i) {
            if (!used[i]) {
                used[i] = true;
                pos++;
                return i;
            }
        }
        return -1;
    }

    public boolean check(int number) {
        return !used[number];
    }

    public void release(int number) {
        pos = Math.min(pos, number);
        used[number] = false;
    }
}
