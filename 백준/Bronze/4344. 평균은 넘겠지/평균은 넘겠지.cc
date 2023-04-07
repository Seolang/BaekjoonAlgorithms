#include <iostream>
#include <stdio.h>
using namespace std;

int main() {
	
	int C=0;
	int N,P[1000],sum=0;
	double R[1000],rum=0;
	
	cin >> C;
	for (int i=0;i<C;i++)
	{
		cin >> N;
		for (int j=0; j<N; j++)
		{
			cin >> P[j];
			sum += P[j];
			
		}
		
		sum=sum/N;
		
		for (int k=0; k<N; k++)
		{
			if (P[k] > sum)
			{
				rum++;
			}
			else
			{
			}
			
		}

		R[i] = 100*(rum/N);
		
		sum=0;
		rum=0;
	}
	
	for (int l=0; l<C; l++)
		printf("%0.3f%%\n",R[l]);
	
	return 0;
}