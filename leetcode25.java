public class leetcode25{
    //这个题有意思。
    //有一口井，井的高度为N，每隔1个单位它的宽度有变化。现在从井口往下面扔圆盘，
    //如果圆盘的宽度大于井在某个高度的宽度，则圆盘被卡住（恰好等于的话会下去）。每隔盘子有几种命运：
    //（1） 掉到井底
    //（2） 被卡住
    //（3） 落到别的盘子上方
    //盘子的高度也是单位高度。给定井的深度和每个盘子的宽度，求最后落到井内的盘子数。

    //如图井和盘子信息如下：
    // A[0] = 5    B[0] = 2
    // A[1] = 6    B[1] = 3
    // A[2] = 4    B[2] = 5
    // A[3] = 3    B[3] = 2
    // A[4] = 6    B[4] = 4
    // A[5] = 2
    // A[6] = 3

    //返回4。
    //数据范围 井深度 N [1..200000], 盘子个数M [1..200000]， 数组元素都在[1..10^9]
    //要求复杂度： 时间 空间都是O(N)。
    //分析； 这个题不难。如果一个盘子j能落到第i层，必须min{A[0..i]} >= B[j],所以我们记录最小值就可以了。
    //如果记录最小值的下标，向上找的可以“快”一点，但是空间复杂度要O(N)。
    //因为最多N个盘子，所以时间复杂度O(N)。
    public class Omega2013{
        int solution(vector<int> &A, vector<int> &B) {  
            // write your code here...  
            int n = A.size(),i, h;  
            vector<int> height;  
            height.resize(n);  
            height[0] = 0;  
            for (i = 1; i < n; ++i) {  
                height[i] = (A[i] < A[height[i - 1]])?i:height[i - 1];  
                
            }  
            for (i = 0,h = n - 1; (h >= 0) && (i < B.size()); --h, ++i) {  
                for  (;(h >= 0) && (A[height[h]] < B[i]) && (h >= 0); h = height[h] - 1)  
                ;  
                if (h < 0) {  
                    break;  
                }  
            }  
            return i;  
        }  

        //我们利用A数组的值，存放前i项的最小值的话可以有空间复杂度为O(1)的算法。
        public int solution2(vector<int> &A, vector<int> &B) {  
            // write your code here...  
            int n = A.size(),i,r = 0;  
            for (i = 1; i < n; ++i) {  
                A[i] = min(A[i - 1], A[i]);  
            }  
            for (i = 0; i < B.size();++i) {  
                for (--n; (n >= 0) && (A[n] < B[i]); --n)  
                ;  
                if (n >= 0) {  
                    ++r;  
                }  
                else {  
                    break;  
                }  
            }  
            return r;  
                
        }  
    }
}