package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SmallestSufficientTeam {

    /*
    1125. Smallest Sufficient Team
        Hard
        1.7K
        43
        Companies
        In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i] contains a list of skills that the person has.

        Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.

        For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
        Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in any order.

        It is guaranteed an answer exists.



        Example 1:

        Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
        Output: [0,2]

        Constraints:

        1 <= req_skills.length <= 16
        1 <= req_skills[i].length <= 16
        req_skills[i] consists of lowercase English letters.
        All the strings of req_skills are unique.
        1 <= people.length <= 60
        0 <= people[i].length <= 16
        1 <= people[i][j].length <= 16
        people[i][j] consists of lowercase English letters.
        All the strings of people[i] are unique.
        Every skill in people[i] is a skill in req_skills.
        It is guaranteed a sufficient team exists.
     */


    /**
     *
     * Approach 1: Bottom-Up Dynamic Programming with Bitmasks
     * Intuition
     * Let nnn be the number of people and mmm be the number of required skills.
     *
     * In this problem, mmm is very small – up to 161616. It alludes to track which skills have been covered so far, which is possible to do efficiently with a bitmask.
     *
     * First, let's make our lives easier by dealing with indices instead of strings for the required skills. We use a hash map skillId that keeps the index for each skill. We initialize skillId[req_skills[i]]=i\text{skillId}[\text{req\_skills}[i]] = iskillId[req_skills[i]]=i for all iii from 000 to m−1m - 1m−1.
     *
     * Now, when each skill has its number, we can represent every set of skills with a bitmask – an integer between 000 and 2m−12^m - 12
     * m
     *  −1.
     *
     * How do we associate a set and an integer exactly? We look at the binary representation of the integer. If the ithi^\text{th}i
     * th
     *   bit is 111, element iii belongs to the set. Otherwise, it does not.
     *
     * Examples
     *
     * 1011112=20+21+22+23+25=47101111_2=2^0+2^1+2^2+2^3+2^5=47101111
     * 2
     * ​
     *  =2
     * 0
     *  +2
     * 1
     *  +2
     * 2
     *  +2
     * 3
     *  +2
     * 5
     *  =47 represents the set {0,1,2,3,5}\{0, 1, 2, 3, 5\}{0,1,2,3,5}.
     * 10010102=21+23+26=741001010_2=2^1+2^3+2^6=741001010
     * 2
     * ​
     *  =2
     * 1
     *  +2
     * 3
     *  +2
     * 6
     *  =74 represents the set {1,3,6}\{1, 3, 6\}{1,3,6}.
     * 000 represents an empty set.
     * 20+21+22+⋯+2m−1=2m−12^0+2^1+2^2+\dots+2^{m-1}=2^m-12
     * 0
     *  +2
     * 1
     *  +2
     * 2
     *  +⋯+2
     * m−1
     *  =2
     * m
     *  −1 represents {0,1,2,…,m−1}\{0, 1, 2, \dots, m - 1\}{0,1,2,…,m−1}.
     * The problem asks to find the smallest team such that the union of the skill sets of its members is the set of all required skills {0,1,2,…,m−1}\{0, 1, 2, \dots, m - 1\}{0,1,2,…,m−1}.
     *
     * One can reformulate the statement in terms of bitmasks: we need to find the smallest team such that the bitwise OR of the bitmasks representing the skill sets of its members is 2m−12^m - 12
     * m
     *  −1 (which is the representation of {0,1,2,…,m−1}\{0, 1, 2, \dots, m - 1\}{0,1,2,…,m−1}).
     *
     * We will solve this problem using dynamic programming.
     *
     * Let dp[skillsMask]\text{dp}[\text{skillsMask}]dp[skillsMask] be a bitmask representing the smallest team that possesses all the skills from skillsMask\text{skillsMask}skillsMask. The value of dp[skillsMask]\text{dp}[\text{skillsMask}]dp[skillsMask] is a bitmask that represents the set of team members.
     *
     * We are using bitmasks to represent skillsMask, but we can also use bitmasks to represent a set of people. skillsMask\text{skillsMask}skillsMask represents the set of skills, and dp[skillsMask]\text{dp}[\text{skillsMask}]dp[skillsMask] represents the set of people on the team. Similar to how we treat the skillsMask bitmask, the bitmask representing people has the ithi^\text{th}i
     * th
     *   bit set if the ithi^\text{th}i
     * th
     *   person is on the team.
     *
     * The base case of this dynamic programming (DP) problem is when skillsMask=0\text{skillsMask} = 0skillsMask=0, which represents an empty set of skills. When no skills are required, we can form an empty team, and thus, we set dp[0]=0\text{dp}[0] = 0dp[0]=0 – a bitmask representing an empty set of people.
     *
     * Now we need to write down the transitions of this DP.
     *
     * For a given skillsMask≠0\text{skillsMask} \ne 0skillsMask
     * 
     * =0, there must be at least one person in a team. Since we need to find the minimal team, we initialize dp[skillsMask]\text{dp}[\text{skillsMask}]dp[skillsMask] with a large value, like the team of all people 2n−12^n - 12
     * n
     *  −1.
     *
     * Then we iterate over all people and for each person, try to update dp[skillsMask]\text{dp}[\text{skillsMask}]dp[skillsMask] with a team containing this person.
     *
     * The ithi^\text{th}i
     * th
     *   person or at least one other team member must possess the skills in skillsMask\text{skillsMask}skillsMask.
     *
     * Let skillsMaskOfPerson[i]\text{skillsMaskOfPerson}[i]skillsMaskOfPerson[i] denote the bitmask representing the skills set of the ithi^\text{th}i
     * th
     *   person. We can precompute this to make the algorithm more efficient.
     *
     * To summarize, we have 3 types of bitmasks. First, the keys to dp, which is skillsMask. This represents the set of skills that a team covers. Next, the dp values represent a set of people on a team. Finally, we are using skillsMaskOfPerson to represent the skills that a given person possesses, which is given in the input – we just need to convert it using skillId, which we defined at the start.
     *
     * Although the other team members may possess the skills from skillsMaskOfPerson[i]\text{skillsMaskOfPerson}[i]skillsMaskOfPerson[i], it is not necessary. However, they must have the skills from skillsMask\text{skillsMask}skillsMask that are not present in skillsMaskOfPerson[i]\text{skillsMaskOfPerson}[i]skillsMaskOfPerson[i].
     *
     * The set smallerSkillsMask=skillsMask∖skillsMaskOfPerson[i]\text{smallerSkillsMask} = \text{skillsMask} \setminus \text{skillsMaskOfPerson}[i]smallerSkillsMask=skillsMask∖skillsMaskOfPerson[i], where ∖\setminus∖ denotes the set difference, contains the required skills that the ithi^\text{th}i
     * th
     *   person does not possess. The other team members must possess these skills.
     *
     * In a code, a neat trick to calculate smallerSkillsMask\text{smallerSkillsMask}smallerSkillsMask is skills_mask & ~skills_mask_of_person[i]. Alternatively, one could calculate it manually by checking each bit one by one, but this trick is cleaner.
     *
     * We will update dp[skillsMask]\text{dp}[\text{skillsMask}]dp[skillsMask] with the bitmask dp[smallerSkillsMask] OR 2i\text{dp}[\text{smallerSkillsMask}] \text{ OR } 2^idp[smallerSkillsMask] OR 2
     * i
     *   – add the ithi^\text{th}i
     * th
     *   person to the team and cover the remaining skills with the smallest possible set of people, which is defined as dp[smallerSkillsMask]\text{dp}[\text{smallerSkillsMask}]dp[smallerSkillsMask]. This update only makes sense if smallerSkillsMask≠skillsMask\text{smallerSkillsMask} \ne \text{skillsMask}smallerSkillsMask
     * 
     * =skillsMask because otherwise, the ithi^\text{th}i
     * th
     *   person would not contribute any new skills to the team.
     *
     * The answer to the problem is dp[2m−1]\text{dp}[2^m - 1]dp[2
     * m
     *  −1] – the smallest team that possesses all the required skills.
     *
     * Algorithm
     * Set nnn to the number of people.
     * Set mmm to the number of required skills.
     * Declare the hash map skillId\text{skillId}skillId.
     * Iterate iii from 000 to m−1m - 1m−1.
     * Set skillId[req_skills[i]]=i\text{skillId}[\text{req\_skills}[i]] = iskillId[req_skills[i]]=i.
     * Declare and initialize the array skillsMaskOfPerson\text{skillsMaskOfPerson}skillsMaskOfPerson.
     * Iterate iii from 000 to n−1n - 1n−1.
     * Iterate skill\text{skill}skill over people[i]\text{people}[i]people[i].
     * Set the bit skillId[skill]\text{skillId}[\text{skill}]skillId[skill] in the bitmask skillsMaskOfPerson[i]\text{skillsMaskOfPerson}[i]skillsMaskOfPerson[i].
     * Declare the array dp\text{dp}dp of size 2m2^m2
     * m
     *   and initialize it with the values of 2n−12^n - 12
     * n
     *  −1.
     * Set dp[0]=0\text{dp}[0] = 0dp[0]=0. (The base case of the DP.)
     * Iterate skillsMask\text{skillsMask}skillsMask from 111 to 2m−12^m - 12
     * m
     *  −1.
     * Iterate iii from 000 to n−1n - 1n−1.
     * Set smallerSkillsMask=skillsMask∖skillsMaskOfPerson[i]\text{smallerSkillsMask} = \text{skillsMask} \setminus \text{skillsMaskOfPerson}[i]smallerSkillsMask=skillsMask∖skillsMaskOfPerson[i].
     * If smallerSkillsMask≠skillsMask\text{smallerSkillsMask} \ne \text{skillsMask}smallerSkillsMask
     * 
     * =skillsMask.
     * Set peopleMask\text{peopleMask}peopleMask to dp[smallerSkillsMask] OR 2i\text{dp}[\text{smallerSkillsMask}] \text{ OR } 2^idp[smallerSkillsMask] OR 2
     * i
     *  . This is the mask that represents the new team once you add the current person.
     * Update dp[skillsMask]\text{dp}[\text{skillsMask}]dp[skillsMask] with peopleMask\text{peopleMask}peopleMask, if it is better (has fewer bits set).
     * Return the array containing the elements from the bitmask dp[2m−1]\text{dp}[2^m - 1]dp[2
     * m
     *  −1].
     *
     * @param req_skills list of required skills
     * @param people list of people
     * @return Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in any order.
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = people.size(), m = req_skills.length;
        HashMap<String, Integer> skillId = new HashMap<String, Integer>();
        for (int i = 0; i < m; i++) {
            skillId.put(req_skills[i], i);
        }
        int skillsMaskOfPerson[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (String skill : people.get(i)) {
                skillsMaskOfPerson[i] |= 1 << skillId.get(skill);
            }
        }
        long dp[] = new long [1 << m];
        Arrays.fill(dp, (1L << n) - 1);
        dp[0] = 0;
        for (int skillsMask = 1; skillsMask < (1 << m); skillsMask++) {
            for (int i = 0; i < n; i++) {
                int smallerSkillsMask = skillsMask & ~skillsMaskOfPerson[i];
                if (smallerSkillsMask != skillsMask) {
                    long peopleMask = dp[smallerSkillsMask] | (1L << i);
                    if (Long.bitCount(peopleMask) < Long.bitCount(dp[skillsMask])) {
                        dp[skillsMask] = peopleMask;
                    }
                }
            }
        }
        long answerMask = dp[(1 << m) - 1];
        int ans[] = new int [Long.bitCount(answerMask)];
        int ptr = 0;
        for (int i = 0; i < n; i++) {
            if (((answerMask >> i) & 1) == 1) {
                ans[ptr++] = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SmallestSufficientTeam smallestSufficientTeam = new SmallestSufficientTeam();
        String[] required_skills = {"algorithms","math","java","reactjs","csharp","aws"};
        List<List<String>> peopleList = new ArrayList<>();
        peopleList.add(List.of("algorithms","math","java"));
        peopleList.add(List.of("algorithms","math","reactjs"));
        peopleList.add(List.of("java","csharp","aws"));
        peopleList.add(List.of("reactjs","csharp"));
        peopleList.add(List.of("csharp","math"));
        peopleList.add(List.of("aws","java"));
        System.out.println(Arrays.toString(smallestSufficientTeam.smallestSufficientTeam(required_skills, peopleList)));
    }

}
