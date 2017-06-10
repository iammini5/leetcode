public class leetcode10{
    //（1）ChocolatesByNumbers
    //N块巧克力，从0到N - 1编号，排成一个圈。从0号开始吃，
    //如果上一次吃了x号，这一次吃(x + M) % N号，
    //如果该号码已经存在，则停止。问结束前，吃了多少块巧克力？
    //数据范围M ,N [1..10^9]
    //要求复杂度 时间O(log(M + N)) 空间O(1)

    //分析： 可以证明吃巧克力必然形成一个从0号开始的圈。
    //因为0， M % N,  M * 2 % N .... 这些编号，如果有两个相等，
    //比如a * M % N 和 b * M % N，满足0 < a < b 且a * M % N == b * M % N, 
    //则有（b - a) * M % N == 0 ，说明再出现b * M % N 之前，已经出现了0。 
    //于是问题等价于 求一个最小的正整数满足 x * M % N == 0 ,这样的x = N / gcd(M , N)。
    //gcd: 求 最大公约数
    public class ChocolatesByNumbers{
        int gcd(int x,int y) {  
            return y?gcd(y, x % y):x;  
        }  
        int solution(int N, int M) {  
            // write your code in C++98  
            return N / gcd(M, N);  
        }  
    }

    //（2） 给定两个等长的整数数组，计算有多少(A【i】, B【i】) 包含的质因数种类相同？
    //数据范围：数组长度Z [1..6000]，每个数的范围[1..2147483647]。
    //要求复杂度 时间 O(Z＊(log(max(A【i】 + B【i】)) ^ 2)， 空间 O(1)
    //分析： 假设g = gcd(A【i】, B【i】)，我们下面要做的就是看A【i】 
    //和B【i】是否和g包含相同的质因数。我们不断的求g' = gcd(A【i】, g)，
    //然后从A【i】中约掉g'，当g' == 1的时候（互质），如果A【i】 == 1 就说明它们的质因数种类相同。
    //这个时间复杂度怎么算？每次求gcd的时间复杂度 O(log(A【i】 + g)) ＝ O(logA【i】),
    // 要求多少次？ 每次做出发A【i】至少一个质因数的指数减少1，
    //所以最多循环的次数是A【i】里面所有质数的指数的和。于是最多循环次数是log(A【i】)次，
    //所以求一组pair的时间复杂度在O((log(A【i】) ^ 2) + O(log(B【i】) ^ 2) 取max相加正好是要求的复杂度。
    public CalcuateSameGcd{
        int gcd(int x,int y) {  
            return y?gcd(y, x % y):x;  
        }  
  
        bool same(int x,int y) {  
            for (; y > 1; ) {  
                y = gcd(x, y);  
                x /= y;  
            }  
            return (x == 1);  
        }  
  
        int solution(vector<int> &A, vector<int> &B) {  
            // write your code in C++11  
            int n = A.size(), answer = 0;  
            for (int i = 0;  i < n; ++i) {  
                int g = gcd(A【i】, B【i】);  
                if (same(A【i】, g) && same(B【i】, g)) {  
                    ++answer;  
                }  
            }  
            return answer;  
        }  
    }
    //其实我们只要看A【i】的足够大次方 % B【i】 == 0  && B【i】的足够大次方 % A【i】 == 0即可。
    //这是因为如果它们包含的质因数相同，一个的足够大次方（当指数足够大的时候）
    //必然是另外一个的倍数。问题是“足够大”是多大？其实这个次方数是log级别的。
    //当然算出这个足够大可以二分，也可以循环。模取幂也是log的复杂度，于是如果前面用二分的话，
    //我们得到了一个单组的loglog的算法。但是我觉得这点没必要了，如果循环的话，
    //我们得到的是log的算法。那不如我们直接取足够大为对方，更简单。
    //即求A【i】 ^ B【i】 % B【i】 == 0 && B【i】 ^ A【i】 % A【i】 == 0，
    //这肯定足够大了……注意模取幂是log的复杂度……
    public CalcuateSameGcd2{
        int mul(long long x,long long y,int m) {  
            return x * y % m;  
        }  
        
        int powermod(int x,int y,int m) {  
        int r = 1 % m;  
            for (; y ; y >>= 1) {  
                if (y & 1) {  
                    r = mul(r, x, m);  
                }  
                x = mul(x, x, m);  
            }  
            return r;  
        }  
        
        int solution(vector<int> &A, vector<int> &B) {  
            // write your code in C++11  
        int n = A.size(), answer = 0;  
            for (int i = 0; i < n; ++i) {  
                if ((powermod(A【i】, B【i】, B【i】) == 0) && (powermod(B【i】, A【i】, A【i】) == 0)) {  
                    ++answer;  
                }  
            }  
            return answer;  
        }  
    }
}