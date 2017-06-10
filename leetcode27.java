public class leetcode27{
    //给定一个字符串S，找到另外一个字符串T，T既是S的前缀，也是S的后缀，
    //并且在中间某个地方也出现一次，并且这三次出现不重合。返回T的最长长度。
    //例如：输入数据是"barbararhubarb" ，输出为1。虽然barb也既是前缀也是后缀，但是在中间没出现过。
    //输入串长度N, [0..10^6] 只包含26个字母。
    //要求复杂度：时间空间都是O(N)。
    //分析： 长度包含0……比较可恶，干脆把长度小于3的都扔回去，因为要至少3次出现不重合。
    //我们计算如下一个函数p, p[x]表示s[x..N - 1]这个子串和s本身的最长公共前缀的长度。
    //我们不定义p[0],所以x > 0。我们试图线性时间计算p。
    //假设我们已经计算出p[1],p[2],p[i - 1]，我们定义 right = max{p[y] + y - 1}，
    //即right是前面求得所有前缀的最右边界，left是right取得最大值的y。
    //也就是说s[left..right]和s[0..right - left + 1]是一样的。初始定义left = right = -1。
    //(1) 如果我们发现，right >=i， 这说明 i被包含在窗口内，
    //于是我们发现s[i..right]和s[i'..right - left + 1]是一样的，其中i' = i - left。而p[i']已经计算过了。
    //（1a) 如果p[i'] < right - i + 1, 这说明这个字符串完全被窗口限定住了，我们立刻得到p[i] = p[i']。
    //(1b) 如果p[i'] >= right - i + 1,这时所得到的前缀可能更长，
    //我们已经至少有p[i] >= p[i']了。我们继续比较(right + 1)和（i' + p[i']) …… 直到不match，
    //然后更新right和left 。
    //（2） 如果我们发现right < i， 那么只好沿着i暴力比较前缀，可能的话，更新left和right。 
    //这个算法叫做Z方法，从一本书上看到的……
    //时间复杂度是O(N)的，因为right几乎每次都会变，如果不变的话，
    //最多浪费一次字符串比较（第一次比较就不相等）。
    //如果我们能计算出这个p，那么我们枚举最后所求的长度。len = n / 3逆向枚举。
    //如果后缀满足条件 必须有 p[n - len] == len
    //如果中间还得出现一次并且不重叠 必须有 max{p[len],p[len + 1],...p[n - len * 2]} >= len，
    //如果len么次增加1，这个集合每次左边多一个候选值，右边多两个候选值，动态更新就可以了。
    //这个应该算一道比较难的面试题了。
    public class Helium{
        int solution(string &S) {  
            // write your code here...  
            int n = S.length(),left,right,i,j,m,len;  
            if (n < 3) {  
                return 0;  
            }  
        
            vector<int> p;  
            p.resize(n, 0);  
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
                
            }  
            len = n / 3;  
            j = n - len * 2;  
            for (m = 0, i = len; i <= j; ++i) {  
                m = max(m, p[i]);  
                if ((m >= len) && (p[n - len] == len)) {  
                    return len;  
                }  
            }  
            for (--len; len > 0; --len) {  
                m = max(m, p[len]);  
                m = max(m, p[++j]);  
                m = max(m, p[++j]);  
                if ((m >= len) && (p[n - len] == len)) {  
                    break;  
                }  
            }  
            return len;  
            
        }  
    }
}