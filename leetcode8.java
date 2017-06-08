
public class leetcode8{
	//(1) Min-Perimeter-Rectangle 
	//给定矩形面积，长和宽都是整数，求最大它的最大周长值。
 	//面积范围N：[1..1,000000000]
	//要求时间复杂度 O(sqrt(N))，时间复杂度O(1)。
	//枚举就可以了。。。
	public class Min-Perimeter-Rectangle{
		public int solution(int N) {  
		    // write your code in C++98  
		    int r = N + 1;  
		    for (int i = 2; N / i >= i; ++i) {  
		        if (N % i == 0) {  
		            r = min(r, N / i + i);  
		        }  
		    }  
		    return r << 1;  
		}  
	}

	//(2)  Peaks
	//一个数组中下标P位置，入过A[P] > A[P - 1] 并且A[P] > A[P + 1]，
	//则称P位置为一个山峰（注意：第一个和最后一个元素不是山峰），我们把数组平均分成m段，每段有n / m个元素，
	//要求每段里至少有一个山峰，问m最大是多少？如果数组没山峰返回0。 N [1..10^5]。元素范围[0..10^9]。
	//要求复杂度：时间O(nlog(log(n)))，空间O(n)。

	//分析： 个人感觉时间复杂度很诡异……，首先如果我们用O(n)得空间统计从开始到目前为止得山峰数sum[]，
	//那么我们如何检查某个k是不是一个可行解？
    //      (a) 首先 n % k == 0
    //      (b) sum[k - 1], sum[2 * k - 1], sum[3 * k - 1],....sum[n - 1] 严格递增，
    //注意这个数列有n / k项。
	//所以我们循环k有n次真正判断额外的循环次数是n / k。 于是总的时间复杂度是n + (n的所有约数和）。 
	//从wikipedia看到
	//http://en.wikipedia.org/wiki/Divisor_function

	//约束和的复杂度是O(nloglog(n)))的，所以时间复杂度就是这样了。

	//其实只要稍加改进时间复杂度可以达到O(n)。
	//考虑距离最远的连个山峰，设最大距离为D，（第一个山峰到开头的距离，以及最后一个山峰到末尾的距离都算进去），
	//则如果桶的size >= D，则一定可以。size < D / 2一定不可以。所以我们只要看D/2..D之间的n的约数即可。
	//如果这里没有解，再沿着D向上找一个约数（但是不用再验证）。之所以把第一个山峰和最后一个山峰那块都算进去
	//是因为可以保证第一个桶和最后一个桶不空。然后可以轻松得出上述结论。
	//复杂度分析，令m = D / 2。则 复杂度只有n / m + n / (m + 1) + ... + n / D，每一项不大于n / m，一共m项，
	//所以时间复杂度居然是O(n)。

	public class Peaks{
		public int solution(vector<int> &A) {  
		    // write your code in C++98  
		    // 2 * size - 1 >= D  
		    // size < D  
		    int n = A.size();  
		    if (n <= 2) {  
		        return 0;  
		    }  
		    vector<int> sum;  
		    sum.resize(n);  
		    int last = -1, D = 0;  
		    sum[0] = 0;  
		    for (int i = 1; i + 1 < n; ++i) {  
		        sum[i] = sum[i - 1];  
		        if ((A[i] > A[i - 1]) && (A[i] > A[i + 1])) {  
		            D = max(D, i - last);  
		            last = i;  
		            ++sum[i];  
		        }  
		          
		    }  
		    if ((sum[n - 1] = sum[n - 2]) == 0) {  
		        return 0;  
		    }  
		    D = max(D, n - last);  
		    for (int i = (D >> 1) + 1; i < D; ++i) {  
		        if (n % i == 0) {  
		            last = 0;  
		            int j;  
		            for (j = i; j <= n; j += i) {  
		                if (sum[j - 1] <= last) {  
		                    break;  
		                }  
		                last = sum[j - 1];  
		            }  
		            if (j > n) {  
		                return n / i;  
		            }  
		        }  
		    }  
		    for (last = D; n % last; ++last)  
		    ;  
		    return n / last;  
		              
		}  
	}
}