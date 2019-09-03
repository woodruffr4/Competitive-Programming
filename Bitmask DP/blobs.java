// Accepted

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.io.PrintWriter;

public class blobs {

	public static loc[] blobs;
	public static int r,c,n;
	public static int[] dx = {0,1,1,1,0,-1,-1,-1};
	public static int[] dy = {1,1,0,-1,-1,-1,0,1};
	public static int[][][] dp;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int num = in.nextInt();
		dp = new int[5][5][(1<<(16))];
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				Arrays.fill(dp[i][j],-1);
			}
		}
		for(int i=1;i<=num;i++) {
			r = in.nextInt();
			c = in.nextInt();
			n = in.nextInt();
			blobs = new loc[n];
			int start = 0;
			for(int j=0;j<n;j++) {
				blobs[j] = new loc(in.nextInt()-1,in.nextInt()-1);
				start |= getMaskFromLocation(blobs[j]);
			}
			int res = topDown(start);
			pw.printf("Case %d: %d\n",i,res);
		}
		pw.close();
	}

	public static int topDown(int mask) {
		if(dp[r][c][mask]!=-1) {
			return dp[r][c][mask];
		}
		if(isEndState(mask)) {
			return (dp[r][c][mask]=1);
		}
		List<Integer> newStates = getNewStates(mask);
		int res = 0;
		for(int k: newStates) {
			res+=topDown(k);
		}
		return (dp[r][c][mask]=res);
	}

	public static List<loc> getLocationsFromBitmask(int mask) {
		List<loc> list = new LinkedList<loc>();
		for(int i=0;i<r*c;i++) {
			if((mask & (1<<i)) != 0) {
				list.add(new loc(i/c,i%c));
			} 
		}
		return list;
	}

	public static boolean isEndState(int mask) {
		int count = 0;
		for(int i=0;i<r*c;i++) {
			if( (mask & (1<<i)) != 0) count++;
		}

		return count==1;
	}

	public static int getMaskFromLocation(loc l) {
		return (1<<(c*l.x+l.y));
	}

	public static boolean blobAtLocationGivenState(int mask, int x, int y) {
		loc l = new loc(x,y);
		int bitmask = getMaskFromLocation(l);
		return (bitmask & mask) != 0;
	}

	public static List<Integer> getNewStates(int mask) {
		List<loc> l = getLocationsFromBitmask(mask);
		List<Integer> list = new LinkedList<Integer>();
		for(loc cur: l) {
			for(int i=0;i<dx.length;i++) {
				if(inBounds(cur.x+dx[i],cur.y+dy[i]) && inBounds(cur.x+2*dx[i],cur.y+2*dy[i]) && blobAtLocationGivenState(mask,cur.x+dx[i],cur.y+dy[i]) && !blobAtLocationGivenState(mask,cur.x+2*dx[i],cur.y+2*dy[i])) {
					int maskRemoved = getMaskFromLocation(new loc(cur.x+dx[i],cur.y+dy[i]));
					int maskRemoved2 = getMaskFromLocation(cur);
					int maskAdd = getMaskFromLocation(new loc(cur.x+2*dx[i],cur.y+2*dy[i]));
					list.add(mask ^ maskRemoved ^ maskRemoved2 | maskAdd);
				}
			}
		}

		return list;
	}

	public static boolean inBounds(int x, int y) {
		return x<r && x>=0 && y<c && y>=0;
	}
}

class loc {
	int x, y;
	public loc(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return this.x+" "+this.y;
	}
}



/*


3
1 2 1
1 1
3 3 8
2 3
2 1
1 2
3 3
3 2
3 1
1 1
1 3
3 3 3
3 1
2 2
1 2


*/