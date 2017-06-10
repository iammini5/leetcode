public class leetcode15{
    //（1）  NumberSolitaire
    //一个游戏是从一排N个格子开始，格子编号0..N - 1，起初，棋子在A[0]，
    //每个格子里有一个整数（可能正，可能负）。你在格子I，你扔骰子，得到点数X = [1..6]，
    //然后走到编号为I + X的格子，如果这个格子不存在就再投一次骰子，直到I + X号格子存在。
    //你走到N - 1号格子时，游戏结束。你所经过格子里的整数的和是你的得分，求最大可能得分？
    //数据范围： N [2..10^5]， 格子里的数的范围 [-10000, +10000]。
    //要求复杂度： 时间O(N),空间O(N)
    //分析： 一个显然的dp  dp[i] = A[i] + max(dp[i - x])  1<=x<=min(6,i)
    public class NumberSolitaire{
        int solution(vector<int> &A) {  
            // write your code in C++11  
            const int inf = 2000000000;  
            int n = A.size();  
            vector<int> dp(n);  
            dp[0] = A[0];  
            
            for (int i = 1; i < n; ++i) {  
                dp[i] = -inf;  
                for (int j = min(6 , i); j; --j) {  
                    dp[i] = max(dp[i], dp[i - j]);  
                }  
                dp[i] += A[i];  
            }  
            return dp[n - 1];  
        }  
    }


}