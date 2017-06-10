public class leetcode17{
    //这个题比较简单，给定一个整数数组，对每个元素，求出和它最近比它大的数的距离（下标绝对值），
    //如果没有比它大的数，认为距离是0。
    //数组元素个数 N [0..50000]，数组元素范围[-10^9, +10^9]。
    //要求复杂度 时间 空间 都是O(N)。
    //分析：
    //这个题比较简单吧。跟直方图最大矩形差不多，类似于求左右边界。求左边界的话记住，有这个数在的话，
    //比它更早的并且比它小的数都没有意义（因为有这个数存在，而它又很大，右边的数往左找的话会先选择这个数）。
    //于是栈内元素是单调递减的，求右边界类似。 

    public class calcMaxDistance{
        vector<int> solution(const vector<int> &A) {  
            // write your code here...  
            int n = A.size(),i;  
            vector<int> a,r;  
            r.resize(n);  
            for (i = 0; i < n; ++i) {  
                while ((!a.empty()) && (A[a.back()] <= A[i])) {  
                    a.pop_back();  
                }  
                r[i] = a.empty()?n:(i - a.back());  
                a.push_back(i);  
            }  
            a.clear();  
            for (i = n - 1; i >= 0; --i) {  
                while ((!a.empty()) && (A[a.back()] <= A[i])) {  
                    a.pop_back();  
                }  
                if (!a.empty()) {  
                    r[i] = min(r[i], a.back() - i);  
                }  
                else if (r[i] == n) {  
                    r[i] = 0;  
                }  
                a.push_back(i);  
            }  
            return r;  
                    
        }  
    }
}