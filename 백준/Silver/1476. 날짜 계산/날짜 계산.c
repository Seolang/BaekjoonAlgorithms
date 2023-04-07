#include <stdio.h>
int main(void)
{
	int E, S, M, e, s, m, y;;
	e = 0;
	s = 0;
	m = 0;
	y = 0;
	scanf("%d %d %d", &E, &S, &M);
	while (e != E || s != S || m != M)
	{
		e++;
		s++;
		m++;
		y++;
		if (e == 16)
		{
			e = 1;
		}
		if (s == 29)
		{
			s = 1;
		}
		if (m == 20)
		{
			m = 1;
		}
	}
	printf("%d\n", y);
	return 0;
}