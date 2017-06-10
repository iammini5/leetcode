public class leetcode11{
    //（1）Ladder
    //给定两个等长的数组A和B, A[i]和B[i]表示求一个有A[i]级的梯子，
    //每次上1级或者两级，上到最高级的方法数对2^B[i]取余数的结果。
    //数据范围：数组长度 L [1..30000] , A中数字范围 [1..L], B中数字范围[1..30]
    //要求复杂度 时间空间都是O(L)
    //分析：打表法——我们循环可以把0..L的结果都算出来 f[i] = f[i - 1] + f[i - 2]  , 
    //f[0] = f[1] = 1。 关键点在于取余数，这个取余数很特殊，对2^B[i]取余数，
    //相当于取结果的低B[i] bit。因此我们可以取所有结果的低30位，打好表，
    //输出时再真正取B[i]位，就能达到O(L)的复杂度了。
    public class Ladder{
        const int M = (1 << 30) - 1;  
        vector<int> solution(vector<int> &A, vector<int> &B) {  
            // write your code in C++98  
            
            int m = 0;  
            for (int i = 0; i < A.size(); ++i) {  
                m = max(m, A[i]);  
            }  
            vector<int> f;  
            f.resize(m + 1);  
            f[0] = f[1] = 1;  
            for (int i = 2; i <= m; ++i) {  
                f[i] = (f[i - 1] + f[i - 2]) & M;  
            }  
            vector<int> answer;  
            for (int i = 0; i < A.size(); ++i) {  
                answer.push_back(f[A[i]] & ((1 << B[i]) - 1));  
            }  
            return answer;  
        }  
    }
    //(2) FibFrog
    //先给了Fibonacci的定义F[0] = 0, F[1] = 1, F(M) = F(M - 1) + F(M - 2) if M >= 2。
    //一只青蛙想从河岸(-1)跳到对岸(N)。中间有0..N-1这N个位置，
    //有一个数组A[]表示这些位置是否有荷叶 ，0表示没有，1表示有。
    //青蛙从岸上要跳到荷叶上，通过0个或者多个荷叶跳到对岸，
    //而且只能朝对岸的方向跳——不能往回跳。而且青蛙每次跳的距离必须是一个Fibonacci数，
    //请问青蛙最少几步跳到对岸？无解返回－1。
    //数据范围： N [0..30000]
    //要求复杂度 :时间O(NlogN)，空间O(N)。

    //分析： 首先我们可以把数组尾端加一个1，表示对岸。可以认为首端有一个1或者认为数组下标从1开始，
    //我们现在目标是到数组最后一个元素，只能通过1且跳的距离是Fibonacci数的最小步数。 
    //这是一个显然的dp (或者说是bfs）。对于位置i 并且A[i] == 1,
    //我们有 dp[i] = min(dp[i - f[j]]) + 1 其中f[j]是fibonacci数并且满足A[i - f[j]]＝＝1
    //因为Fibonacci是指数增长的，所以不超过N的Fibonacci数是O(logN)个，整个递推的时间复杂度是O(NlogN)。
    public class FibFrog{
        int solution(vector<int> &A) {  
    // write your code in C++98  
            A.push_back(1);  
            int n = A.size();  
            vector<int> f;  
            f.push_back(1);  
            f.push_back(1);  
            for (int i = 1; f[i] < n; ++i) {  
                f.push_back(f[i] + f[i - 1]);  
            }  
            vector<int> answer;  
            answer.resize(n + 1, -1);  
            answer[0] = 0;  
            for (int i = 1; i <= n; ++i) {  
                answer[i] = -1;  
                if (A[i - 1] == 0) {  
                    continue;  
                }  
                for (int j = 0;(j < f.size()) && (i >= f[j]); ++j) {  
                    if ((answer[i - f[j]] >= 0) && ((answer[i] < 0) || (answer[i] > answer[i - f[j]] + 1))) {  
                        answer[i] = answer[i - f[j]] + 1;  
                    }  
                    
                }  
                
            }  
            return answer[n];  
        }  

    }
}