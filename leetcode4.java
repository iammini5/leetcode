public class leetcode4{

	//1） DIstinct
	//给定一个数组，问其中有多少个不同的元素。
	//数组元素个数范围N[0,10^5]
	//数组元素值范围 [-10^6, +10^6]
	//要求时间复杂度 O(NlogN)，空间复杂度O(N)。
	//给出的空间是复制用的，当然我们也可以放到set里，这都无所谓。
	public class DIstinct{
		public int solution(const vector<int> &A) {  
		    // write your code in C++98  
		    vector<int> a = A;  
		    int r = 0;  
		    sort(a.begin(),a.end());  
		    for (int i = 0; i < a.size(); ++i) {  
		        if ((i == 0) || (a[i] != a[i - 1])) {  
		            ++r;  
		        }  
		    }  
		    return r;  
		}  
	}

	//（2） MaxProductofThree
 	//给定一个数组，找到乘积最大的3个不同的元素。
	//数组元素个数N [3..10^5]，每个元素的范围[-1000, +1000]。
	//要求复杂度 时间O(NlogN)，空间O(1)。
	public class MaxProductofThree{
		public int solution(vector<int> &A) {  
		    // write your code here...  
		    sort(A.begin(), A.end());  
		    int n = A.size();    
		    return max(A[n - 1] * A[n - 2] * A[n - 3], A[0] * A[1] * A[n - 1]);  
		     
		 }  
	}

	//3）Triangle
 	//给定一个数组A，N个整数，问是否能组成三角形，即是否存在 0 < P < Q < R < N,同时满足
	//A[P] + A[Q] > A[R],
	//A[Q] + A[R] > A[P],
	//A[R] + A[P] > A[Q].
	//数组里地数都是整数，有整有负
	//N 范围[0..10^5], 数组中数的范围[-2147483648,+2147483647]
	//要求复杂度 时间O(NlogN),空间O(N)
	//函数头部
	//分析： 因为是const的，所以要复制一份，所以空间复杂度就是O(n)。
	//这个题很简单，首先负数和0是不可能出现的，因为两边之差要严格小于第三边，
	//考虑最大和次大的差，一定是非负的，所以最小边一定是正的。
	//考虑三个正整数 0 < x <=y <= z，那么z + x > y, z + y > x是天然满足的，
	//唯一可能不满足的就是x + y > z那么x,y越接近z,越大，就越可能总和大于z。
	//所以我们只需要比较相邻三个正数就可以了。还要注意整数越界，如果不用long long还是判断减法比较好。
	public class Triangle{
		public int solution(const vector<int> &A) {  
		    // write your code here...  
		    vector<int> a = A;  
		    sort(a.begin(), a.end());  
		    int n = a.size(), i;  
		    for (i = 0; i + 2 < n; ++i) {  
		        if ((a[i] > 0) && (a[i] > a[i + 2] - a[i + 1])) {  
		            return 1;  
		        }  
		    }  
		    return 0;  
		                  
		}  
	}

}