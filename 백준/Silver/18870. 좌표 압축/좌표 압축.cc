#include<iostream>
#include<unordered_map>
#include<unordered_set>
#include<vector>
#include<algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    unordered_set<int> countSet;
    unordered_map<int, int> sumMap;

    int n;
    cin >> n;

    int* arr = new int[n];

    int key;
    for (int i = 0; i < n; i++) {
        cin >> key;
        arr[i] = key;
        countSet.insert(key);
    }

    vector<int> values(countSet.begin(), countSet.end());
    sort(values.begin(), values.end());

    int sum = 0;
    for (int v : values) {
        sumMap[v] = sum;
        sum++;
    }

    for (int i = 0; i < n; i++) {
        cout << sumMap[arr[i]] << " ";
    }

    return 0;
}