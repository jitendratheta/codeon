package mypackage;

import main.FastScanner;
import java.io.PrintWriter;

public class BiasedCoinKHeadProbability {
    public void solve(int testNumber, FastScanner in, PrintWriter out) {

        // Total number of coins
        final int N = 5;

        // initializing probability of head coming on the coin
        double [] arr = new double[]{0.5, 0.3, 0.2, 0.15, 0.45};

        // exact 3 hhead should come
        int K = 3;

        double [][] dp = new double[N+1][N+1];

        // base multiplier for subsequesnt calculations
        dp[0][0] = 1;

        // this stores all positive case probility for i coins i heads
        for(int j = 1; j <= N; ++j)
            dp[j][0] = dp[j-1][0] * (arr[j-1]);

        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < K; ++j) {
                // for i_1, j+1,  either previous should have j+1 positive and negative case here +
                //                       previosu should have j positive and  positive case here will give result
                dp[i+1][j+1] = dp[i][j+1] * (1 - arr[i]) + (dp[i][j] * arr[i]);
            }
        }

        for(int i = 0; i <= N; ++i) {
            for (int j = 0; j <= K; ++j)
                out.printf("%.3f ", dp[i][j]);
            out.println();
        }

        out.print(dp[N][K]);
    }
}
