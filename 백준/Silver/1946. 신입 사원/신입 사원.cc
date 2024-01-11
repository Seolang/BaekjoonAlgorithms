#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;

    for (int _case = 0; _case < t; _case++) {

        int n;
        cin >> n;

        vector<vector<int>> arr(n, vector<int>(2, 0));
        
        for (int i = 0; i < n; i++) {
            cin >> arr[i][0] >> arr[i][1];
        }

        sort(arr.begin(), arr.end());

        int maxSecond = n+1;
        int answer = 0;

        for (auto& rank : arr) {
            if (rank[1] < maxSecond) {
                answer++;
                maxSecond = rank[1];
            }
        }

        cout << answer << '\n';
    }
   
    return 0;
}