package com.sean.module3;

import java.util.*;

/**
 * @ClassName leetcode355
 * @Description: 设计推特
 * @Author a9705
 * @Date 2022/8/8
 * @Version V1.0
 **/
public class leetcode355 {

    Map<Integer, Person> users = new HashMap<>();

    static final int MAX_INFO_NUMS = 10;

    public leetcode355() {

    }

    public void postTweet(int userId, int tweetId) {

        Person person = getPerson(userId);
        person.ownInfo.addFirst(new Info(tweetId));
    }

    /**
     * 可以优化，在发推特和关注的时候进行最新列表处理，或者考虑合并k个链表
     *  时间复杂度 O(10n+10n*logn) 堆插入删除是logn 有10n个节点插入，
     *  空间复杂度 O(n*10)
     */
    public List<Integer> getNewsFeed(int userId) {

        Person person = getPerson(userId);
        //使用优先队列来进行处理
        PriorityQueue<Info> queue = new PriorityQueue<>();
        //取出10条数据来进行处理
        for (Person p : person.followed) {
            Deque<Info> ownInfo = p.ownInfo;
            Info[] infos = ownInfo.toArray(new Info[]{});
            for (int i = 0; i < MAX_INFO_NUMS && i < infos.length; i++) {
                queue.add(infos[i]);
            }
        }

        //加入自己的消息
        for (Info info : person.ownInfo) {
            queue.add(info);
        }

        //取出数据放入答案
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < MAX_INFO_NUMS && !queue.isEmpty(); i++) {
            ans.add(queue.poll().tweetId);
        }

        return ans;
    }

    public void follow(int followerId, int followeeId) {
        Person from = getPerson(followerId);
        Person to = getPerson(followeeId);
        from.followed.add(to);

    }

    public void unfollow(int followerId, int followeeId) {
        Person from = getPerson(followerId);
        Person to = getPerson(followeeId);
        from.followed.remove(to);
    }

    private Person getPerson(int userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        } else {
            Person person = new Person(userId);
            users.put(userId, person);
            return person;
        }
    }

    class Person {


        public Set<Person> followed = null;
        public Deque<Info> ownInfo = null;
        public int userId;

        public Person(int userId) {
            followed = new HashSet<>();
            ownInfo = new LinkedList<>();
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "followed=" + followed +
                    ", ownInfo=" + ownInfo +
                    ", userId=" + userId +
                    '}';
        }
    }

    class Info implements Comparable<Info> {
        public int tweetId;
        public int timdId;

        public Info(int tweetId) {
            this.tweetId = tweetId;
            this.timdId = TimeId.getTimeId();
        }

        @Override
        public int compareTo(Info o) {
            if (this.timdId > o.timdId) {
                return -1;
            } else if (this.timdId < o.timdId) {
                return 1;
            }

            return 0;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "tweetId=" + tweetId +
                    '}';
        }
    }

    static class TimeId {
        static int id = 0;

        public static int getTimeId() {
            id++;
            return id;
        }
    }
}
