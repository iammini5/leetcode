public class leetcode39{
    //切蛋糕问题：
    //一个矩形的蛋糕，长为X，宽为Y，沿着X和Y轴各切了刀，形成(N + 1) ^ 2 小块。求面积第K大的块的面积。

    //  函数头部：
    //int solution(int X, int Y, int K, vector<int> &A, vector<int> &B);

    //数据范围：
    //N   [1..40000];
    //X, Y [2..4 * 10 ^ 8]
    //相邻两刀之间的距离（包括刀和边界的距离）<=10000
    //要求复杂度时间： Nlog(N + X + Y)
    //空间： O(N)
    //思路： 其实这个题就是杨氏矩阵行列乘积的最大值…… 有复杂度更底的算法…… 对于这个复杂度，
    //用二分就行了。。。当然X,Y要先排序。
    //二分的思路很简单，从最小块到最大块枚举一个整数，然后看一下它氏第几大的，看它是第几大的时候，
    //可以利用类似于2-sum的方法O(N)实现。
    public class Silicium{
        int cal(vector<int> &a,vector<int> &b,long long num) {  
            int n = a.size(), ans = 0;  
                for(int i = 0, j = n - 1; i < n; ++i) {  
                    for(; (j >= 0) && (a[i] * b[j] > num) ; --j)  
                    ;  
                    ans += n - 1 - j;  
                }  
                return ans;  
                
            }  
            
            int bs(vector<int> &a,vector<int> &b,int k) {  
            int n = a.size(), left = a[0] * b[0], right = a[n - 1] * b[n - 1];  
                while (left <= right) {  
                    int mid = (left + right) >> 1;  
                    if (cal(a, b, mid) >= k) {  
                            left = mid + 1;  
                    }  
                    else {  
                        right = mid - 1;  
                    }  
                }  
                return right + 1;  
            }  
            
            
            
            int solution(int X, int Y, int K, vector<int> &A, vector<int> &B) {  
                // write your code in C++11  
                A.push_back(X);  
                B.push_back(Y);  
                for (int i = A.size() - 1; i > 0; --i) {  
                    A[i] -= A[i - 1];  
                    B[i] -= B[i - 1];  
                    
                }  
                sort(A.begin(),A.end());  
                sort(B.begin(),B.end());  
                return bs(A, B, K);  
            }  
    }
}