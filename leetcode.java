

public class leetcode{

	public leetcode(){};

	// Frog-Jmp:
	//一只青蛙，要从X跳到Y或者大于等于Y的地方，每次跳的距离为D，问至少跳几次。 X,Y,D都是[1..10^9]的整数。
	// #include <algorithm>  
	public class Frog-Jmp{
		
		public int solution(int X, int Y, int D) {  
	    	// write your code here...  
	    	return (X >= Y)?0:((Y - X + D - 1) / D);  
		}  
	}

	//Perm-Missing-Elem:
	//一个长度为N的数组里，都是整数，所有的数都不相同，范围是[1..N + 1]，
	//这意味着有一个整数缺失了，找到这个缺失的整数，N的范围[0..10^5], 要求时间复杂度O(N)，空间复杂度O(1)。


	public class Perm-Missing-Elem{

		//求和，注意使用long long。
		public int solution1(vector<int> &A) {  
	    // write your code here...  
		int n = A.size(), i;  
		long long r = n + 1;  
		    for (i = 0; i < n; ++i) {  
		        r += (i + 1) - A[i];  
		    }  
		    return r;  
		} 
		// 求异或，不需要long long
 
		public int solution2(vector<int> &A) {  
		    // write your code here...  
			int n = A.size(), i, r = n + 1;  
			    for (i = 0; i < n; ++i) {  
			        r ^= (i + 1) ^ A[i];  
			    }  
			    return r;  
		}  

        //3 把A[i]换到下标为A[i] - 1的位置，注意(N + 1)单独拿出来
		public int solution3(vector<int> &A) {  
    	// write your code here...  
		    int n = A.size(),i,x,t;  
		    for (i = 0; i < n; ++i) {  
		        for (x = A[i]; (x <= n) && (A[x - 1] != x); ) {  
		            t = A[x - 1];  
		            A[x - 1] = x;  
		            x = t;  
		        }  
		    }  
		    for (i = 0; i < n; ++i) {  
		        if (A[i] != i + 1) {  
		            break;  
		        }  
		    }  
		    return i + 1;  
		         
		}  

	}
	
	//Tape-Equilibrium:
	//空数组长度为N，定义对0 < P < N, |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
	//为P分割的两部分的差值，
	//求最小的差值。 N在[2..10^5]，数组中数据范围[-1000,+1000]。要求复杂度时间O(N)，空间O(N)。
	//分析：这个题可以做到空间O(1)，我们主需要记录前P项和和总合就可以了。

	public class Tape-Equilibrium{

		public int solution(vector<int> &A) {  
		    // write your code here...  
		    int n = A.size(), sum, r, i, t;  
		    for (i = sum = 0; i < n; ++i) {  
		        sum += A[i];  
		    }  
		    r = abs(sum - A[0] - A[0]);  
		    for (i = 2, t = A[0]; i < n; ++i) {  
		        t += A[i - 1];  
		        r = min(r, abs(sum - t - t));  
		    }  
		    return r;  
		}  
	}

	//Further Training:
	//Abs-distinct:
	//一个长度为N的整数数组，已经按非降序排好序，求数组中的数有多少种不同的绝对值。数据范围N [1..10^5]，
	//数组元素[-2147483648,+2147483647]。
	//要求复杂度 时间O(N)，空间O(N)。
	//分析：这个题可以做到空间复杂度O(1)，因为数组是排好序的，我们可以两头扫。
	//但是由于数据范围-2147483648,我们不能随便取绝对值，因为会整数越界，这里要小心谨慎，不然过不了某一组数据。
	
	public class Further-Training{
		public int solution(const vector<int> &A) {  
    	// write your code here...  
				int n = A.size(), i, j ,r, t;  
			    for (i = r = 0, j = n - 1; (i < j) && (A[i] < 0) && (A[j] > 0); ++r) {  
			        t = (A[i] + A[j] < 0)?A[i]:A[j];  
			        for (;((t < 0) && (A[i] == t)) || ((t > 0) && (A[i] + t == 0)) ; ++i);  
			        for (;((t < 0) && (A[j] + t == 0)) || ((t > 0) && (A[j] == t)); --j);  
			    }  
			    for (;i <= j;++r) {  
			        for (t = A[i]; (i <= j) && (A[i] == t); ++i)  
			        ;  
			    }  
			    return r;  
		}  
	}

	//Binary-gap:
	//一个整数的2进制表示中，两个1bit之间全部是0, 0的个数叫做一个binary-gap，给定Ｎ，求其最长binary-gap。
	//（两个1之间最大的连续0的个数）。
	//N [1..2147483647]。要求复杂度时间O(logN)，空间O(1)。
	//分析：右移（防止越界），我们记录上一个1bit的位置就可以了。
	public class Binary-gap{
		public int solution(int N) {  
		    // write your code here...  
		    int i,r, last;  
		    for (i = r = 0, last = -1; N; N >>= 1,++i) {  
		        if (N & 1) {  
		            if (last >= 0) {  
		                r = max(i - last - 1, r);  
		            }  
		            last = i;  
		        }  
		    }  
    		return r;  
		}  

	}

