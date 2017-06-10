public class leetcode31{
    //给定一个字符串，求其每个前缀的长度及其出现次数的乘积的最大值。
    //例如：
    // "abababa" 
    //"a"出现4次，1 * 4 = 4,
    //"ab"出现3次 2 * 3 = 6,
    //"aba"出现3次 3 * 3 = 9,
    //"abab"出现2次 4 * 2 = 8,
    ////"ababa"出现2次 5 * 2 = 10,
    //"ababab"出现1次 6 * 1 = 6,
    //"abababa"出现1次 7 * 1 = 7
    //于是应该输出10.
    //字符串长度[1..300000]，只包含小写字母。如果结果超过10^9，则输出10^9。
    //要求时间复杂度O(N),空间复杂度O(N)。

    //分析：这个题我几乎沿用了（27）的前半段代码。其实就是ext_kmp。如果我们能算出每个位置和前缀匹配的最长长度p[i]，然后按照p[i]的大小，从大到小进行累加，这是因为如果长度为x的前缀出现在某位置，那么所有长度不超过x的前缀都在该位置出现。我们在计算某个长度i的前缀出现的总次数是total，然后计算total * i就可以了，注意乘法溢出就好。
    public class Carbo{
        int solution(string &S) {  
            // write your code in C++98  
            int n = S.length(),left,right,i,j,len;  
            vector<int> p;  
            vector<int> num;  
            num.resize(n + 1, 0);  
            p.resize(n , 0);  
            num[n] = 1;  
            left = right = -1;  
            for (i = 1; i < n; ++i) {  
                
                if (right < i) {  
                    for (j = 0; (i + j < n) && (S[j] == S[j + i]); ++j)  
                    ;  
                    if (j) {  
                        p[i] = j;  
                        left = i;  
                        right = i + j - 1;  
                    }  
                }  
                else if (right - i < p[i - left]) {  // right - i + 1 <= p[i - left]  
                    for (j = right + 1; (j < n) && (S[j] == S[j - i]); ++j)  
                    ;  
                    p[i] = j - i;  
                    left = i;  
                    right = j - 1;  
                }  
                else {  
                    p[i] = p[i - left];  
                }  
                ++num[p[i]];  
                
            }  
            int total = 0, answer = 0;  
            for (int i = n; i ; --i) {  
                total += num[i];  
                if (total  > 1000000000 / i) {  
                    return 1000000000;  
                }  
                answer = max(answer ,total * i);  
            }  
            return answer;  
        }  
    }
}