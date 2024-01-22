#include<iostream>
#include<algorithm>

using namespace std;

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    int answer = 0;
    cin >> n;

    int* arr = new int[n];

    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    sort(arr, arr + n);

    for (int i = 0; i < n; i++) {
        int front = 0;
        int back = n - 1;

        while (front < back) {

            if (front == i) {
                front++;
            }
            else if (back == i) {
                back--;
            }
            else {
                int in = arr[i];
                int fn = arr[front];
                int bn = arr[back];

                if (in == fn + bn) {
                    answer++;
                    break;
                }
                else if (in < fn + bn) {
                    back--;
                }
                else {
                    front++;
                }
            }
        }
    }
   
    cout << answer;

    return 0;
}