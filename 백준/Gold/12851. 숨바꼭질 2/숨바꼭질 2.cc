#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int N, K, ans = 999999, way = 0;
int dp[100004], cnt[100004];
void BFS() {
	dp[N] = 1;
	cnt[N] = 1;
	if (N == K) {
		return;
	}

	queue<int> q;
	q.push(N);

	while (!q.empty()) {
		int now = q.front();
		q.pop();

		if (dp[now] > ans) break;

		for (auto next : { now - 1, now + 1, now * 2 }) {
			if (0 <= next && next <= 100000) {
				if (!dp[next]) {
					q.push(next);
					dp[next] = dp[now] + 1;
					cnt[next] = cnt[now];

					if (next == K) {
						ans = dp[next];
					}
				}

				else if (dp[next] == dp[now] + 1) {
					cnt[next] += cnt[now];
				}
			}
		}

	}

}

int main() {
	cin >> N >> K;
	BFS();
	cout << dp[K] - 1 << '\n' << cnt[K] << '\n';
	return 0;
}