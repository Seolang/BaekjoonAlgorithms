#include<iostream>

using namespace std;

int main() {
    int n, m;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> m;

        int* arr;
        if (m < 3) arr = new int[4]();
        else arr = new int[m + 3]();

        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        for (int j = 4; j <= m; j++) {
            arr[j] = arr[j - 1] + arr[j - 2] + arr[j - 3];
        }

        cout << arr[m] << endl;
    }
    return 0;
}