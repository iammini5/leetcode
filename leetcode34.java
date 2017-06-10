public class leetcode34{
    //给定一棵树（无向无环图），从一个节点出发，每次选择一个节点，
    //从起点到目的节点的路径上没经过的节点尽可能多，直到遍历完所有的节点。
    //如果起点到两个目的节点的路径中没经过的节点同样多，则选择标号较小的节点作为目的节点。
    // 如此继续，直到遍历所有的节点，求按顺序选择了哪些目的节点？
    //例如从2 开始，第一次选择0，然后1，0变为经历过的节点。
    //然后从0开始，第二次选择6， 然后4，6变为经历过的节点。
    //然后从6开始，第三次选择3，然后3变为经历过的节点。
    //  //然后从3开始，最后一次选择5，然后5变为经历过的节点。
    //输出[2,0,6,3,5]
    //函数头部是 vector<int> solution(int K, vector<int> &T);
    //其中K是起点编号，(i，T[i])表示树的一条边。
    //变量范围节点数N， [1..90,000]，T元素范围[0..N-1]
    //要求时间复杂度O(N),空间复杂度O(N)。
    ///分析： 我觉得这个题我没能完美的解决，因为没给出严格的证明。首先目标节点肯定是叶子，直观上深度更深的叶子更有效。所以我按叶子深度由大到小对叶子排序，如果深度相同，我把编号小的叶子放前面。按照这个既定顺序给叶子定义权值，这个权值就是最后访问路径上经历的新节点数（这个需要严格证明）。权值的定义如下，沿着叶子不断向父亲走，直到第一个标记过的节点停止。这条路径上的节点数作为叶子的权值。并且这条路径上的节点都设置未标记过。关键问题是，要按照之前排好的顺序给每个叶子算权值（因为顺序会影响叶子的权值）。直观感受是，如果两个叶子在一个分叉上，显然深的节点更先被访问，如果不在一个分叉上，那么先算深的也没什么损失。最后，按照这个权值再对叶子排序一次，就是所要的结果。为了满足时间复杂度，我采用的是基数排序，写了一个help函数完成排序。
    ////所以大概思路就是： 
    //（1） 先dfs一次，得到每个叶子的深度和每个节点的父亲
    //（2） 对所有叶子按深度进行基数排序
    //（3） 按照排好的顺序计算每个叶子的权值（复杂度相当于遍历树，因为是沿着叶子向上遍历的）
    //（4） 按照计算的权值，再对叶子做一次基数排序 得到最终结果。
    public class Fluorum{
        void help(int n, vector<pair<int,int> > &v) {  // (id, weight)   
            vector<vector<int> > have;  
            have.resize(n);  
            for (int i = 0; i < v.size(); ++i) {  
                have[v[i].first].push_back(v[i].second);  
            }  
            v.clear();  
            for (int i = 0; i < n; ++i) {  
                for (int j = 0; j < have[i].size(); ++j) {  
                    v.push_back(make_pair(i, have[i][j]));  
                }  
            }  
            have.clear();  
            have.resize(n);  
            for (int i = 0; i < v.size(); ++i) {  
                have[v[i].second].push_back(v[i].first);  
            }  
            v.clear();  
            for (int i = n - 1; i >= 0; --i) {  
                for (int j = 0; j < have[i].size(); ++j) {  
                    v.push_back(make_pair(have[i][j] , i));  
                }  
                    
            }  
        }  
        void dfs(int x,int p,int d,vector<int> &depth, vector<int> &parent,vector<vector<int> > & con) {  
            parent[x] = p;  
            depth[x] = d;  
            for (int i = 0; i < con[x].size(); ++i) {  
                if (con[x][i] != p) {  
                    dfs(con[x][i], x, d + 1, depth, parent, con);  
                }  
                
            }  
            
        }  
        vector<int> solution(int K, vector<int> &T) {  
            // write your code in C++98  
            vector<vector<int> > con;  
            int n = T.size();  
            con.resize(n);  
            for (int i = 0; i < n; ++i) {  
                if (T[i] != i) {  
                    con[i].push_back(T[i]);  
                    con[T[i]].push_back(i);  
                }  
            }  
            vector<int> parent, depth;  
            depth.resize(n);  
            parent.resize(n);  
            dfs(K, -1, 0, depth, parent, con);  
            vector<pair<int,int> > v;  
            for (int i = 0; i < n; ++i) {  
                if ((i != K)  && (con[i].size() == 1)) {  //leaf  
                    v.push_back(make_pair(i, depth[i]));  
                }  
            }  
            help(n,v);  
            vector<bool> mark;  
            mark.resize(n, false);  
            for (int i = 0; i < v.size(); ++i) {  
                int x = -1;  
                for (int j = v[i].first; (j >= 0) && (!mark[j]); ++x, j = parent[j]) {  
                    mark[j] = true;  
                }  
                v[i].second = x;  
            }  
            help(n,v);   
            vector<int> result;  
            result.push_back(K);  
            for (int i = 0; i < v.size(); ++i) {  
                result.push_back(v[i].first);  
            }  
            return result;  
        }  
    }

}