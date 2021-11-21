#include <unistd.h>
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>
float leib(int n)
{
    float w = 0, x = 0;
    int i;
    for (i = 1; i <= n; i++)
    {
        x = powf(-1, i-1) / (2*i-1);
        w += x;
    }
    return 4 * w;
}
int main ()
{
    int thread;
    printf("Ilosc watkow: ");
    scanf("%d", &thread);
    int i;
    for(i=0; i<thread; i++)
    {
        if(fork()==0)
        {
            srand(time(NULL) ^ (getpid()<<16));
            int n = 100 + rand()%5000+1;
            printf("N = %d ", n);
            float x = leib(n);
            printf("Przyblizenie Pi ze wzoru leibniza = %f", x);
            printf("\n");
            exit(0);
        }
    }
}