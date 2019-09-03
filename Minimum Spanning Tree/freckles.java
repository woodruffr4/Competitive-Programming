// Accepted - UVa 10034 Freckles

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class freckles {

	public static int[] parent;

	public static boolean isConnected(int a, int b) {
		return find(a)==find(b);
	}

	public static int find(int a) {
		if(parent[a]==a) return a;
		return (parent[a] = find(parent[a]));
	}

	public static void union(int a, int b) {
		if(!isConnected(a,b)) {
			int x = find(a), y = find(b);
			parent[y]=x;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = Integer.parseInt(in.nextLine());
		in.nextLine();
		while(num-->0) {

			int numPoints = Integer.parseInt(in.nextLine());
			parent = new int[numPoints+1];
			for(int i=0;i<parent.length;i++) {
				parent[i]=i;
			}
			List<point> points = new ArrayList<point>();
			for (int i=0; i<numPoints; i++) {
				String[] line = in.nextLine().split(" ");
				points.add(new point(Double.parseDouble(line[0]),Double.parseDouble(line[1])));
			}

			List<ii> edges = new LinkedList<ii>();

			for(int i=0;i<numPoints;i++) {
				for(int j=i+1;j<numPoints;j++) {
					point a = points.get(i);
					point b = points.get(j);
					//System.out.println(dist(a,b));
					edges.add(new ii(i,j,dist(a,b)));
				}
			}

			Collections.sort(edges);

			double res = 0;

			for(int i=0;i<edges.size();i++) {
				ii next = edges.get(i);
				if(!isConnected(next.a,next.b)) {
					//System.out.println(next.c);
					res+=next.c;
					union(next.a,next.b);
				}
			}

			System.out.printf("%.2f\n",res);

			if(num!=0) {
				System.out.println();
				in.nextLine();
			}
		}
	}

	public static double dist(point a, point b) {
		return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
	}
}

class point {
	double x,y;
	public point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class ii implements Comparable<ii> {
	int a,b;
	double c;
	public ii(int a, int b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int compareTo(ii other) {
		if(this.c<other.c) return -1;
		else return 1;
	}
}


/*

1

3
1.0 1.0
2.0 2.0
2.0 4.0


*/