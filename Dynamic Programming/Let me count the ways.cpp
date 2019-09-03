// Accepted - UVa 357 Let Me Count The Ways

#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

int coins[] = {1,5,10,25,50};
unsigned long long dp[5][30001];

unsigned long long allCoins(int index, int val) {
	if(val == 0) return 1;
	if(index < 0 || val < 0) return 0;

	if(dp[index][val]!=-1) return dp[index][val];

	return (dp[index][val] = allCoins(index-1,val)+allCoins(index,val-coins[index]));
}

int main() {

	int numCoins = 5;

	memset(dp,-1,sizeof(dp));

	int val;

	while(cin >> val) {

		unsigned long long res = allCoins(4,val);

		if(res == 1) {
			cout << "There is only 1 way to produce " << val << " cents change." << endl;
		} else {
			cout << "There are " << res << " ways to produce " << val << " cents change." << endl;
		}

	}


	

	return 0;
}
