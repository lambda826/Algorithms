package questions._06_sorting.bucketSorting;

/*

There are n cars going to the same destination along a one-lane road. The destination is target miles away.

You are given two integer array position and speed, both of length n, where position[i] is the position of the ith car and speed[i] is the speed of the ith car (in miles per hour).

A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.

The distance between these two cars is ignored (i.e., they are assumed to have the same position).

A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.

If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.

Return the number of car fleets that will arrive at the destination.


Example 1:
    Input:
        target = 12,
        position = [10,8,0,5,3],
        speed = [2,4,1,1,3]
    Output:
        3
    Explanation:
        The cars starting at 10 and 8 become a fleet, meeting each other at 12.
        The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
        The cars starting at 5 and 3 become a fleet, meeting each other at 6.
        Note that no other cars meet these fleets before the destination, so the answer is 3.

Example 2:
    Input:
        target = 10,
        position = [3],
        speed = [3]
    Output:
        1


Constraints:
    n == position.length == speed.length
    1 <= n <= 10^5
    0 < target <= 10^6
    0 <= position[i] < target
    All the values of position are unique.
    0 < speed[i] <= 10^6

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _0853_Car_Fleet {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Key point: sort on the position, because we only need to check whether the latter car can catch up the previous one;
    // Be cautious:
    //      When we compare the time = distance / speed, we use multiplication instead of division to avoid using double which might not be accurate;
    //      Use long instead of int to avoid overflow.
    //      If the latter one can catch up with the previous car, then we should still use this car for the next iteration because it is slower so that it is the cap.
    class Solution {

        private class Car {
            int speed;
            int position;

            public Car(int speed, int position) {
                this.speed = speed;
                this.position = position;
            }
        }

        public int carFleet(int target, int[] position, int[] speed) {
            List<Car> list = new ArrayList<>();
            for (int i = 0; i < position.length; ++i) {
                list.add(new Car(speed[i], position[i]));
            }
            Collections.sort(list, (c1, c2) -> c2.position - c1.position);
            int fleet = 1;
            Car preCar = list.get(0);
            for (int i = 1; i < list.size(); ++i) {
                if (!canCatch(target, preCar, list.get(i))) {
                    ++fleet;
                    preCar = list.get(i);
                }
            }

            return fleet;
        }

        private boolean canCatch(int target, Car car1, Car car2) {
            long d1 = target - car1.position;
            long d2 = target - car2.position;
            return d2 * car1.speed <= d1 * car2.speed;
        }

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Optimized {

        public int carFleet(int target, int[] position, int[] speed) {
            int[] bucket = new int[target];
            for (int i = 0; i < position.length; ++i) {
                bucket[position[i]] = speed[i];
            }
            double slow = -1;
            int fleet = 0;
            for (int i = target - 1; i >= 0; --i) {
                if (bucket[i] != 0) {
                    double time = ((double) (target - i)) / bucket[i];
                    if (time > slow) {
                        ++fleet;
                        slow = time;
                    }
                }
            }
            return fleet;
        }
    }
}