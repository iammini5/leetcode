public class leetcode37{
    //给定一个无向图，不保证是平面图，可能有自环，有重边，总之是任意一个无向图。有边权，
    //你可以从任何点出发，任何点结束，可以经过同一个定点任意多次。
    //但是你走过的路必须满足所有边的权值严格单调递增，求最长能经过多少条边。
    //图是按照边给定的，函数头部：
    //int solution(int N, vector<int> &A, vector<int> &B, vector<int> &C)
    //其中 N 表示节点数,A B 表示边的定点[0..N-1]，C表示边权。
    //数据范围：节点数N [1..2*10^5],边数[0..2*10^5]，权值范围[1..10^9]，整数。
    //  要求时间复杂度：O(N + MlogM)，空间复杂度O(N + M)。
    //分析： 有点dp的思路，如果我们把边按照权重排序，则直接加边就可以了。
    //例如dp[x]表示当前以x为终点的最长路。那么加上一条边(x,y)后，dp[y] = max(dp[x] + 1, dp[y])，
    //大概就是这个思路。但是注意边权有相同的，我们必须把相同边权的边统一一起处理掉，
    //并且暂时不能更新，只有等所有相同边权的边都计算完之后再更新。因为是按边处理的，
    //所以复杂度是O(M)，当然还有排序的O(MlogM)，感觉没N的事。。。
    public class Magnesium{
        int solution(int N, vector<int> &A, vector<int> &B, vector<int> &C) {  
            // write your code in C++98  
            vector<pair<int,pair<int,int> > > road;  
            vector<int> level, dist;  
            dist.resize(N, 0);  
            int result = 0;  
            for (int i = 0; i < A.size(); ++i) {  
                road.push_back(make_pair(C[i], make_pair(A[i], B[i])));  
                road.push_back(make_pair(C[i], make_pair(B[i], A[i])));  
            }  
            sort(road.begin(), road.end());  
            for (int i = 0; i < road.size();) {  
                int x = i;  
                vector<pair<int,int> > temp;  
                for (; (i < road.size()) && (road[x].first == road[i].first); ++i) {  
                    if (dist[road[i].second.second] <= dist[road[i].second.first]) {  
                        result = max(result, dist[road[i].second.first] + 1);  
                    // printf("from = %d, to = %d, level = %d, dist = %d\n",road[i].second.first, road[i].second.second, road[i].first, dist[road[i].second.first] + 1);  
                        temp.push_back(make_pair(road[i].second.second, dist[road[i].second.first] + 1));  
                    }  
                    
                }  
                for (int j = 0; j < temp.size(); ++j) {  
                    dist[temp[j].first] = max(dist[temp[j].first], temp[j].second);  
                }  
                
            }  
            return result;  
        }  

    }
    
}