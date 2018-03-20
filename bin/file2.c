#include<stdio.h> 
#include<stdlib.h> 
#define M 15 
#define N 15 
struct mark //�����Թ��ڵ���������� 
{ 
int x; 
int y; 
}; 

struct Element //"��"ջԪ�أ��ٺ١��� 
{ 
int x,y; //x��,y�� 
int d; //d��һ���ķ��� 
}; 

typedef struct LStack //��ջ 
{ 
Element elem; 
struct LStack *next; 
}*PLStack; 

/*************ջ����****************/ 

int InitStack(PLStack &S)//�����ջ 
{ 
S=NULL; 
return 1; 
} 

int StackEmpty(PLStack S)//�ж�ջ�Ƿ�Ϊ�� 
{ 
if(S==NULL) 
return 1; 
else 
return 0; 
} 

int Push(PLStack &S, Element e)//ѹ��������Ԫ�� 
{ 
PLStack p; 
p=(PLStack)malloc(sizeof(LStack)); 
p->elem=e; 
p->next=S; 
S=p; 
return 1; 
} 

int Pop(PLStack &S,Element &e) //ջ��Ԫ�س�ջ 
{ 
PLStack p; 
if(!StackEmpty(S)) 
{ 
e=S->elem; 
p=S; 
S=S->next; 
free(p); 
return 1; 
} 
else 
return 0; 
} 

/***************���Թ�·������***********************/ 
void MazePath(struct mark start,struct mark end,int maze[M][N],int diradd[4][2]) 
{ 
int i,j,d;int a,b; 
Element elem,e; 
PLStack S1, S2; 
InitStack(S1); 
InitStack(S2); 
maze[start.x][start.y]=2; //��ڵ����ϱ�� 
elem.x=start.x; 
elem.y=start.y; 
elem.d=-1; //��ʼΪ-1 
Push(S1,elem); 
while(!StackEmpty(S1)) //ջ��Ϊ�� ��·������ 
{ 
Pop(S1,elem); 
i=elem.x; 
j=elem.y; 
d=elem.d+1; //��һ������ 
while(d<4) //��̽���������������� 
{ 
a=i+diradd[d][0]; 
b=j+diradd[d][1]; 
if(a==end.x && b==end.y && maze[a][b]==0) //������˳��� 
{ 
elem.x=i; 
elem.y=j; 
elem.d=d; 
Push(S1,elem); 
elem.x=a; 
elem.y=b; 
elem.d=886; //�������Ϊ-1 �ж��Ƿ��˳��� 
Push(S1,elem); 
printf("\n0=�� 1=�� 2=�� 3=�� 886Ϊ���߳��Թ�\n\nͨ·Ϊ:(������,������,����)\n"); 
while(S1) //�������� ������Թ�·������ 
{ 
Pop(S1,e); 
Push(S2,e); 
} 
while(S2) 
{ 
Pop(S2,e); 
printf("-->(%d,%d,%d)",e.x,e.y,e.d); 
} 
return; //��������ѭ����������break,�����ֳ���exit�ֻ��������ѡ��return���ǲ����
} 
if(maze[a][b]==0) //�ҵ�����ǰ���ķǳ��ڵĵ� 
{ 
maze[a][b]=2; //����߹��˵� 
elem.x=i; 
elem.y=j; 
elem.d=d; 
Push(S1,elem); //��ǰλ����ջ 
i=a; //��һ��ת��Ϊ��ǰ�� 
j=b; 
d=-1; 
} 
d++; 
} 
} 
printf("û���ҵ������߳����Թ���·��\n"); 
} 

/*************�����Թ�*******************/ 
void initmaze(int maze[M][N]) 
{ 
int i,j; 
int m,n; //�Թ���,�� [/M] 

printf("�������Թ������� m="); 
scanf("%d",&m); 
printf("�������Թ������� n="); 
scanf("%d",&n); 
printf("\n�������Թ��ĸ��и���:\n�ÿո����,0����·,1����ǽ\n",m,n); 
for(i=1;i<=m;i++) 
for(j=1;j<=n;j++) 
scanf("%d",&maze[i][j]); 
printf("�㽨�����Թ�Ϊ(����ȦΪǿ)...\n"); 
for(i=0;i<=m+1;i++) //��һȦΧǽ 
{ 
maze[i][0]=1; 
maze[i][n+1]=1; 
} 
for(j=0;j<=n+1;j++) 
{ 
maze[0][j]=1; 
maze[m+1][j]=1; 
} 
for(i=0;i<=m+1;i++) //����Թ� 
{ 
for(j=0;j<=n+1;j++) 
printf("%d ",maze[i][j]); 
printf("\n"); 
} 
} 

void main() 
{ 
int sto[M][N]; 
struct mark start,end; //start,end��ںͳ��ڵ����� 
int add[4][2]={{0,1},{1,0},{0,-1},{-1,0}};//�������������� ��������Ϊ�����ϱ� [/M] 

initmaze(sto);//�����Թ� 

printf("������ڵĺ�����,������[���Ÿ���]\n"); 
scanf("%d,%d",&start.x,&start.y); 
printf("������ڵĺ�����,������[���Ÿ���]\n"); 
scanf("%d,%d",&end.x,&end.y); 

MazePath(start,end,sto,add); //find path 
system("PAUSE"); 
}
