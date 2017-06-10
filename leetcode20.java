public class leetcode20{
    //给定一个m行n列的矩阵，我们认为矩阵是循环的，也就是说第0列和第(n - 1)列是相邻的，
    //第0行和第(m - 1)行是相邻的。求最大子阵和……
    //数据范围：m.n [1..100],矩阵中每个整数[-10000, +10000]。 要求复杂度O(m^3 + n ^3) 空间复杂度O(m * n)。
    //分析： 首先循环最大子段和的问题，我们可以在O(n)时间内解决。
    //因为循环最大子段和等于 max(非循环的最大子段和，总和 - 非循环的最小子段和)。 
    //那么这个问题，我们首先把矩阵复制一下，就是在第(m - 1)行下面再连接一个相同的矩阵。
    //然后我们按列记录s[i][j]表示从第0行到第i行这一列数的总和。 假设我们最终选定的矩阵在某两行之间的话，
    //我们所求的相当于s[]在这两行之间的循环最大子阵和。之所以把矩阵复制了一份是因为上下循环的关系。
    //我们复制一下就可以统一处理。所以以后只要枚举开始的行，枚举行数，再求最大循环子段和就好了。
    public class Tau2012{
        int maximum(vector<int> &a) {  
        int i, best, end;  
            for (i = best = end = 0; i < a.size(); ++i) {  
                if (end < 0) {  
                    end = 0;  
                }  
                if ((end += a[i]) > best) {  
                    best = end;  
                }  
            }  
            return best;  
        }  
        
        int solution(const vector< vector<int> > &C) {  
            // write your code here...  
            int m = C.size(), n = C[0].size(),i,j,k,s,answer;  
            vector<vector<int> > a;  
            a.resize(m << 1);  
            a[0].resize(n, 0);  
            for (i = 1; i <= m; ++i) {  
                a[i].resize(n);  
                for (j = 0; j < n; ++j) {  
                    a[i][j] = a[i - 1][j] + C[i - 1][j];  
                }  
            }  
            for (i = 1; i < m; ++i) {  
                a[i + m].resize(n);  
                for (j = 0; j < n; ++j) {  
                    a[i + m][j] = a[i + m - 1][j] + C[i - 1][j];  
                }  
            }  
            answer = 0;  
            vector<int> b;  
            b.resize(n);  
            for (i = 1; i <= m; ++i) {  // number of row to choose  
                for (j = 1; j <= m; ++j) {  // begin of row  
                    for (k = 0; k < n; ++k) {  
                        b[k] = a[j + i - 1][k] - a[j - 1][k];  
                    }  
                    answer = max(answer, maximum(b));  
                    for (k = s = 0; k < n; ++k) {  
                        s += b[k];  
                        b[k] = -b[k];  
                    }  
                    answer = max(answer, s + maximum(b));  
                }  
            }  
            return answer;  
                
            
        }  
    }
}