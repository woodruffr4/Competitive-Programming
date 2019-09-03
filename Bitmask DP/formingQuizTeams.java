import java.util.*;

public class formingQuizTeams {

	public static double[] dp;
	public static student[] students;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int counter = 1;
		while(num != 0) {

			students = new student[2*num];
			dp = new double[(1<<(2*num))];
			Arrays.fill(dp,1e9);
			for (int i=0; i<2*num; i++) {
				in.next();
				students[i] = new student(in.nextInt(),in.nextInt());
			}

			int max = (1<<(2*num))-1;
			double res = doIt(0,max);

			System.out.printf("Case %d: %.2f\n",counter,res);

			num = in.nextInt();
			counter++;
		}
	}

	public static double doIt(int bitmask, int max) {
		if(bitmask == max) {
			return 0;
		}
		if(dp[bitmask] != 1e9) return dp[bitmask];
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i=0;i<students.length;i++) {
			if((bitmask & 1<<i) == 0) {
				list.add(i);
			}
		}

		double min = 1e9;

		for(int j=1;j<list.size();j++) {
			min = Math.min(min, dist(students[list.get(j)],students[list.get(0)])+doIt(bitmask | (1<<list.get(0)) | (1<<list.get(j)),max));
		}

		return dp[bitmask] = min;
		
	}

	public static double dist(student a, student b) {
		return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
	}
}

class student {
	int x, y;
	public student(int x, int y) {
		this.x = x;
		this.y = y;
	}
}



/*

8
sohel 10 10
mahmud 20 10
sanny 5 5
prince 1 1
per 120 3
mf 6 6
kugel 50 60
joey 3 24
limon 6 9
manzoor 0 0
hot 2 43
dog 2 24
on 43 6
the 53 23
live 42 10
long 9 56
1
derek 9 9
jimmy 10 10
0



*/