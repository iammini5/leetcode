public class leetcode23{
    //这个题也比较有意思。意思是给定一个数组A，长度为M，里面都是正整数，代表每块地形的高度。
    //现在要测试一种加农炮，给定一个炮弹的高度H， 如果存在最小的I，满足0 < I <  M，满足A[I] >= H，
    //则炮弹会被挡住，于是A[I - 1]的高度会增加1。如果H <= A[0]，则这个炮弹无效，如果H > 所有的A[I]，
    //这个炮弹也无效。现在再给定N个整数的数组B代表炮弹高度，计算出最后地形的样子。
    //数据范围： M和N的范围[0..30000] A和B中元素的高度[0..10^6]。
    //要求复杂度： 时间 O(H + M + N)，空间 O(H + M)。H是炮弹的最大高度。
    //分析： 我们要算出一个炮弹高度x下被拦截住的地形下标I = hit[x]，
    //我们目前知道如果i > j并且b[i] > b[j],  则hit[b[i]] >= hit[b[j]]。
    //仔细研究下hit数组，其实hit中得数是连续的，相同的数在一起。
    //如果一个炮弹撞到了A[I]，当A[I - 1]增加1之后的高度，原来越过它的可能会撞到它，
    //其实那个hit数组每次最多只有一个元素会改变，因为高度只是每次加1.这个hit数组值得仔细研究，
    //A中能撞的下标I，一定是[1..I]之间的最高高度，才有可能。
    public class Chi2012{
        vector<int> solution(const vector<int> &A, const vector<int> &B) {  
    // write your code here...  
        int i, j,h, m = A.size(), n = B.size();  
                for (i = h = 0; i < n; ++i) {  
                    h = max(h, B[i]);  
                }  
                vector<int> hit;  
                hit.resize(h + 1);  
                for (i = j = 0; j <= h; ++j) {  
                    for (; (i < m) && (A[i] < j); ++i)  
                    ;  
                    hit[j] = i;  
                }  
                vector<int> a = A;  
                for (i = 0; i < n; ++i) {  
                    j = hit[B[i]];  
                    if ((j > 0) && (j < m)) {  
                        if (hit[++a[j - 1]] >= j) {  
                            hit[a[j - 1]] = j - 1;  
                        }  
                    }  
                }  
                return a;  
        }  
    }
}