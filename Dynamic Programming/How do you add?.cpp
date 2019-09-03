// Accepted - UVa 10943 How do you add?

#include <iostream>
using namespace std;

int dp[101][101] = {0};



int tableFill(int n, int k) {
	if(n<0 || k<0 || n>100 || k>100) return 0;
	if(k==1) return 1;
	if(dp[n][k]!=0) return dp[n][k];

	int sum = 0;

	for (int i = 0; i <= n; ++i)
	{
		sum += tableFill(n-i, k-1);
		sum%=1000000;
	}

	return (dp[n][k]=sum);

}



int main() {
	int n,k;
	cin >> n >> k;

	while(n!=0 && k!=0) {
		cout << tableFill(n,k) << endl;
		cin >> n >> k;
	}
	

	return 0;
}
