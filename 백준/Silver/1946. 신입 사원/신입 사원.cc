#include<iostream>
#include<algorithm>
#include<utility>

using namespace std;

typedef pair<int, int> pii;

pii arr[100000];

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int t;
    cin >> t;

    for (int _case = 0; _case < t; _case++) {

        int n;
        cin >> n;

        
        
        for (int i = 0; i < n; i++) {
            cin >> arr[i].first >> arr[i].second;
        }

        sort(arr, arr+n);

        int maxSecond = n+1;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i].second < maxSecond) {
                answer++;
                maxSecond = arr[i].second;
            }
        }

        cout << answer << '\n';
    }
   
    return 0;
}