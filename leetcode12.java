public class leetcode12{
    //(1) MinMaxDivision
    //给定一个非负整数数组，每个整数都是[0..M]之间的，你要把它分成K段，
    //（切K - 1刀），段可以为空，每个元素必须属于一段，
    //每段必须包含0个或者多个连续的元素，要求分好和最大段的和尽量小，返回这个尽可能小的最大和。
    //数据范围 ：N, K [1..10^5]， M [0..10^4]
    //要求复杂度 时间 O(N * log(N + M)) 空间 O(1)。

    //分析：典型的二分我们可以。二分一个最大段的和，然后我们一段一段地加，
    //超过要加的值，就开始一段新的。这个方法得力于都是非负整数……
    //简单说一下复杂度，二分问题的框架就是 二分 ＋ 判断。 
    //判断部分显然是O(N)的。 二分的复杂度取决于二分的区间大小。
    //我们的二分区间左端点可以认为是min(A[i])，也可以认为是0，反正区间大点也关系，
    //右端点最大是N * M，那么二分的复杂度是
    //O(log(N * M)) = O(logN + logM) = O(2 * log(max(M, N)) = O(log(max(M, N)) = O(log (M + N)) 
    //所以算上检测的复杂度就达到要求的那个了。
    public class MinMaxDivision{
        public boolean can(vector<int> &a,int x, int k) {  
        int sum = 0;  
            --k;  
            for (int i = 0; i < a.size();) {  
                if ((sum += a[i]) > x) {  
                    if (--k < 0) {  
                        return false;  
                    }  
                    sum = 0;  
                }  
                else {  
                    ++i;  
                }  
                
            }  
            return true;  
        }  
        int solution(int K, int M, vector<int> &A) {  
            // write your code in C++98  
            int left = 0,right = -1;  
            for (int i = 0; i < A.size(); ++i) {  
                right += A[i];  
                left = max(left, A[i]);  
                
            }  
            
            while (left <= right) {  
                int mid = (left + right) >> 1;  
                if (can(A, mid, K)) {  
                    right = mid - 1;  
                }  
                else {  
                    left = mid + 1;  
                }  
            }  
            return right + 1;  
            
        }  
    }

    //（2） NailingPlanks
    //N块木板，可以看作N线段，给定两个长度为N的正整数数组A[],B[]，[A[k],B[k]]表示木板（线段）的起点和终点，
    //A[k] <= B[k]。有M个钉子，它们分别在长度为M的正整数数组里。钉子I可以固定住木板K，
    //当且今当A[K]<=C[I]<=B[K]。问按顺序使用钉子，至少使用前多少个钉子可以固定住所有木板？无解返回-1。
    //数据范围： 木板数N和M的范围［1..30000]， A B C数组元素范围为[1..2 * M]
    //要求复杂度： 时间O((N+M)*log(M)) ， 空间O(M)
    //分析： 一个显然的并且符合要求的算法是二分答案，问题是如何判断，显然我们不能循环木板和钉子。
    //但是我们可以计算从开头到当前位置一共有多少个钉子，这是前缀和的思想。
    //计算前缀和需要O(M)，判断需要O(N)，二分是O(logM)，所以整好是要求的时间复杂度。空间上需要存前缀和O(M)。
    public class NailingPlanks{
        public int solution1(vector<int> &A, vector<int> &B, vector<int> &C) {  
            // write your code in C++98  
            int m = C.size();  
            int M = (m << 1) | 1;  
            int left = 0, right = m, result = -1;  
            while (left <= right) {  
                int mid = (left + right) >> 1;  
                vector<int> v;  
                v.resize(M, 0);  
                for (int i = 0; i < mid; ++i) {  
                    ++v[C[i]];  
                }  
                for (int i = 1; i < M; ++i) {  
                    v[i] += v[i - 1];  
                }  
                bool can = true;  
                for (int i = 0; i < A.size(); ++i) {  
                    if (v[B[i]] - v[A[i] - 1] == 0) {  
                        can = false;  
                        break;  
                    }  
                }  
                if (can) {  
                    result = mid;  
                    right = mid - 1;  
                }  
                else {  
                    left = mid + 1;  
                }  
            }  
            return result;  
        }  

        //更快的算法，如果我们建立一个长度为2 * M的数组，
        //每个位置表示该位置上钉子的最小编号（可能同一个位置有多个钉子，取编号最小的），
        //没有钉子的位置值为无穷大。那么固定第i块木板的最小编号钉子，
        //相当于[A[i],B[i]]区间的最小值。但是我们这个题实际上是求这些最小值的max，
        //首先如果一个木板A的覆盖区间完全包含另外一个木板B,则实际上我们只考虑木板B即可。
        //因为固定木板B同时能固定木板A，并且我们一定要固定木板B，即使A覆盖区间有更小的值，
        //也无法改变最终取最大值的结果。
        //于是，我们可以建立一个数组plank[x]表示右端点为x的木板的最大左边界，
        //没有木板的话，认为边界是0。我们从左到右遍历木板右边界，
        //假设这之前（更左）的木板已经被固定了，已经固定的区间范围是[left,right) (右开区间），
        //然后对当前这个木板，如果显然它的右边界更大（我们遍历右边界是按当增的顺序），
        //如果该木板start <= left,则它已经被前面固定住了，不影响结果。
        //否则，要求[start,end]之间的最大值。这个问题有点像滑动窗口最大值的问题。
        //本质在于：我们不断查询最大值，每次查询的时候窗口的左边界和右有边界是单调递增的，
        //于是我们可以动态更新窗口维护最大值。这个经典问题可以用单调队列实现，这也是把单调队列发挥到了极致。
        //结论： 查询窗口最大值的时候，如果窗口向右滑动的过程中，
        //查询时左边界和右边界都是单增的，则可以使用单调队列解决。
        //时间复杂度 O(N + M)达到了线性。
        final int inf = 2000000000;  
        int solution(vector<int> &A, vector<int> &B, vector<int> &C) {  
            // write your code in C++98  
            int m = C.size(), M = (m << 1) | 1;  
            vector<int> nail(M, inf);  
            for (int i = m - 1; i >= 0; --i) {  
                nail[C[i]] = i;  
            }  
            vector<int> plank(M, 0);  
            for (int i = 0; i < A.size(); ++i) {  
                plank[B[i]] = max(plank[B[i]], A[i]);  
            }  
            int left = 0, right = 0, r = 0;  
            deque<int> q;  
            for (int i = 1; i < M; ++i) {  
                if (plank[i] > left) {  
                    left = plank[i];  
                    while ((!q.empty()) && (q.front() < left)) {  
                        q.pop_front();  
                    }  
                    for (right = max(right, left); right <= i; ++right) {  
                        while ((!q.empty()) && (nail[q.back()] >= nail[right])) {  
                            q.pop_back();  
                        }  
                        q.push_back(right);  
                    }  
                    r = max(r, nail[q.front()]);  
                    if (r >= inf) {  
                        return -1;  
                    }  
                }  
            }  
            return r + 1;  
        }  

    }
}