public class leetcode33{
    //问题描述很简单，给定一个整整数组A,问A有多少个段（下标对）i<=j， 
    //满足max(A[i..j]) - min(A[i..j]) <= K。 如果结果超过10^9，就返回10^9。
    //数据范围 ：
    //数组元素个数N [1..10^6]
    //K [0..10^9]
    //数组元素值范围: [-10^9,+10^9]

    //要求：时间复杂度和空间复杂度都是O(n)。
    //分析：注意如果(i,j)满足条件，那么(i+1,j) (i+2,j)....都满足条件，
    //所以满足条件的对子随着i的递增，j可以不减小…… 于是就是单调队列，维护i..j的最大值和最小值。
    //单调队列应用到极致了。（仔细想了下，对每个i，最后那个j可能在下一次的时候重复入一次队，
    //不过没关系，不影响复杂度和结果。）

    public class Oxygenium{
        int solution(int K, vector<int> &A) {  
            // write your code in C++98  
            deque<int> qmin,qmax;  
            int answer = 0;  
            for (int i = 0, j = 0; i < A.size(); ++i) {  
                while (j < A.size()) {  
                    while ((!qmin.empty()) && (A[qmin.back()] >= A[j])) {  
                        qmin.pop_back();  
                    }  
                    qmin.push_back(j);  
                    while ((!qmax.empty()) && (A[qmax.back()] <= A[j])) {  
                        qmax.pop_back();  
                    }  
                    qmax.push_back(j);  
                    if (A[qmax.front()] - A[qmin.front()] <= K) {  
                        ++j;  
                    }  
                    else {  
                        break;  
                    }  
                }  
                if (qmin.front() == i) {  
                    qmin.pop_front();  
                }  
                if (qmax.front() == i) {  
                    qmax.pop_front();  
                }  
                
                answer += j - i;  
                if (answer >=  1000000000) {  
                    return 1000000000;  
                }  
            }  
            return answer;  
            
        }  
    }
}