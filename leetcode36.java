public class leetcode36{
    //给定一个整数数组A，有N个元素，找到所有下标对(P,Q)满足  0 ≤ P ≤ Q < N 并且 A[P] ≤ A[Q]中最大的Q-P。
    //数据范围N [1..3*10^5]
    //数组元素[-10^9, +10^9]
    //要求时间复杂度O(N)，空间复杂度O(N)。
    //分析： 如果b[i] = max{a[i..N - 1]} ，则对每个i，我们找到最大的j，满足b[j]>=a[i]，就可以了。
    //这样做的目的是b，反映了后面还有没有比a[i]大的。注意到假如现在找到的最大差值是r，
    //那么我们只需要j = i + r + 1开始找就可以了，所以j始终是不减少的。循环不变式是j = i + r + 1
    public class Natrium{
        int solution1(vector<int> &A) {  
            // write your code in C++11  
            int n = A.size();  
            vector<int> b;  
            b.resize(n);  
            for (int i = n - 1; i >= 0; --i) {  
                b[i] = ((i + 1 < n) && (b[i + 1] > A[i]))?b[i + 1]:A[i];  
            }  
            int r = 0, j = 0;  
            for (int i = 0; ++j < n; ++i) {  
                for (; (j < n) && (b[j] >= A[i]); ++j)  
                ;  
                r = j - i - 1;  
            }  
            return r;  
        }
        //另外一种思路是利用单调堆栈来做，考虑能作为P的下标，显然如果P' < P并且A[P'] <= A[P]，
        //则P'更好……于是能作为P的下标的A值严格单调递减。我们先收集这样可能的P，放到堆栈里，
        //然后从大到小考虑Q，如果A[Q]不小于A[栈顶]，我们就找到了这样的一个对下标，
        //当然下标差值可能是负数，但是没关系，不影响结果。当Q减小的时候，
        //我们只能从堆栈更多的弹出元素才能得到更大的结果，因为之前弹出的元素都是对更大的Q满足的，
        //对现在的Q本身没任何意义。  
        int solution(vector<int> &A) {  
            // write your code in C++11  
            stack<int> s;  
            int n = A.size();  
            s.push(0);  
            for (int i = 1; i < n; ++i) {  
                if (A[i] < A[s.top()]) {  
                    s.push(i);  
                }  
            }  
            int answer = 0;  
            for (int j = n - 1; (j >= 0) && (!s.empty()); --j) {  
                while ((!s.empty()) && (A[j] >= A[s.top()])) {  
                    answer = max(answer , j - s.top());  
                    s.pop();  
                }  
                
            }  
            return answer;  
            
        }  
    }
}