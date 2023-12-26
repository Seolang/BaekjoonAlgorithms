#include<iostream>
#include<map>
#include<set>

using namespace std;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    set<int> countSet;
    map<int, int> sumMap;

    int n;
    cin >> n;

    int* arr = new int[n];

    int key;
    for (int i = 0; i < n; i++) {
        cin >> key;
        arr[i] = key;
        countSet.insert(key);
    }

    int sum = 0;

    for (auto it : countSet) {
        sumMap[it] = sum;
        sum++;
    }

    for (int i = 0; i < n; i++) {
        cout << sumMap[arr[i]] << " ";
    }

    return 0;
}