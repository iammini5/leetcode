public class leetcode5{
	// (1) Brackets 
	//合法括号序列，包括( [ { ) ] }这6种字符的字符串，长度N在[0..200000]范围内，为其是否合法。
	//要求时间复杂度O(N)，空间复杂度O(N)。
	//用堆栈简单判断就可以了。
	public class Brackets{
		public int solution(const string &S) {  
		    // write your code in C++ 98  
		    int n = S.size();  
		    stack<int> s;  
		    for (int i = 0; i < n; ++i) {  
		        if (S[i] == ')') {  
		            if ((s.empty()) || (S[s.top()] != '(')) {  
		                return false;  
		            }  
		            s.pop();  
		        }  
		        else if (S[i] == ']') {  
		            if ((s.empty()) || (S[s.top()] != '[')) {  
		                return false;  
		            }  
		            s.pop();  
		        }  
		        else if (S[i] == '}') {  
		            if ((s.empty()) || (S[s.top()] != '{')) {  
		                return false;  
		            }  
		            s.pop();  
		        }  
		        else {  
		            s.push(i);  
		        }  
		    }  
		    return s.empty();  		          		              		  		                 
		}  
	}

	//(2) Nesting
	//比（1）还简单一点，连堆栈都省了，因为只有左右小括号。长度[0..10^6]，要求时间复杂度O(n)，空间复杂度O(1)。

	public class Nesting{
		int solution(const string &S) {  
		    // write your code in C++98  
		    int depth = 0;  
		    for (int i = 0; i < S.length(); ++i) {  
		        if (S[i] == '(') {  
		            ++depth;  
		        }  
		        else if (--depth < 0) {  
		            return false;  
		        }  
		    }  
		    return depth == 0;  
		}  
	}

	//(3) Fishing
	//X轴上有一群鱼，每条鱼的位置不同。每条鱼游动的方向可能沿着x正方向，
	//也可能沿着x轴负方向。从左到右（沿着x轴正方向）知道每条鱼的大小和游动的方向，
	//每条鱼的大小不同，游动速度一样，两条鱼相遇，大鱼会吃掉小鱼，问时间足够长之后能剩下多少条鱼。
	//数据范围 鱼数 N [1..10^5]，每条鱼游动的方向只能是+1和-1，
	//鱼大小的范围[0..10^9]。要求复杂度 时间O(N)，空间O(N)。

	//这个题也不难因为每条鱼速度相同，所以只有反方向的鱼才可能相遇。 
	//我们从左到右把往正方向游动的鱼压入堆栈，当出现第一条负方向游动的鱼时，不断从堆栈弹出那些鱼，
	//这些鱼按顺序和这条鱼相遇，决定哪条被吃掉。直到这条负方向的鱼被吃掉或者堆栈为空。

	public class Fishing{
		int solution(vector<int> &A, vector<int> &B) {  
		    // write your code in C++ 98  
		    int n = A.size(),m = n;  
		    stack<int> s;  
		    for (int i = 0; i < n; ++i) {  
		        if (B[i]) {  
		            s.push(A[i]);  
		        }  
		        else {  
		            while ((!s.empty()) && (s.top() < A[i])) {  
		                s.pop();  
		                --m;  
		            }  
		            if (!s.empty()) {  
		                --m;  
		            }  
		        }  
		    }  
		    return m;  		                     
		}  
	}


}