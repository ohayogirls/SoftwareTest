#include<stdio.h>
#include<math.h>
int main(void)
{
	unsigned int x,y;
	printf("������һ��С��1000������:");
	scanf("%d",&x);
	y=sqrt(x);
	if (x>=1000)
	{
	printf("��ֵ������Ҫ������������"); 
	scanf("%d",&x);
	}
	else
	printf("����ƽ����Ϊ%d",y); 
	getch();
	return 0; 
}
