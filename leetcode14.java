public class leetcode14{
    //（1） TieRopes
    //给定n段绳子——一个正整数数组，和一个正整数K，每次只能连接相邻的两根绳子，
    //连接好了绳子长度为之前的绳子长度和，并且位置不变，问这么连接下去，
    //最多能形成多少根长度至少为K的绳子？
    //数据范围： N[1..10^5], 数组元素和K的范围[1..10^9]。

    //要求复杂度： 时间O(N), 空间O(1)。

    //分析： 假设最终扔掉一根绳子，那么为什么不把这根绳子连接到它相邻的绳子上呢？ 
    //所以不会扔绳子的…… 于是就线性扫一下 总和 >= K就是一条。。。
    public class TieRopes{
        int solution(int K, vector<int> &A) {  
            // write your code in C++11  
            int r = 0;  
            for (int i = 0; i < A.size();) {  
                int length = 0;  
                for (; (i < A.size()) && (length < K); length += A[i++])  
                ;  
                if (length >= K) {  
                    ++r;  
                }  
            }  
            return r;  
            
        }  
    }
    //（2） MaxNonoverlappingSegments
    //给定N条线段，每条线段是[A[i],B[i]]的形式（闭区间），线段已经按照结束端点排序了，
    //求最多能选出多少条没有公共点的线段。
    //数据范围 N [0..30000], A, B数组都是整数，范围[0..10^9]。
    //要求复杂度： 时间空间都是O(1)。

    //分析： 这个就是活动安排问题……而且区间都按右端点排序了，贪心一个一个取，相交就扔掉就可以了。
    public MaxNonoverlappingSegments{
        int solution(vector<int> &A, vector<int> &B) {  
            // write your code in C++11  
            int last = -1, answer = 0;  
            for (int i = 0; i < A.size(); ++i) {  
                if ((last < 0) || (A[i] > B[last])) {  
                    last = i;  
                    ++answer;  
                }  
            }  
            return answer;  
        } 
    }
}