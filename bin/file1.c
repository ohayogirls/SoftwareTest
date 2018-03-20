#include<stdio.h>
#include<string.h>
#define SIZE 20
int main()
{
	char str[SIZE]={'\0'};
	int count=0;
	printf("please input the string\n");
	gets(str);
	puts(str);
	int length = strlen(str);
	int i;
	for(i=0;i<length;i++)
	{
		if(str[i]!=' ')
		{
			count++;
			while(str[i]!=' '&&str[i]!='\0')
			i++;
		}
	}
	printf("%d\n",count);
	return 0;
}
