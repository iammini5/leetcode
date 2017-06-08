public class leetcode7{
	//（1） Max-slice-sum
	//最大子段和  元素个数n [1..10^5]，数组元素个数[-10^6,+10^6]，保证最终结果32bit整数内。
	//要求时间复杂度O(n)，空间复杂度O(1)。
	public class Max-slice-sum{
		public int solution(const vector<int> &A) {  
		    // write your code in C++98  
		    int n = A.size(), end = A[0], result = end;  
		    for (int i = 1; i < n; ++i) {  
		        if (end < 0) {  
		            end = 0;  
		        }  
		        result = max(result, end += A[i]);  
		    }  
		    return result;  
		}  
	}
	//(2)  Max-profit
	//知道每天的股价，一天买一天卖，求最大利润，天数n [0..10^6]，价格[0..10^9]。
	//要求复杂度 时间O(n)，空间O(1)。
	//这个题已经是old了，亚马逊去年面试面过，算法导论第三版也写过，可以用价格差转化为问题（1），
	//不过我还是喜欢我自己的写法。
	//解：
	public class Max-profit{
		public int solution(const vector<int> &A) {  
		    // write your code in C++98  
		    int profit = 0,ind = 0, n = A.size();  
		    for (int i = 1; i < n; ++i) {  
		        if (A[i] < A[ind]) {  
		            ind = i;  
		        }  
		        profit = max(profit, A[i] - A[ind]);  
		    }  
		    return profit;  
		}  
	}
	//（3） Max-double-slice-sum
	//对数组A，给定三元组（X,Y,Z)，0<=X<Y<Z<N，A[X + 1] + ...+ A[Y - 1] + A[Y + 1] +..+A[Z - 1]，
	//叫做double-slice。求最大的double-slice。
	//数组元素范伟N [3..10^5]，数组元素[-10^4,+10^4]。要求复杂度：时间O(N)，空间O(N)。
	//解：
	public class Max-double-slice-sum{
		public int solution(vector<int> &A) {  
		    // write your code in C++98  
		    int n = A.size(), temp = 0;  
		    vector<int> b;  
		    b.resize(n);  
		    for (int i = 1; i < n; ++i) {  
		        if (temp < 0) {  
		            temp = 0;  
		        }  
		        b[i] = temp;  
		        temp += A[i];  
		         
		    }  
		    temp = 0;  
		    int result = 0;  
		    for (int i = n - 2; i ; --i) {  
		        if (temp < 0) {  
		            temp = 0;  
		        }  
		        result = max(result, temp + b[i]);  
		        temp += A[i];  
		    }  
		    return result;       		         
		}  
	}
}
	