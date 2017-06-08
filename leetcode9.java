public class leetcode9{
	//（1） CountSemiprimes
	//半质数的定义是恰好两个质数（可以相同）乘积的数，
	//例如 4, 6, 9, 10, 14, 15, 21, 22, 25, 26，都是半质数。给定N，
	//长度为M的等长整数数组P和Q，满足1 ≤ P[K] ≤ Q[K] ≤ N， 求每个区间[P[k], Q[k]]之间有多少个半质数。
	//函数头部：vector<int> solution(int N, vector<int> &P, vector<int> &Q);
	//数据范围： N［1..50000] 数组长度M [1..30000]。
	//要求时间复杂度 O(NlogN + M)， 空间复杂度O(N + M)。


	//分析： 我们可以用筛法筛出质数。 简单筛质数筛法的复杂度为N / 2 + N /3 + N / 5 + .... + N / 
	//(不超过N的最大质数） 就是n除以质数的和，由于调和级数的渐近复杂度，知道筛法的时间复杂度是O(NlogN)，
	//空间复杂度为O(N)。 筛出质数后，我们同样利用筛法的思想，对每个不超过sqrt(N)的质数，
	//算出判断它的每个倍数是不是半质数……当然这里可以优化，可以用两个质数乘积求出所有半质数。 
	//这部分复杂度可以用O(NlogN)界定。 
	//接下来，我们利用前缀和思想，打出一张O(N)的表tab，表示0..i中有多少个半质数。
	//这个时间复杂度是O(N)，然后对于P[i], Q[i]。我们直接用tab[Q[i]] - tab[P[i] - 1]得到结果，
	//M个pair的时间复杂度为O(M)。
	public class CountSemiprimes{
		vector<int> solution(int N, vector<int> &P, vector<int> &Q) {  
		    // write your code in C++98  
		    vector<bool> mark;  
		    mark.resize(N + 1, true);  
		    mark[0] = mark[1] = false;  
		    for (int i = 2; i <= N; ++i) {  
		        if (mark[i] && (N / i >= i)) {  
		            for (int j = i * i; j <= N; j += i) {  
		                mark[j] = false;  
		            }  
		        }  
		    }  
		    vector<bool> is;  
		    is.resize(N + 1, false);  
		    for (int i = 2; N / i >= i; ++i) {  
		        if (mark[i]) {  
		            for (int j = i * i; j <= N; j += i) {  
		                if (mark[j / i]) {  
		                    is[j] = true;  
		                }  
		            }  
		        }  
		    }  
		    vector<int> num;  
		    num.resize(N + 1);  
		    num[0] = 0;  
		    for (int i = 1; i <= N; ++i) {  
		        num[i] = num[i - 1] + (is[i]?1:0);  
		    }  
		    vector<int> answer;  
		    for (int i = 0; i < P.size(); ++i) {  
		        answer.push_back(num[Q[i]] - num[P[i] - 1]);  
		    }  
		    return answer;  
		      
		      
		      
		}  
	}
	//(2) CountNonDivisible
	//给定一个整数数组，求每个元素在数组中非约数的个数。
	//例如：
 	//A[0] = 3
 	//A[1] = 1
 	//A[2] = 2
 	//A[3] = 3
 	//A[4] = 6


	//A[0] = 3在数组中的非约数有2,6
	//A[1] = 1在数组中的非约数有3,2,3,6
	//A[2] = 2在数组中的非约数有3,3,6
	//A[3] = 3在数组中的非约数有2,6
	//A[4] = 6在数组中的没有非约数


	//因此返回数组[2,4,3,2,6]。


	//函数头部： vector<int> solution(vector<int> &A);
	//数据范围： 数组长度N [1..50000]
	//数组元素范围： [1..2 * N]
	//要求时间复杂度 O(NlogN) 空间复杂度O(N)


	//这题的关键在于数据范围和数组长度的数量级相同，都是O(N)。
	//我们可以用2 * N的空间统计数组A中每个数出现多少次。
	//我们在用一张表tab[1..2 * N]，tab[i]记录数组A中出现的i的非约数有几个。
	//起初我们认为tab[i] =  N，即A中所有的数都不是i的约数。然后对于每个A中出现的数，
	//类似筛法，我们求它所有的倍数j,从tab[j]中减掉i在A中出现的次数，因为i是j的约数。
	//时间复杂度主要在筛法那里 是O(2Nlog(2N)) = O(NlogN)。空间复杂度主要是两张表，
	//一张统计数在A出现多少次，一张是tab，因为数据范围是2 * N，所以这个空间还是O(N)的。

	public class CountNonDivisible{
		public vector<int> solution(vector<int> &A) {  
		    // write your code in C++98  
		    int n = A.size(), m = (n << 1) | 1;  
		    vector<int> num;  
		    num.resize(m + 1, 0);  
		    for (int i = 0; i < n; ++i) {  
		        ++num[A[i]];  
		    }  
		    vector<int> answer;  
		    answer.resize(m + 1, n);  
		    for (int i = 1; i <= m; ++i) {  
		        if (num[i]) {  
		            for (int j = i; j <= m; j += i) {  
		                answer[j] -= num[i];  
		            }  
		        }  
		    }  
		    vector<int> r;  
		    for (int i = 0; i < n; ++i) {  
		        r.push_back(answer[A[i]]);  
		    }  
		    return r;  
		      
		}  
	}
}