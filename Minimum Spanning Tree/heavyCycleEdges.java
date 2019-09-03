// Accepted - UVa 11747 Heavy Cycle Edges

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class heavyCycleEdges {

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
		int nodes = in.nextInt();
		int edgeCount = in.nextInt();
		while(nodes != 0 && edgeCount != 0) {
			parent = new int[nodes+1];
			for(int i=0;i<nodes+1;i++) {
				parent[i]=i;
			}
			ii[] edges = new ii[edgeCount];
			for(int i=0;i<edgeCount;i++) {
				edges[i] = new ii(in.nextInt(), in.nextInt(), in.nextInt());
			}

			Arrays.sort(edges);

			ArrayList<Integer> set = new ArrayList<Integer>();

			for(int i=0;i<edgeCount;i++) {
				ii edge = edges[i];
				if(!isConnected(edge.a,edge.b)) {
					union(edge.a,edge.b);
				} else {
					set.add(edge.c);
				}
			}
			if(set.size() == 0) {
				System.out.println("forest");
			} else {
				for(int i=0;i<set.size();i++) {
					if(i==set.size()-1) {
						System.out.println(set.get(i));
					} else {
						System.out.print(set.get(i)+" ");
					}
				}
			}

			nodes = in.nextInt();
			edgeCount = in.nextInt();

		}
		
	}
}

class ii implements Comparable<ii> {
	int a,b,c;
	public ii(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int compareTo(ii other) {
		return this.c-other.c;
	}
}



/*


3 3
0 1 1
1 2 2
2 0 3
4 5
0 1 1
1 2 2
2 3 3
3 1 4
0 2 0
3 1
0 1 1
0 0



*/