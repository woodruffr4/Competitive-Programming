// Accepted - UVa 10980 Lowest Price in Town

import java.util.Scanner;
import java.util.HashMap;
import java.io.PrintWriter;

public class lowestPriceInTown {


	public static double[] dp;
	public static double[] dp2;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int counter = 1;
		while(in.hasNextLine()) {
			String[] a = in.nextLine().split(" ");
			double base = Double.parseDouble(a[0]);
			int num = Integer.parseInt(a[1]);
			dp = new double[101];
			HashMap<Integer, Double> map = new HashMap<Integer, Double>();
			for (int i=0; i<num; i++) {
				String[] b = in.nextLine().split(" ");
				map.put(Integer.parseInt(b[0]), Math.min(Double.parseDouble(b[1]),map.getOrDefault(Integer.parseInt(b[0]),2000.0)));
			}

			for(int i=1;i<dp.length;i++) {
				dp[i] = i*base;
			}

			for(int i=0;i<dp.length;i++) {
				for(int k: map.keySet()) {
					if(i+k<101) {
						for(int j=1;j<=k;j++) {
							dp[i+j] = Math.min(dp[i+j],dp[i]+map.get(k));
						}
					}
				}
			}

			String[] queries = in.nextLine().split(" ");
			out.printf("Case %d:\n",counter);
			for(String s: queries) {
				int q = Integer.parseInt(s);
				out.printf("Buy %d for $%.2f\n",q,dp[q]);
			}

			counter++;
		}
		out.close();

	}
}


/*


22.00 2
2 22.00
4 60.00
2 4
25.00 2
2 48.00
2 46.00
2
22.00 2
2 22.00
4 40.00
1 2 3


*/