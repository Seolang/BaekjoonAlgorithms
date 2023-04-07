#include <iostream>
#include <algorithm>
#include <queue>
#include <stack>

using namespace std;

typedef pair<int, vector<int> > piv;

int N, K, ans = 999999;
int dp[100004], path[100004];
int ansPath;

void BFS() {
	dp[N] = 1;
	path[N] = -1;
	if (N == K) {
		ansPath = N;
		return ;
	}

	queue<int> q;
	q.push(N);

	while (!q.empty()) {
		int now = q.front();
		q.pop();

		for (auto next : { now - 1, now + 1, now * 2 }) {
			if (0 <= next && next <= 100000) {
				if (!dp[next]) {
					path[next] = now;
					q.push(next);
					dp[next] = dp[now] + 1;

					if (next == K) {
						ans = dp[next];
						ansPath = next;
						return;
					}
				}
			}
		}
	}
}

int main() {
	cin >> N >> K;
	BFS();
	cout << dp[K] - 1 << '\n';
	stack<int> stk;
	while (ansPath != -1) {
		stk.push(ansPath);
		ansPath = path[ansPath];
	}

	while (!stk.empty()) {
		cout << stk.top() << " ";
		stk.pop();
	}
	return 0;
}