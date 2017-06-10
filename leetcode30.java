public class leetcode30{
    //题目有个背景，但是本质如下，一个数组有N个数，首先先要找到所谓的局部最大值，也就是说，
    //如果数组a下标范围是[0..N-1]，局部最大值的下标x在[1..N - 2]内，并且a[x] > a[x - 1], a[x] > a[x + 1]。
    //先要找到这样的下标index，假设我们形成这样一个index数组，然后求一个K值，从index数组找出K个数，
    //任意两个数的差距都要>=K，求这个最大的K值。需要注意的是第二步的index数组只是数组a的下标，
    //跟a数组原始的数无关。
    //数据范围 N [1..10^5]，数组中数的范围[1..10^9], 要求时间复杂度O(N)，空间复杂度O(N)。
    //首先我们找到index数组，按照定义找就行，时间复杂度O(N)，对于K，一个很显然的想法就是2分K，
    //注意K是O(sqrt(N))的，但是二分K，O(logK) = O(logN)，判断K是否可行，我们可以扫一遍index数组，
    //（注意index数组自然有序），常规方法就是贪心，每次选距离上一个恰好大于等于K的点，
    //可是这样复杂度会变为O(NlogN)。其实对于index数组可以我们同样可以二分，
    //每次而分出距离上一个点大于等于K的最小位置。对于每个二分出来的值x，我们2分x次，
    //每次复杂度可以认为O(logN)，于是总共复杂度是O(x * logN), 而X = O(sqrt(N))，
    //外面还有二分K的复杂度，总复杂度是O(log N * log N * sqrt(N)) 这个复杂度在O(N)内。
    public class Boron{
        int find(vector<int> &a,int from, int d) {  
            //find the first index x  a[x] - a[from] >= d  
            int left = from + 1, right = a.size() - 1;  
            while (left <= right) {  
                int mid = (left + right) >> 1;  
                if (a[mid] - a[from] >= d) {  
                    right = mid - 1;  
                }  
                else {  
                    left = mid + 1;  
                }  
            }  
            return right + 1;  
        }  
                
        int solution(vector<int> &A) {  
            // write your code in C++98  
            int n = A.size();  
            vector<int> a;  
            for (int i = 1; i < n -1; ++i) {  
                if ((A[i] > A[i - 1]) && (A[i] > A[i + 1])) {  
                    a.push_back(i);  
                }  
            }  
            if (a.size() < 2) {  
                return a.size();  
            }              
            // K * (K - 1) <= |a.back() - a.front()|  
        // (K - 1) * (K - 1) <= K * (K - 1) <= |a.back() - a.front()|  
            int left = 2, right = sqrt(a.back() - a.front()) + 1;  
            while (left <= right) {  
                int mid = (left + right) >> 1,i,last;  
                for (i = 1,last = 0; i < mid; ++i) {  
                    last = find(a, last, mid);  
                    if (last >= a.size()) {  
                        break;  
                    }  
                }  
                if (i >= mid) {  
                    left = mid + 1;  
                }  
                else {  
                    right = mid - 1;  
                }  
            }  
            return left - 1;  
        }  
    }

}