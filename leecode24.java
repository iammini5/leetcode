public class leetcode24{
    //给定一个n * n 的格子，边都是导线，M秒，每秒有一根导线被烧断，
    //问从(0,0)到(n - 1, n - 1)在第几秒后不连通？如果一直连通的话，返回-1.
    //输入为烧掉的边，也就A,B,C如果C= 0 ，烧掉的是(A,B) (A, B + 1) , C = 1烧掉的是(A,B) (A + 1, B)。
    //于是输入的就是n和3个数组。C的输入比较坑，每个数组给长度，其实A,B,C三个数组都是一样长的嘛。
    //数据范围： N [1..400] , M [0..2 * N * (N - 1)]  A,B的范围是[0, N - 1] C里面只包含0和1。
    //要求复杂度： 时间 O(N^2logN) 空间 O(N^2)。
    //分析： 这个题可以2分时间，然后判断连通性，复杂度达到它的要求，但是我c++用dfs堆栈溢出，
    //bfs超时，双向bfs也超时，结果用c写了一个双向bfs过了……
    public class Psi{
        int e[402][402];  
        int mark[402][402];  
        int q[2][80002];  
        int head[2],tail[2];  
        
        int help(int num,int N,int *A,int *B, int *C) {  
        
            const int dx[] = {-1, 0 , 1, 0};  
            const int dy[] = {0, 1, 0, -1};  
        
        int i,x,y,j,k,xx,yy;  
        
            
            for (i = 0; i < N; ++i) {  
                for (j = 0; j < N; ++j) {  
                    e[i][j] = 15;  
                    mark[i][j] = -1;  
                }  
            }  
            for (i = 0; i < num; ++i) {  
                if (C[i]) {  
                    e[A[i]][B[i]] &= 11;  
                    e[A[i] + 1][B[i]] &= 14;  
                }  
                else {  
                    e[A[i]][B[i]] &= 13;  
                    e[A[i]][B[i] + 1] &= 7;  
                }  
            }  
            q[0][0] = 0;  
            q[1][0] = ((N - 1) << 9) | (N - 1);  
            mark[0][0] = 0;  
            mark[N - 1][N - 1] = 1;  
            tail[0] = tail[1] = 1;  
            head[0] = head[1] = 0;  
            for (i = 0; ; i ^= 1) {  
                if (head[i] == tail[i]) {  
                    return 0;  
                }  
                for (j = tail[i] - head[i]; j; --j) {  
                    x = q[i][head[i]] >> 9;  
                    y = q[i][head[i]++] & 511;  
                    for (k = 0; k < 4; ++k) {  
                        xx = x + dx[k];  
                        yy = y + dy[k];  
                        if ((xx >= 0) && (xx < N) && (yy >= 0) && (yy < N) && (e[x][y] & (1 << k)) && (mark[xx][yy] != i)) {  
                            if (mark[xx][yy] == 1 - i) {  
                                return 1;  
                            }  
                            mark[xx][yy] = i;  
                            q[i][tail[i] ++] = ((xx << 9) | yy);  
                        }  
                    }  
                }  
            }  
            return 0;  
                
        }  
        int solution(int N, int A[], int M, int B[], int M2, int C[], int M3) {  
            // write your code here...  
        int left = 1, right = M ,mid;  
            while (left <= right) {  
                if (help(mid = (left + right) >> 1, N , A, B, C)) {  
                    left = mid + 1;  
                }  
                else {  
                    right = mid - 1;  
                }  
            }  
            return (++right > M)?(-1):right;  
        }  
    }

    //当然这不是最好的方法，最好的方法是用并查集，我们没有“拆查集”，我们把时间倒过来，
    //从最后一条烧掉的边开始加回来，直到起点和终点相连……并查集的复杂度比log低……
    public class Psi22012{
        vector<int> f;  
  
        int getf(int x) {  
            return (x == f[x])?x:(f[x] = getf(f[x]));  
        }  
        
        void make(int x,int y) {  
            x = getf(x);  
            y = getf(y);  
            if (x != y) {  
            f[x] = y;  
            }  
        }  
        
        int solution(int N, const vector<int> &A, const vector<int> &B, const vector<int> &C) {  
            // write your code here...  
            vector<vector<int> > e;  
            int i, j, n, m = A.size();  
            e.resize(N);  
            for (i = 0; i < N; ++i) {  
                e[i].resize(N, 15);  
            }  
            for (i = 0; i < m; ++i) {  
                if (C[i]) {  
                    e[A[i]][B[i]] &= 11;  
                    e[A[i] + 1][B[i]] &= 14;  
                }  
                else {  
                    e[A[i]][B[i]] &= 13;  
                    e[A[i]][B[i] + 1] &= 7;  
                }  
            }  
            n = N * N;  
            f.resize(n);  
            for (i = 0; i < n; ++i) {  
                f[i] = i;  
            }  
            for (i = 0; i < N; ++i) {  
                for (j = 0; j < N; ++j) {  
                    if ((i + 1 < N) && (e[i][j] & 4)) {  
                        make(i * N + j, (i + 1) * N + j);  
                    }  
                    if ((j + 1 < N) && (e[i][j] & 2)) {  
                        make(i * N + j, i * N + j + 1);  
                    }  
                }  
                
            }  
            if (getf(0) == getf(n - 1)) {  
                return -1;  
            }  
            for (i = m - 1; i >= 0; --i) {  
                if (C[i]) {  
                    make(A[i] * N + B[i], (A[i] + 1) * N + B[i]);  
                }  
                else {  
                    make(A[i] * N + B[i], A[i] * N + B[i] + 1);  
                }  
                if (getf(0) == getf(n - 1)) {  
                    break;  
                }  
            }  
            return i + 1;  
        }  
    }


}