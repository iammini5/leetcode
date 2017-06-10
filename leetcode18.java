public class leetcode18{
    // 从正整数1开始，产生一个数列，数列中的每个数是之前出现过的任意两个数的和（可以相等），
    //问产生正整数A，需要的数列长度至少是多少？返回这样一个最短的序列。
    //例如A=42 可以这样[1, 2, 3, 6, 12, 24, 30, 42]，也可以[1, 2, 4, 5, 8, 16, 21, 42]，
    //后者是最短的。A不大于600。

    //分析： 本题没规定时间、空间复杂度。因为本题只能暴力搜索，但是一般的实现会超时，
    //需要一些剪枝。首先保证数列严格单增。另外，我们用迭代加深dfs做的时候，
    //注意看一下剩余的长度能不能达到所要找的数，判断的方法很简单，达到一个数最快的方法是，
    //每次把最大的翻倍……另外可以估算下序列的长度不超过2*logA，
    //因为我们可以按2进制位把A需要的那些2^n都产生出来，再加出A……代码没啥好说的……
    public class Rho2012{
        public bool dfs(int A,vector<int> &v,int length) {  
            if (v.back() == A) {  
                return true;  
            }  
            if (v.size() >= length) {  
                return false;  
            }  
            int i,j, x;  
            for (i = v.back(), j = length - v.size(); (i < A) && (j); i <<= 1, --j)  
                ;  
            if (i < A) {  
                return false;  
            }          
            for (i = v.size() - 1; (i >= 0) && (v[i] << 1) > v.back(); --i) {        
                for (j = i; (j >= 0) && ((x = v[i] + v[j]) > v.back()); --j) {   
                    if (x <= A) {  
                        v.push_back(x);  
                        if (dfs(A, v, length)) {  
                            return true;  
                        }  
                        v.pop_back();  
                    }  
                }  
            }  
            return false;  
        }          
        vector<int> solution(int A) {  
            // write your code here...  
        vector<int> v;  
        int i;  
            v.push_back(1);  
            for (i = 1; !dfs(A, v, i); ++i)  
                ;  
            return v;  
        }  
    }
}