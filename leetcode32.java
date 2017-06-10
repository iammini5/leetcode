public class leetcode32{
    //比如说这个题，给定数组A，表示每个点的高度。起初所有的点都是连接在一起的。还有一个数组B，表示水面的高度。如果水面高度大于等于某点高度，则这个点在水面下。问每天在水面上的点有多少个连通区域。（一个连通区域就是一条线段，全在水面上，他称为岛屿）
    //举例：
	//A[0] = 2    B[0] = 0
    //	A[1] = 1    B[1] = 1
    //	A[2] = 3    B[2] = 2
    //	A[3] = 2    B[3] = 3
    //	A[4] = 3    B[4] = 1
    //第0天所有的点是一个岛屿，
    ////第1天1号点在水面下，所以分为了两个岛屿。
    //第2天只有2，4两个点在水面上，所以也是两个岛屿。
    //第3天，所有点都在水面下，没有岛屿。
    //第4天和第1天的情况一样。
    //数据范围：
    //点的个数和天数都是[1..3000]
    //A,B中每个元素的个数都是 [0..10^6]。
    //要求时间复杂度和空间复杂度都是 O(N+M+max(A)+max(B))。

    //话说这个题其实不难。我们假设水平面一点一点升高。从0升高到一个高度A[x]时，A[x]恰好没入水面，我们根据A[x-1] A[x+1]的状态 来判断段数时增加1、不变、还是减少1。原因是：
    //（1） 如果A[x-1], A[x+1]已经在水面下，A[x]把它们连接起来，则段数减少1。
    //（2） 如果A[x-1],A[x + 1]都在水面上，A[x]把它们分成两段，段数增加1。
    //（3） 如果A[x-1],A[x + 1]一个在水面上一个在水面下，则段数不变。
    //所以我们只要沿着水面升高就可以了。开始先用类似计数排序的方法，把A和B都排序就好了。
    public class Nitrogenium{
        vector<int> solution(vector<int> &A, vector<int> &B) {  
            // write your code in C++98  
            int n = A.size(), m = B.size(), ta = 0, tb = 0;  
            for (int i = 0; i < n; ++i) {  
                ta = max(ta, A[i]);  
            }  
            vector<vector<int> > save;  
            save.resize(ta + 1);  
            for (int i = 0; i < n; ++i) {  
                save[A[i]].push_back(i + 1);  
            }  
            for (int i = 0; i < m; ++i) {  
                tb = max(tb, B[i]);  
            }  
            vector<int> result;  
            result.resize(tb + 1,0);  
            vector<bool> mark;  
            mark.resize(n + 2,false);  
            mark[0] = mark[n + 1] = true;  
            int now = 1;  
            for (int i = 0; (i <= tb) && (i <＝ ta); ++i) {  
                for (int j = 0; j < save[i].size(); ++j) {  
                    int x = save[i][j];  
                    now += 1 - (mark[x - 1]?1:0) - (mark[x + 1]?1:0);  
                    mark[x] = true;  
                    
                }  
                result[i] = now;  
            }  
            vector<int> answer;  
            answer.resize(m);  
            for (int i = 0; i < m; ++i) {  
                answer[i] = result[B[i]];  
            }  
            return answer;  
                
        }  
    }
}