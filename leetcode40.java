public class leetcode40{
    //给定n个绳子，每个绳子编号0..N - 1。每个绳子下面挂一个重物，
    //每个绳子另外一端（不挂重物的那端），可以挂在其他的绳子上，
    //也可以挂在顶端（只有一个顶端），这些绳子形成一个树。树的结构由数组A,B,C,给出。
    //其中A表示绳子的承受力，如果挂载绳子下的总重量大于绳子的承受力，绳子会断。
    //B表示绳子一端挂的重物的重量，C表示该绳子另外一端挂的绳子的编号(C[i] < i)。
    //挂绳子的顺序就是编号从小到大的顺序，求最多挂几根绳子而不会由绳子断掉？
    //例如：
    //A[0] = 5    B[0] = 2    C[0] = -1
    //A[1] = 3    B[1] = 3    C[1] = 0
    //A[2] = 6    B[2] = 1    C[2] = -1
    //A[3] = 3    B[3] = 1    C[3] = 0
    //A[4] = 3    B[4] = 2    C[4] = 3
    //返回3，因为挂到第4根的时候（编号为3），绳子会断 （0号断了）。
    //又如：
    //A[0] = 4    B[0] = 2    C[0] = -1
    //A[1] = 3    B[1] = 2    C[1] = 0
    //A[2] = 1    B[2] = 1    C[2] = 1

    //返回2。
    //函数头部：
    //int solution(vector<int> &A, vector<int> &B, vector<int> &C)

    //数据范围：
    //N [0..10^5]
    //A数组数据范围是[1..10^6]
    //B数组数据范围是[1..5000]

    //要求复杂度: 时间 O(NlogN) 空间O(N)

    //分析：这个题似乎可以像Tarjan的LCA算法那样求，但是很麻烦，我也没细想…… 有一个显然的简单思路，
    //就是二分。二分绳子数，然后只考虑编号<= mid的那些绳子，看看这个树会不会有断掉的绳子，
    //决定二分的方向……
    //总之，不是一个难题，描述很麻烦，直接上代码：
    public class Sulphur{
        bool dfs(int x,int m,vector<vector<int> > &con, vector<int> &A,vector<int> &B, vector<int> &w) {  
            w[x] = B[x];  
            for (int i = 0; (i < con[x].size()) && (con[x][i] < m); ++i) {  
                if (!dfs(con[x][i], m, con, A, B, w)) {  
                    return false;  
                }  
                w[x] += w[con[x][i]];  
            }  
            return w[x] <= A[x];  
        }  
        
        bool can(int m,vector<vector<int> > &con,vector<int> &A,vector<int> &B) {  
        vector<int> w(m,-1);  
            for (int i = 0; i < m; ++i) {  
                if ((w[i] < 0) && (!dfs(i,m, con, A, B,w))) {  
                    return false;  
                }  
            }  
            return true;  
        }  
        
        int solution(vector<int> &A, vector<int> &B, vector<int> &C) {  
            // write your code in C++11  
            int n = A.size();  
            vector<vector<int> > con(n);  
            for (int i = 0; i < n; ++i) {  
                if (C[i] >= 0) {  
                    con[C[i]].push_back(i);  
                }  
            }  
            int left = 1, right = n;  
            while (left <= right) {  
                int mid = (left + right) >> 1;  
                if (can(mid,con, A, B)) {  
                    left = mid + 1;  
                }  
                else {  
                    right = mid - 1;  
                }  
            }  
            return left - 1;  
        }  
    }
    
}