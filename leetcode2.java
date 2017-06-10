public class leetcode2{

	//(1) Perm-Check
	//给定整数数组有N个数，问它是不是1-N的一个排列，也就是说是否每个数都是1-N,并且只出现一次。输出1和0表示是与否，
	//输入范围N [1..10^5],数组里地整数[1..10^5]，要求复杂度时间空间都是O(N)。
	//分析：空间复杂度O(N)的算法很简单，我们可以建立一个bool数组表示1-N，每个数是否出现过
	public class Perm-Check{
		public int solution1(vector<int> &A) {  
		    // write your code here...  
		    vector<bool> have;  
		    int i,n = A.size();  
		    have.resize(n, false);  
		    for (i = 0; i < n; ++i) {  
		        if ((--A[i] >= n) || (have[A[i]])) {  
		            return 0;  
		        }  
		        have[A[i]] = true;  
		    }  
		    return 1;  
		          
		} 
		//事实上，我们有空间复杂度为O(1)的算法。利用练习(1)里Perm-Missing-Elem的方法，把A[i]换到A[A[i] - 1]的位置上，代码如下：
		public int solution2(vector<int> &A) {  
		    // write your code here...  
		    int n = A.size(),i,x,t;  
		    for (i = 0; i < n; ++i) {  
		        for (x = A[i]; (x <= n) && (A[x - 1] != x); ) {  
		            t = A[x - 1];  
		            A[x - 1] = x;  
		            x = t;  
		        }  
		        if (x > n) {  
		            return 0;  
		        }  
		    }  
		    for (i = 0; i < n; ++i) {  
		        if (A[i] != i + 1) {  
		           return 0;  
		        }  
		    }  
		    return 1;  
		         
		}   
		//我们还可以把它做得更美观一点，假设我们试图让A[0..i]分别是1..i + 1,
		//对于当前的A[i] != i + 1，从A[0..i - 1]已经是1..i了，
		//我们考虑如果A[i] < i + 1，那说明重复了，还有A[i] > n也不行，
		//因为所有数只能1..n，另外就是看一下A[A[i] - 1]的位置上的数和A[i]是否重复，
		//重复也不行，不重复就交换一下直到A[i]满足要求为止
		public int solution3(vector<int> &A) {  
    		// write your code here...  
    		int n = A.size(),i,t;  
		    for (i = 0; i < n; ++i) {  
		        while (A[i] != i + 1) {  
		            if ((A[i] > n) || (A[i] < i + 1) || (A[A[i] - 1] == A[i])) {  
		                return 0;  
		            }  
		            t = A[i];  
		            A[i] = A[A[i] - 1];  
		            A[t - 1] = t;  
		        }  
		    }  
		    return 1;  
		}  

		//这里面虽然有个while循环，但是事实上是O(n)的，因为每次交换至少把一个数换到了它应该在的位置。我们还可以不用这个while循环，思路一致，我们可以试图修改循环变量i的值……代码如下：
		public int solution4(vector<int> &A) {  
		    // write your code here...  
		    int n = A.size(),i,t;  
		    for (i = 0; i < n;) {  
		        if (A[i] == i + 1) {  
		            ++i;  
		        }  
		        else {  
		            if ((A[i] > n) || (A[i] < i + 1) || (A[A[i] - 1] == A[i])) {  
		                return 0;  
		            }  
		            t = A[i];  
		            A[i] = A[A[i] - 1];  
		            A[t - 1] = t;  
		        }  
		    }  
		    return 1;  
		}  

	}

	//(2) Frog-River-One
	//背景很有意思，实质是问一个长度为N的整数数组是否包含了从1-X的全部整数。
	//N和X都是[1..10^5]，并且数组中的数都是1..X。如果数组A[0..r]包含了1..X的全部数，
	//返回r，否则返回-1。要求复杂度时间O(N),空间O(X)。
	//这个题其实和前一个题差不多，空间方面我们同样可以建立一个长度为X的bool数组表示出现没出现过。
	//其实我们还是有空间为O(1)的做法。首先如果解存在我们至少要有X项，我们把数组的前X项，
	//利用前面所讲的交换，交换到应该放的位置，然后看一下从X..N - 1项那些值是否在0..X -1的位置需要，
	//如果需要就要放过去，并且更新r，代码有点小麻烦，
	public class Frog-River-One{
		public int solution(int X, vector<int> &A) {  
		    // write your code here...  
		    int i, r,t,n = A.size();  
		    if (X > n) {  
		        return -1;  
		    }  
		    for (i = 0; i < X;) {  
		        if ((A[i] > X) || (A[A[i] - 1] == A[i])) {  
		            ++i;  
		        }  
		        else {  
		            t = A[i];  
		            A[i] = A[A[i] - 1];  
		            A[t - 1] = t;  
		            /*if (t < i + 1) { 
		                ++i; 
		            }*/  
		        }  
		    }  
		    r = X - 1;  
		    for (i = X; i < n; ++i) {  
		        if ((A[i] <= X) && (A[A[i] - 1] != A[i])) {  
		            A[A[i] - 1] = A[i];  
		            r = i;  
		        }  
		    }  
		    for (i = 0; i < X; ++i) {  
		        if (A[i] != i + 1) {  
		            return -1;  
		        }  
		    }  
		    return r;  
	 	}  

	}
	//(3) Max-Counters
	//一个数组，长度为N，起初全部数都为0，有两种操作，一个是对某个元素加1，
	//另一个是把所有元素变为目前的最大值，给定若干次这样的操作之后，求数组最终状态。
	//数组长度N和操作个数M都在[1..10^5]。要求复杂度时间O(N + M)，空间O(N)。
	//我们对于数组中得每个操作维护一个时间戳，根据时间戳决定数组元素的值是之前的最大值，
	//还是当前的值，记录每次第二种操作之前的最大值，代码如下：
	public class Max-Counters{
		public vector<int> solution1(int N, vector<int> &A) {  
		    // write your code here...  
		    int i,m = A.size(), lastv = 0, lastt = -1, v = 0;  
		    vector<int> r, t;  
		    r.resize(N , 0);  
		    t.resize(N, -1);  
		    for (i = 0; i < m; ++i) {  
		        if (--A[i] < N) {  
		            v = max(r[A[i]] = ((t[A[i]] > lastt)?r[A[i]]:lastv) + 1, v);  
		            t[A[i]] = i;  
		        }  
		        else {  
		            lastv = v;  
		            lastt = i;  
		        }  
		    }  
		    for (i = 0; i < N; ++i) {  
		        if (lastt > t[i]) {  
		            r[i] = lastv;  
		        }  
		    }  
		    return r;  
		              
		}  
		//其实时间戳可以省略，代码如下：
		public vector<int> solution2(int N, vector<int> &A) {  
		    // write your code here...  
		    int i,m = A.size(), lastv = 0, v = 0;  
		    vector<int> r;  
		    r.resize(N , 0);  
		    for (i = 0; i < m; ++i) {  
		        if (--A[i] < N) {  
		            v = max(v, r[A[i]] = max(r[A[i]], lastv) + 1);  
		        }  
		        else {  
		            lastv = v;  
		        }  
		    }  
		    for (i = 0; i < N; ++i) {  
		        r[i] = max(r[i], lastv);  
		    }  
		    return r;  
		              
		}  
	}
	//
}