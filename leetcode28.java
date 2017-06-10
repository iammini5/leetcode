public class leetcode28{
    //这个问题现在还在比赛中……不过8月底应该就结束了。题目不难，是说有N个时钟，
    //每个时钟有M个指针，有P个刻度。时钟是圆形的，P个刻度均分整个圆。每个时钟每个指针指向整数刻度，
    //并且每个时钟自身指针指向的数字都不同。你可以任意旋转时钟的表盘，但是你不能转指针。
    //问最后有多少对时钟可以变成相同的状态。（状态是指每个指针的相对位置一样）。例如：
    //原始这个样子的时钟，可以旋转成

    //忽略刻度。然后（1，3） （1，4） （3，4） （2，5）看起来一样的。
    //输入规模 二维数组A[N][M]  N  M 都是[1..500],  P在[1..10^9]内。数组元素在[1..P]内。
    //要求复杂度 时间 O(N * MlogM + N * logN) 空间 O(N * M)。
    //分析： 因为只有相对的指针位置才有意义。我们对每个时钟，指针有小到大排序，时间复杂度O(MlogM)，
    //注意，然后计算差值，第一个要减去最后一个。这里有O(M)，然后我们需要建立最小表示，
    //也就是说以某个位置开始，把这些指针看成一个圈，形成的字符串字典序最小，这个有O(M)的经典算法，
    //这样我们可以唯一表示一个时钟状态,N个时钟，时间复杂度O(N * MlogM)。 
    //后面我们需要用一个map记录时钟查找时钟，对每个时钟，我们需要查找它出现多少次，
    //查找本身是O(logN)的,但这里判断相等的话，查找N个时钟一共O(NlogN）,
    //但这里我们忽略了map一个M个元素的vector的复杂度，感觉上应该还大一些…… 
    public class Lithium{
        vector<int> make(vector<int> &v) {  
        vector<int> a;  
        int i,j,k,t,n = v.size();  
            a.resize(n);  
            for (i = k = 0, j = 1; (i < n) && (j < n) && (k < n);) {  
                for (k = 0; (k < n) && ((t = v[(i + k) % n] - v[(j + k) % n]) == 0); ++k)  
                ;  
                if (k < n) {  
                    if (t > 0) {  
                        i += k + 1;  
                    }  
                    else {  
                        j += k + 1;  
                    }  
                    if (i == j) {  
                        ++j;  
                    }  
                }  
            }  
            if (i > j) {  
                i = j;  
            }  
            for (j = 0; j < n; ++j) {  
                a[j] = v[(i + j) % n];  
            }  
            return a;  
        }  
        
        int solution(const vector< vector<int> > &A, int P) {  
            // write your code here...  
            
        int n = A.size(), m = A[0].size(), i,j,answer;  
        vector<int> v,b;  
        map<vector<int>,int> have;  
        map<vector<int>,int>::iterator t;  
            vector<vector<int> > ppmm;  
            if (m == 1) {  
                return n * (n - 1) / 2;  
            }  
            b.resize(m);  
            for (i = answer = 0; i < n; ++i) {  
                v = A[i];  
                sort(v.begin(), v.end());  
                for (j = 1; j < m; ++j) {  
                    b[j] = v[j] - v[j - 1];  
                }  
                b[0] = v[0] + P - v[m - 1];  
                b = make(b);  
                t = have.find(b);  
                if (t == have.end()) {  
                    have.insert(make_pair(b, 1));  
                }  
                else {  
                    answer += t->second++;  
                }  
            }  
            return answer;  
        }  
    }
}