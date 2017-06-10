public class leetcode13{
    //（1）AbsDistinct
    //给定一个按非递减顺序排好顺序的非空整数数组，问里面右多少种不同的绝对值。
    //数据范围：整数数组长度[1..10^5], 整数范围[-2147483648, +2147483647]。
    //要求复杂度 ： 时间O(N),空间O(1)
    //分析： 题目不难…… 但是细节很重要。因为整数直接取绝对值可能回溢出（例如-2147483648),
    //而且我们没有额外空间hash。所以一个好办法是类似合并两个有序序列。
    //我们从最小的负数和最大的正数开始类似归并排序那么做。
    //这样，正负数都是按照绝对值逐渐减小的顺序遍历的。
    //我们把正数和负数的绝对值想像成两个递减的序列，然后按归并排序思路，
    //每次大的动一下就可以了，直到一个列表为空的时候，我们需要把另外一个列表计算进去。
    //要点就是可以用x + y的符号来代替绝对值比较，因为一个正数 ＋ 负数 不会溢出。。。。
    public class AbsDistinct{
        int solution(vector<int> &A) {  
            // write your code in C++11  
            int answer = 0;  
            for (int i = 0, j = A.size() - 1;i <= j;) {  
                if ((A[i] >= 0) || (A[j] <= 0)) {  
                    for (;i <= j;++answer) {   
                        for (int k = i; (i <= j) && (A[i] == A[k]); ++i)  
                        ;  
                    }          
                }  
                else {  
                    ++answer;  
                    int temp = A[i] + A[j];  
                    if (temp < 0) {  
                        for (int k = i; (i <= j) && (A[i] == A[k]); ++i)  
                        ;  
                    }  
                    else if (temp > 0) {  
                        for (int k = j; (i <= j) && (A[j] == A[k]); --j)  
                        ;  
                    }  
                    else {  
                        for (int k = i; (i <= j) && (A[i] == A[k]); ++i)  
                        ;  
                        for (int k = j; (i <= j) && (A[j] == A[k]); --j)  
                        ;  
                    }  
                }  
            }  
            return answer;  
                        
        }  
    }

    //（2） CountTriangles
    //给定正整数数组A,长度为N,下标从0开始，求(P,Q,R),满足0<=P<Q<R<N 
    //并且 A[P] + A[Q] > A[R], A[Q] + A[R] > A[P], A[P] + A[R] > A[Q]的三元组个数。
    //数据范围 N [0..1000], 数组元素[1..10^9]。
    //要求复杂度 时间O(N ^ 2) ，空间 O(1)。
    //分析： 显然我们不能枚举……我们可以把数组排序 O(NlogN),
    //甚至O(N^2)的排序都可以。然后还是枚举，只不过枚举两条较小的边A[x] , A[y], 
    //然后我们考虑最大边A[z]，设想假设我们固定x, 当y变大时A[x] + A[y]也变大，
    //我们需要A[x] + A[y] > A[z], y变大之前的那些z值现在依然也满足条件，
    //所以我们只要接着上次满足条件的最大的z，继续循环就可以了。
    //所以对于同一个x来说,y和z的变化都是O(N)的。总复杂度O(N^2)。
    public class CountTriangles{
        int solution(vector<int> &A) {  
            // write your code in C++98  
            sort(A.begin(), A.end());  
            int n = A.size(), result = 0;  
            for (int x = 0; x < n; ++x) {  
                int z = 0;  
                for (int y = x + 1; y < n; ++y) {  
                    for (z = max(z, y + 1); (z < n) && (A[x] + A[y] > A[z]); ++z)  
                    ;  
                    result += z - y - 1;  
            }  
            
            }  
        
            return result;  
        }  
    }
    //（3） 给定正整数非负整数数组A,和非负整数M,A中所有元素在[0..M]之间，
    //求A有多少个非空子数组（连续的段），不包含重复的数。如果结果超过10^9,则返回10^9
    //数据范围： M [0..10^5],数组长度N [1..10^5]
    //要求复杂度 时间O(N), 空间O(M)。
    //分析： 这个问题很像只包含一种字符的子串，不同的是我们求的是个数。
    //我们用O(M)的空间维护一个滑动窗口，里面的值是不重复的。
    //对每个i,我们求出j满足窗口[i..j]之间元素是不重复的，
    //则以i开头包含的不重复的子数组数显然是j - i + 1，然后我们滑动i + 1的时候，
    //[i + 1..j]显然也没有重复字符，所以j不用重新算，接着算就好了。
    public class CalcNonMinus{
        int solution(int M, vector<int> &A) {  
            // write your code in C++98  
            int j = 0, result = 0;  
            vector<bool> have;  
            have.resize(M + 1, false);  
            for (int i = 0; i < A.size(); ++i) {  
                for (; (j < A.size()) && (!have[A[j]]); ++j) {  
                    have[A[j]] = true;  
                }  
                if ((result += j - i) >= 1000000000) {  
                    return 1000000000;  
                }  
                have[A[i]] = false;  
                
            }  
            return result;  
        }  
    }

    //（4） 给定一个整数数组，求两个数的和（可以是同一个数），满足绝对值最小。返回这个绝对值。
    //数据范围： 数组长度[1..10^5],数组元素[-10^9, +10^9]。
    //要求复杂度： 时间 O(NlogN), 空间O(1)。
    //分析： 和（1）差不多，先排序，再两边扫，类似2-sum,绝对值较大的指针动一下。

    public class clacMin{

        int ab(int x) {  
            return (x >= 0)?x:(-x);  
        }  
        
        int f(int x,int y) {  
            return ab(x + y);  
        }  
        
        int solution(vector<int> &A) {  
            // write your code in C++98  
            vector<int> a = A;  
            sort(a.begin(),a.end());  
            int answer = f(a[0],a[0]);   
            for (int i = 0, j = a.size() - 1; i <= j; ) {  
                answer = min(answer, f(a[i], a[j]));  
                if (ab(a[i]) > ab(a[j])) {  
                    ++i;  
                }  
                else {  
                    --j;  
                }  
                
            }  
            return answer;  
        }  

    }
}