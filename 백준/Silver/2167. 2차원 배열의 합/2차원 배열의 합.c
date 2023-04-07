#include <stdio.h>
int main(void) {
	int N, M, store[301][301], i, j, x, y, k, v, w, sum[10001],z,a,b,c;
	scanf("%d %d", &N, &M);
	for (v = 0; v < N; v++)
	{
		for (w = 0; w < M; w++)
		{
			scanf("%d", &store[v][w]);
		}
	}
	scanf("%d", &k);
	z = 0;
	while (z < k)
	{
		scanf("%d %d %d %d", &i, &j, &x, &y);
		sum[z] = 0;
		for (a = i - 1; a < x; a++)
		{
			for (b = j - 1; b < y; b++)
			{
				sum[z] = sum[z] + store[a][b];
			}
		}
		z++;
	}
	for (c = 0; c < k; c++)
	{
		printf("%d\n", sum[c]);
	}
	return 0;
}