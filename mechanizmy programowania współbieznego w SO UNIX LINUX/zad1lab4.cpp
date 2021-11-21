#include <unistd.h>
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
float fx(float x)
{
    return 4*x*x-6*x+5;
}
float m_trap(float a, float b, float n)
{
    float w = 0;
    float dx = (b-a)/n;
    int i;
    for(i=1;i<=n-1;i++)
    {
        w += fx(a+i*dx);
    }
    w += (fx(a)+fx(b))/2;
    w *= dx;
    return w;
}
int main()
{
    printf("ilosc procesow: ");
    int threads;
    scanf("%d",&threads);
    int i;
    for(i=0; i < threads; i++)
    {
        if(fork()==0)
        {
            srand(time(NULL) ^ (getpid()<<16));
            int a = rand()%50;
            int b = a + 1 + rand()%50;
            int n = 50 + rand()%50;
            float w = m_trap(a, b, n);
            
            printf("\nPrzedzial a: %d ", a, b);
            printf("\nPrzedzial b: %d ", b);
            printf("\nwynik: %f ", w);
            exit(0);
        }
    }
}