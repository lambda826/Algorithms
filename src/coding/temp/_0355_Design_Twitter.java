/**
 *  @author Yunxiang He
 *  @date 03/30/2019
 */

package coding.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. 
Your design should support the following methods:
    postTweet(userId, tweetId): Compose a new tweet.
    getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
    follow(followerId, followeeId): Follower follows a followee.
    unfollow(followerId, followeeId): Follower unfollows a followee.


Example:
    Twitter twitter = new Twitter();
    // User 1 posts a new tweet (id = 5).
    twitter.postTweet(1, 5);
    // User 1's news feed should return a list with 1 tweet id -> [5].
    twitter.getNewsFeed(1);
    // User 1 follows user 2.
    twitter.follow(1, 2);
    // User 2 posts a new tweet (id = 6).
    twitter.postTweet(2, 6);
    // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
    // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
    twitter.getNewsFeed(1);
    // User 1 unfollows user 2.
    twitter.unfollow(1, 2);
    // User 1's news feed should return a list with 1 tweet id -> [5],
    // since user 1 is no longer following user 2.
    twitter.getNewsFeed(1);

*/

public class _0355_Design_Twitter {

    int timestamp;
    Map<Integer, User> userDB;
    Map<Integer, Tweet> tweetDB;

    public _0355_Design_Twitter() {
        timestamp = 0;
        userDB = new HashMap<>();
        tweetDB = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, timestamp++);
        tweetDB.put(tweetId, tweet);
        userDB.putIfAbsent(userId, new User(userId));
        userDB.get(userId).tweets.add(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> list = new ArrayList<>();
        if (userDB.containsKey(userId)) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((t1, t2) -> tweetDB.get(t2).time - tweetDB.get(t1).time);
            // add
            maxHeap.addAll(userDB.get(userId).tweets);
            for (Integer followerID : userDB.get(userId).followers) {
                maxHeap.addAll(userDB.get(followerID).tweets);
            }
            // poll
            for (int i = 0; i < 10 && !maxHeap.isEmpty(); ++i) {
                list.add(maxHeap.poll());
            }
        }
        return list;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            userDB.putIfAbsent(followerId, new User(followerId));
            userDB.putIfAbsent(followeeId, new User(followeeId));
            userDB.get(followerId).followers.add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        userDB.putIfAbsent(followerId, new User(followerId));
        userDB.putIfAbsent(followeeId, new User(followeeId));
        userDB.get(followerId).followers.remove(followeeId);
    }

    // pull mode
    class User {
        int userId;
        Set<Integer> followers;
        Set<Integer> tweets;

        public User(int userId) {
            this.userId = userId;
            this.followers = new HashSet<>();
            this.tweets = new HashSet<>();
        }
    }

    class Tweet {
        int tweetId;
        int time;

        public Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }
}
