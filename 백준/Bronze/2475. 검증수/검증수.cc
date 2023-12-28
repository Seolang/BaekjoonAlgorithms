#include<iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    int sum = 0;
    
    for(int i=0; i<5; i++) {
        int n;
        cin >> n;
        sum += n*n;
    }
    
    cout << sum % 10;
    
    return 0;
}