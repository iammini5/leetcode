public class leetcode21{
    //问题描述是：给定一个整数数组A，所有的数均不相同。假设下标从0开始，找到一个数组B, 
    //满足A[B[0]] > A[B[1]] > A[B[2]] >...A[B[K]],对任意两项A[B[i]]和A[B[i + 1]]，
    //任意j，  min(B[i],B[i + 1]) < j < max(B[i],B[i + 1]) 均有A[j] < A[B[i + 1]] ，求最大的K。
    //例如，对数组 Ａ，　
    //A[0]  =  9   A[1]  = 10   A[2]  =  2
    //A[3]  = -1   A[4]  =  3   A[5]  = -5
    //A[6]  =  0   A[7]  = -3   A[8]  =  1
    //A[9]  = 12   A[10] =  5   A[11] =  8
    //A[12] = -2   A[13] =  6   A[14] =  4

    //求得的数组B为 9，1，4，8，6，7,长度为6
    //A[9] = 12    A[1] = 10    A[4] =  3
    //A[8] =  1    A[6] =  0    A[7] = -3

    //输入数组A的长度n [1..10^5]，数组元素范围[-10^9,+10^9]，都是整数且不相同。
    //要求复杂度 时间空间都是O(n)。


    //分析： 这个题乍一看没思路。需要转换：我们把A中得数建成一个类似”堆“的结构。
    //对A数组，这个二叉树的根是最大值，然后我们把最大值两边的部分分别建立成这样的二叉树（每段递归的找最大值）。
    //我们暂时抛开建树的复杂度，如果有了这样的树，我们所以的B数组长度对应为从树根到叶子的叶子的一条路径长度。
    //（因为每个子树的根都是最大值）。那么如何建立这样的树呢？ 暴力递归的方法会导致O(n^2)的复杂度。
    //如果我们从做到右扫描A数组，假设某个值是当前最大值，它的左子树所有的元素都确定了，
    //再出现的数，要么放在它的右子树，要么它成为别人的左子树。对于任意一个元素来讲，
    //要么它成为之前元素的右子树，要么之前某个元素成为它的左子树，这取决于大小关系。
    //我们在算一个元素的右子树的时候，因为元素还没扫描完，所以我们很难确定什么时候把它作为某个元素的右子树位置。 
    //我们可以保存这样一个栈，栈的每个元素是一棵树，树根的左子树已经完全确定，根的右子树暂时是空。
    //当弹出一棵树的时候，我们需要把它下面那个元素的子树合并。
    //也就是说，栈考顶得元素是其下面元素的右子树，但是右子树会变化。
    //最初的树
    //堆栈里的树
    //12新来后合并情况：
    //大概过程如下：
    //如果当前元素比栈顶元素大，就把栈顶元素弹出，新弹出的元素作为之前弹出元素的右子树，都弹出之后形成的树，
    //作为当前元素的左子树（右子树为空），形成的树放入栈。因为弹出来的东西都比当前元素小，
    //并且在当前元素的左边。另外，我们不会真得合并树，只记录树的高度即可（根到叶子的节点个数）。
    public class upsilon2012{
        int solution(const vector<int> &A) {  
            // write your code here...  
            int i, height, n = A.size();  
            vector<pair<int,int> > s;  
            for (i = 0; i < n; ++i) {  
                for (height = 0; (!s.empty()) && (s.back().first < A[i]);s.pop_back()) {  
                    height = max(height + 1, s.back().second);  
                }  
                s.push_back(make_pair(A[i], height + 1));  
            }  
            for (height = 0; !s.empty();s.pop_back()) {  
                height = max(height + 1, s.back().second);  
            }  
            return height;  
        }  
    }
}