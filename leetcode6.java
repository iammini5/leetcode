public class leetcode6{
	// （1） Dominator
	//就是找数组中出现次数大于一半的数。数据范围 数的个数 N [0..10^6],  
	//数组里地整数范围[-2147483648, +2147483647]，要求复杂度时间O(N)，空间O(1)。
	//经典找众数的方法，区别在于众数不一定存在，所以还得检查一下找到的那个数是不是真正的出现次数大于一半。
	//返回的是众数的一个下标，或者-1。
	public class Dominator{
		public int solution(const vector<int> &A) {  
		    // write your code in C++98  
		    if (A.empty()) {  
		        return -1;  
		    }  
		    int answer = 0, count = 1;  
		    for (int i = 1; i < A.size(); ++i) {  
		        if (A[i] == A[answer]) {  
		                ++count;  
		        }  
		        else if (--count == 0) {  
		            answer = i;  
		            count = 1;  
		        }  
		    }  
		    count = 0;  
		    for (int i = 0; i < A.size(); ++i) {  
		        if (A[i] == A[answer]) {  
		            ++count;  
		        }  
		    }  
		    return ((count << 1) > A.size())?answer:(-1);  
		              
		}  
	}

	//（2）Equi-leader
	//和众数差不多，对数组[0..N - 1]，如果存在S, 0 <= S < N - 1，
	//满足子数组A[0..S]与子数组A[S + 1..N -1]的众数一样，
	//那么S叫做一个equi-leader。求一个数组有多少个equi-leader。
	//首先，就是equi-leader决定的众数肯定都相同，因为它的必要条件是它在整个数组中
	//出现的次数大于一半（因为在两部分出现都大于一半），因此这个数是唯一的。
	//所以我们可以先找众数，并统计众数出现了多少次。然后枚举S，同时记录众数在A[0..S]中出现了多少次，
	//也就知道了它在A[S + 1.. N - 1]中出现了多少次……
	public class Equi-leader{
		public int solution(vector<int> &A) {  
		    // write your code in C++98  
		    int ind = 0, count = 1;  
		    for (int i = 1; i < A.size(); ++i) {  
		        if (A[ind] == A[i]) {  
		            ++count;  
		        }  
		        else if (--count == 0) {  
		            ind = i;  
		            count = 1;  
		        }  
		    }  
		    count = 0;  
		    for (int i = 0; i < A.size(); ++i) {  
		        if (A[ind] == A[i]) {  
		            ++count;  
		        }  
		    }  
		    if ((count << 1) <= A.size()) {  
		        return 0;  
		    }  
		    int num = 0,answer = 0;  
		    for (int i = 0; i < A.size() - 1; ++i) {  
		        if (A[ind] == A[i]) {  
		            ++num;  
		            --count;  
		        }  
		          
		        if (((num << 1) > i + 1) && ((count << 1) > A.size() - i - 1)) {  
		            ++answer;  
		        }  
		    }  
		    return answer;  
		}  
	}
	
}