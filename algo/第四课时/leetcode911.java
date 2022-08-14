package com.sean.module4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @ClassName leetcode911
 * @Description: 在线选举
 * @Author a9705
 * @Date 2022/8/13
 * @Version V1.0
 **/
public class leetcode911 {

    TreeMap<Integer, Integer> rank = new TreeMap<>();
    Map<Integer, Integer> votes = new HashMap<>();

    /**
     * 时间复杂度 O(log(n))
     * 空间复杂度 O(n)
     */
    public leetcode911(int[] persons, int[] times) {
        for (int i = 0; i < persons.length; i++) {
            int curr = votes.getOrDefault(persons[i], 0);
            curr++;
            votes.put(persons[i], curr);
            if (rank.isEmpty()) {
                rank.put(times[i], persons[i]);
            } else {
                Map.Entry<Integer, Integer> currEntity = rank.floorEntry(times[i]);
                Integer maxVotePerson = currEntity.getValue();
                if (maxVotePerson == persons[i]) {
                    rank.put(times[i], persons[i]);
                } else {
                    Integer maxVote = votes.get(maxVotePerson);
                    if (curr >= maxVote) {
                        rank.put(times[i], persons[i]);
                    } else {
                        rank.put(times[i], maxVotePerson);
                    }
                }
            }

        }
    }

    public int q(int t) {
        return rank.floorEntry(t).getValue();
    }

}
