public class leetcode26{
    //一个裸的最短路。n个点，你住在0，要去买东西，每个点有一个关门时间，问能最早买到食物的时间。
    //有两点注意 （1）有重边　（２）　原图是dicrect连接。。。但不是有向边，被这个误导了。
    //direct当直接讲……原图无向图。后面就是裸的dijkstra算法。
    //数据范围 点 N [0..100] 不知道为啥还有0.。。。
    //连边 数 M [1..10000]
    //连边按3元祖给书 A 里面数据范围  [0..99] B 里面数据范围 [0..99]是节点 C [0..10^5]表示从A到B的时间
    //还有一个长度为N的数组D，表示关门时间，数值范围 [-1..10^9]表示关门时间，-1表示已经关门了。
    //返回能买到食品的最早时间，买不到输出-1。
    //要求复杂度 时间 O(N^2) 空间 O(N^2)。
    public class  Hydrogenium{
        int solution(const vector<int> &A, const vector<int> &B, const vector<int> &C, const vector<int> &D) {  
            // write your code here...  
            int n = D.size(),i,ind,m = A.size();  
            if ((n == 0) || (D[0] >= 0)) {  
                return 0;  
            }  
            
            vector<vector<int> > e;  
            e.resize(n);  
            
            for (i = 0; i < n; ++i) {  
                e[i].resize(n, -1);  
            }  
            for (i = 0; i < m; ++i) {  
                if ((e[A[i]][B[i]] < 0) || (e[A[i]][B[i]] > C[i])) {  
                    e[A[i]][B[i]] = e[B[i]][A[i]] = C[i];  
                }  
            }  
            vector<bool> mark;  
            mark.resize(n, false);  
            vector<int> d;  
            d.resize(n, -1);  
            d[0] = 0;  
            for (;;) {  
                ind = -1;  
                for (i = 0; i < n; ++i) {  
                    if ((!mark[i]) && (d[i] >= 0) && ((ind < 0) || (d[ind] > d[i]))) {  
                        ind = i;  
                    }  
                }  
                if (ind < 0) {  
                    break;  
                }  
                if (d[ind] <= D[ind]) {  
                    return d[ind];  
                }     
                mark[ind] = true;  
                for (i = 0; i < n; ++i) {  
                    if ((!mark[i]) && (e[ind][i] >= 0) && ((d[i] < 0) || (d[i] > d[ind] + e[ind][i]))) {  
                        d[i] = d[ind] + e[ind][i];  
                    }  
                }  
            }  
            return -1;  
        }  
    }
}