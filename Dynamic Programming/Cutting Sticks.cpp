// Accepted - UVa 10003 Cutting Sticks

#include <iostream>
#include <vector>
#include <climits>
#include <cstring>
using namespace std;

vector<int> cuts;
int dp[52][52] = {0};

int minCut(int left, int right) {
	if(dp[left][right]!=-1) return dp[left][right];
	if(right == left + 1) return 0;

	int mini = INT_MAX;

	for (int i = left+1; i < right; ++i)
	{
		mini = min(mini, minCut(left,i)+minCut(i,right)+(cuts[right]-cuts[left]));
	}

	return (dp[left][right]=mini);
}

int main() {
	int size;
	cin >> size;
	
	while(size != 0) {
		int numCuts;
		cin >> numCuts;

		cuts.clear();
		cuts.push_back(0);

		for (int i = 0; i < numCuts; ++i)
		{
			int add;
			cin >> add;
			cuts.push_back(add);
		}

		cuts.push_back(size);

		memset(dp,-1,sizeof(dp));

		cout << "The minimum cutting is " << minCut(0,numCuts+1) << "." << endl;

		cin >> size;
	}

	return 0;
}

/*

100
3
25 50 75
10
4 4 5 7 8
0

*/
