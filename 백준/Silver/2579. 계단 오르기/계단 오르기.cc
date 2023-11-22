#include<iostream>

using namespace std;

int main() {
    int n;
    cin >> n;
    int* arr = new int[n];
    int** dp = new int* [n];

    for (int i = 0; i < n + 3; i++)
        dp[i] = new int[3]();

    for (int i = 0; i < n; i++)
        cin >> arr[i];


    // [x][0] : OX
    // [x][1] : XO
    // [x][2] : OO

    dp[0][0] = 0;
    dp[0][1] = dp[0][2] = arr[0];

    for (int i = 1; i < n; i++) {
        dp[i][0] = max(dp[i - 1][1], dp[i - 1][2]);
        dp[i][1] = dp[i - 1][0] + arr[i];
        dp[i][2] = dp[i - 1][1] + arr[i];
    }

    cout << max(dp[n - 1][1], dp[n - 1][2]) << endl;
    return 0;
}