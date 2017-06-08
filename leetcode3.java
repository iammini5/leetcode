public class leetcode3{

	//（1） Min-avg-slice
 	//给定一个长度为n的整数数组，找到一个连续的子数组，数组元素的平均值最小。 数据范围N [1..10^5],数组元素范围[-10^4, +10^4]。
	//要求复杂度： 时间O(N),空间O(N)。
	//分析： 就是求最小值……因为如果拉进别的数，平均值会增大，干嘛搞成这样，空间可以O(1)。说得神乎其神的……
	public class Min-avg-slice{
		public int solution(vector<int> &A) {  
			    // write your code here...  
				int i,j,n = A.size();  
				    for (i = j = 0; i < n; ++i) {  
				        if (A[i] < A[j]) {  
				            j = i;  
				        }  
				    }  
				    return A[j];  
				}  
	}

	//(2) Passing-cars
	//给定一个长度为N的0-1数组A，它表示一条路上的车流方向,下标从0开始，要找到0<=P<=Q<N，并且A[P] = 0, A[Q] = 1的下标对数。数据范围N [1..10^5]。
	//要求复杂度： 时间O(N),空间O(1)。
	//分析： 实质对于每个0，我们求它后面有多少个1即可。所以我们倒着遍历数组，对每个0，看一下截止到目前为止的后缀和即可。
	public class Passing-cars{
		public int solution(vector<int> &A) {  
		    // write your code here...  
		    int i,sum,answer;  
		    for (answer = sum = 0, i = A.size() - 1; i >= 0; --i) {  
		        if (A[i]) {  
		            ++sum;  
		        }  
		        else if ((answer += sum) > 1000000000) {  
		            return -1;  
		        }  
		    }  
		    return answer;  
		}  

	}
	//(3) Genomic-range-query
	//给定一个字符串，代表基因，只包含ACGT,4个字符，假设它们分别代表整数1，2，3，4，
	//再给定M个查询P,Q, (P[i],Q[i])表示从查询原串下标P[i]到Q[i]之间的最小值，
	//字符串长度N [1..10^5]，查询个数M [1..50000]。要求复杂度 时间O(N + M)，空间O(N)。
	//分析： 本来是线段树的题目，但是因为只有4种值，所以我们可以记录下前n项1,2,3,4分别出现了多少次，
	//这样通过减法，我们就知道查询段内每个数出现的次数，自然知道最小值了。
	public class Genomic-range-query{
		public vector<int> solution(string &S, vector<int> &P, vector<int> &Q) {  
		    // write your code here...  
		    vector<vector<int> > have;  
		    int i,j,n = S.size();  
		    have.resize(n + 1);  
		    have[0].resize(4, 0);  
		    for (i = 1; i <= n; ++i) {  
		        have[i] = have[i - 1];  
		        switch(S[i - 1]) {  
		        case 'A':  
		            ++have[i][0];  
		            break;  
		        case 'C':  
		            ++have[i][1];  
		            break;  
		        case 'G':  
		            ++have[i][2];  
		            break;  
		        case 'T':  
		            ++have[i][3];  
		            break;  
		        }  
		    }  
		    n = P.size();  
		    vector<int> answer;  
		    answer.resize(n);  
		    for (i = 0; i < n; ++i) {  
		        for (j = 0; j < 4; ++j) {  
		            if (have[Q[i] + 1][j] - have[P[i]][j]) {  
		                answer[i] = j + 1;  
		                break;  
		            }  
		        }  
		    }  
		    return answer;  
		      
		}  
	}
}



