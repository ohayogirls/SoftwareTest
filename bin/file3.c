#include<stdio.h>
#include<math.h>
int main(void)
{
	unsigned int x,y;
	printf("请输入一个小于1000的数字:");
	scanf("%d",&x);
	y=sqrt(x);
	if (x>=1000)
	{
	printf("数值不符合要求，请重新输入"); 
	scanf("%d",&x);
	}
	else
	printf("它的平方根为%d",y); 
	getch();
	return 0; 
}
