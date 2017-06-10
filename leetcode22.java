public class leetcode22{
    //用1 * 1， 2 * 2的矩形覆盖一个n行m列的矩形，问有多少种方法。
    //数据范围 : n [1..10^6],  m [ 1..7]
    //要求复杂度： 时间  O(log(n) * 8 ^m))  空间  O(4^m)
    //分析：这个题跟之前那个木块砌墙问题一样…… 稍作修改即可，又是矩阵乘法。

    public class Phi2012{
        vector<vector<int> > a;  
  
        const int MOD =  10000007;  
        
        int add(int x,int y) {  
            return ((x += y) >= MOD)?(x - MOD):x;  
        }  
        
        int mul(long long x,long long y) {  
            return x * y % MOD;  
        }  
        
        vector<vector<int> >  mulmatrix(vector<vector<int> > &a,vector<vector<int> > &b) {  
        vector<vector<int> > c;  
        int n = a.size(), i ,j, k;  
            c.resize(n);  
            for (i = 0; i < n; ++i) {  
                c[i].resize(n , 0);  
                for (j = 0; j < n; ++j) {  
                    for (k = 0; k < n; ++k) {  
                        c[i][j] = add(c[i][j], mul(a[i][k],b[k][j]));  
                    }  
                }  
            }  
            return c;  
        }  
                
        
        void count(int col, int n, int last, int now) {  
            if (col >= n) {  
                ++a[last][now];  
                return;  
            }  
            count(col + 1, n, last, now);  
            if (((last & (1 << col)) == 0) && (col + 1 < n) && ((last & (1 << (col + 1))) == 0)) {  
                count(col + 2, n, last, now | (3 << col));  
            }  
        }  
            
        
        int solution(int N, int M) {  
            // write your code here...  
        int i,total = 1 << M;  
        vector<vector<int> > r;  
            a.resize(total);  
            r.resize(total);  
            for (i = 0; i < total; ++i) {  
                a[i].resize(total, 0);  
                count(0, M, i, 0);  
                r[i].resize(total, 0);  
                r[i][i] = 1;  
            }  
            for (; N ; N >>= 1) {  
                if (N & 1) {  
                    r = mulmatrix(r, a);  
                }  
                a = mulmatrix(a, a);  
            }  
            
            return r[0][0];  
            
                
        }  

    }
}