	//找数组平衡点问题：
	//给定一个整数数组P，A[0..N - 1] ,平衡点定义为整数P,  满足
	//A[0] + A[1] +...+A[P - 1] = A[P + 1] + A[P + 2] + ... + A[N - 1]
	//注意不包含P元素。
	//输出任何一个平衡点，如果没有，则输出-1。
	//如果P == 0 等号左边是0，P == N - 1等号右边是0.
	//N是10^7的范围，每个数组元素是-2147483648..2147483647
	//要求复杂度: 时间复杂度O(N),空间复杂度O(1)
	public class Equi{
		public int solution(const vector<int> &A) {  
		    // write your code here...  
		    int n = A.size(),i;  
		    long long sum = 0, left = 0;  
		    for (i = 0; i < n; ++i) {  
		        sum += A[i];  
		    }  
		    for (i = 0; i < n; ++i) {  
		        if (left == sum - A[i] - left) {  
		            return i;  
		        }  
		        left += A[i];  
		    }  
		        
		    return -1     
		}  
	}

	//Dominator:
	//找众数，即数组中出现次数超过一半的数。数组长度N [0..10^6] (有0），
	//每个数组元素，整数[-2147483648, +2147483647]。要求时间复杂度O(N)，空间复杂度O(1)。
	//返回的是众数的任意一个下标。没有众数返回-1。
	//分析： 经典算法，假设众数是x，扫一遍，相同的话加一次，不同的话减一次，次数变为负数就换掉x。
	//最后还要检查一遍x是否真的为众数。
	//代码：
	public class Dominator{
		public int solution(const vector<int> &A) {  
		    // write your code here...  
		    int r, i , t, n = A.size();  
		    for (i = r = t = 0; i < n; ++i) {  
		        if (A[i] == A[r]) {  
		            ++t;  
		        }  
		        else if (--t < 0) {  
		            t = 1;  
		            r = i;  
		        }  
		    }  
		    for (i = n - 1, n >>= 1; i >= 0; --i) {  
		        if ((A[i] == A[r]) && (--n < 0)) {  
		            return r;  
		        }  
		    }  
		    return -1;  
		}  
	}

	//Max-profit:
	//给定数组是股票价钱，要在早时买，晚些时候卖（可以是同一天），求最大收益。
	//天数N [1..10^5],每天的价格[0..10^9]。要求复杂度时间O(N)，空间O(1)。
	//分析：这个题已经写入算法导论第三版了，可以转化为最大子段和。但是还有另外的方法，
	//如果我们知道某天卖出，那么买入的时候一定是这些天最便宜的日子收益才能最大。
	public class Max-profit{
		public int solution(const vector<int> &A) {  
		    // write your code here...  
		    int n = A.size(), i, profit, cheapest;  
		    for (i = profit = cheapest = 0; i < n; ++i) {  
		        if (A[i] < A[cheapest]) {  
		            cheapest = i;  
		        }  
		        profit = max(profit, A[i] - A[cheapest]);  
		    }  
		    return profit;  
		}  
	}
	//Tree-height:
	//二叉树的高度，节点数N [1..1000]，要求复杂度时间空间都是O(N)。
	//分析：就是dfs，至于空间的话，主要看堆栈是否要计算了……代码我就写了一句话。
	public class Tree-height{
		public int solution(tree * T) {  
		    // write your code here...  
		    return T?(max(solution(T->l),solution(T->r)) + 1):(-1);  
		}  
	}

	//Array-inversion-count:
	//就是求数组逆序数，超过10^9，返回-1。 要求时间复杂度O(NlogN)，空间复杂度O(N)，没给N的范围。
	//经典分治算法，跟归并排序一样。
	public class Array-inversion-count{
		vector<int> temp;  
		public int help(vector<int> &A,int from,int to) {  
			int i,j,r,r1,r2,mid;  
		    if (to - from <= 0) {  
		        return 0;  
		    }  
		    mid = (from + to) >> 1;  
		    r1 = help(A, from, mid);  
		    if (r1 > 1000000000) {  
		        return -1;  
		    }  
		    r2 = help(A, mid + 1, to);  
		    if (r2 > 1000000000) {  
		        return -1;  
		    }  
		    if ((r  = r1 + r2) > 1000000000) {  
		        return -1;  
		    }  
		    temp.clear();  
		    for (i = from, j = mid + 1; (i <= mid) && (j <= to);) {  
		        if (A[i] <= A[j]) {  
		            temp.push_back(A[i++]);  
		        }  
		        else {  
		            if ((r += mid - i + 1) > 1000000000) {  
		                return -1;  
		            }  
		            temp.push_back(A[j++]);  
		        }  
		    }  
		    for (;i <= mid;++i) {  
		        temp.push_back(A[i]);         
		    }  
		    for (;j <= to; ++j) {  
		        temp.push_back(A[j]);  
		    }  
		    for (i = from; i <= to; ++i) {  
		        A[i] = temp[i - from];  
		    }  
		    return r;  
		}  
  
		public int solution(const vector<int> &A) {  
		    // write your code here...  
			vector<int> a = A;  
		    return help(a, 0, a.size() - 1);  
		}  
	}

	//takes a String and returns the phone number in certain format. 
	//It will return the numbers with dash ("-") in every 3 digits 
	//and at least keep 2 digits together. For example, it will return :
 	//Test: '00-44  48 5555 8361' 
	//Expected: 004-448-555-583-61

	//Test:'0 - 22 1985--324' 
	//Expected: 022-198-53-24 

	//Test: '555372654' 
	//Expected: 555-372-654
	
	public class FormatPhoneNumber{
		void solution(String s) {
			if (s == null) return null;
			return s.replaceAll("\\D", "")                // Discard all non-digits.
					.replaceAll("(\\d{2})(?=\\d{2}$)" +   // Final group of 4 digits
								"|" +                     // ... or ...
								"(\\d{3})(?!$)",          // non-final group of 3 digits,
								"$1$2-");                 // insert separator.
		}
	}
}
