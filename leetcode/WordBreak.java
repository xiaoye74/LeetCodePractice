package com.qianxiang.leetcode;

import java.util.ArrayList;
import java.util.Set;
/*
1、Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence
of one or more dictionary words.For example, given s ="leetcode",dict =["leet", "code"].
Return true because"leetcode"can be segmented as"leet code".
2、Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
For example, given
s ="catsanddog",
dict =["cat", "cats", "and", "sand", "dog"].
A solution is["cats and dog", "cat sand dog"].
 */
/*
思路
s ="catsand",
dict =["cat", "cats", "and", "sand", "dog"].

动态规划根本思想是记录状态值:
DP[i][j]:
           j    0       1       2       3       4       5       6
        i
        0       c       ca      cat(1)  cats(1) catsa   catsan  catsand
        1               a       at      ats     atsa    atsan   atsand
        2                       t       ts      tsa     tsan    tsand
        3                               s       sa      san     sand(1)
        4                                       a       an      and(1)
        5                                               n       nd
        6                                                       d

DP[i][j]里:
0       c       ca      cat(1)  cats(1) catsa   catsan  catsand
1       a       at      ats     atsa    atsan   atsand
2       t       ts      tsa     tsan    tsand
3       s       sa      san     sand(1)
4       a       an      and(1)
5       n       nd
6       d
思路:
DP[i][j]存放着字符串s的所有子字符串在dict中的状态值。
遍历顺序是先搜索i到串尾的子串，若子串在dict里，再搜索串头到i的子串。
        c a t s a n d
        j     i
比如，dp[3][3]=1表明"sand"在dict里，再搜索cat......
再搜索顺序为cat at t......


output(6,s):
i=6     k:0 1 2 3 4 5 6
dp[k][i-k]:偏移为k，截断字符串长度i-k+1
沿着次对角线遍历,相当于从头部每隔一个字符截断!!!
dp[0][6]:偏移为0，截断字符串长度7      0
dp[1][5]:偏移为1，截断字符串长度6      0
dp[2][4]:偏移为2，截断字符串长度5      0
dp[3][3]:偏移为3，截断字符串长度4      1   -->output(2,s)
dp[4][2]:偏移为4，截断字符串长度3      1   -->output(3,s)
dp[5][1]:偏移为5，截断字符串长度2      0
dp[6][0]:偏移为6，截断字符串长度1      0

output(2,s):
i=2     k:0 1 2
dp[k][i-k]:偏移为k，截断字符串长度i-k+1
沿着次对角线遍历,相当于从头部每隔一个字符截断!!!
dp[0][2]:偏移为0，截断字符串长度3      1   -->output(-1,s)
dp[1][1]:偏移为1，截断字符串长度2      0
dp[2][0]:偏移为2，截断字符串长度1      0

output(-1,s):
......


mystring.push_back(s.substr(k,i-k+1));
output(k-1,s);
s.substr(k,i-k+1)==>递归output(k-1,s)!!!
偏移为k,截断长度i-(k-1);                           ------
==>递归为k-1                                              |--->处理字符串长度i
从偏移为0，截断长度k开始以次对角线方向遍历!!!       ------
 */
public class WordBreak {
    //  2
    ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> wordBreak1(String s, Set<String> dict) {
        dfs(s, s.length(), "", dict);
        return list;
    }

    private void dfs(String s, int index, String str, Set<String> dict) {
        if (index <= 0) {
            if (str.length() > 0) {
                list.add(str.substring(0, str.length() - 1));
            }
        }
        for (int i = index; i >= 0; i--) {
            if (dict.contains(s.substring(i, index))) {
                dfs(s, i, s.substring(i, index) + " " + str, dict);
            }
        }
    }
    // 1
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0)
            return false;
        boolean flag[] = new boolean[s.length()+1];
        flag[0] = true;
        for (int i = 1; i <= s.length(); ++i)
        {
            for (int j = i-1; j >= 0; j--)
            {
                if (flag[j] && dict.contains(s.substring(j,i)))
                {
                    flag[i] = true;
                    break;
                }
                else
                    flag[i] = false;
            }
        }
        return flag[s.length()];
    }

}
