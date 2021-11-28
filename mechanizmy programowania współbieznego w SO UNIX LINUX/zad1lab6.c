#include <stdio.h>
#include <math.h>
#include "mpi.h"
int main(int argc, char **argv)
{
    int p, n;
    int tag = 50;
    int suma = 0;
    float w = 0;
    MPI_Init( &argc, &argv);
    MPI_Comm_rank( MPI_COMM_WORLD, &p);
    MPI_Comm_size( MPI_COMM_WORLD, &n);
    MPI_Status status;
    if(p!=0)
    {
        MPI_Recv(&w, 1, MPI_FLOAT, p-1, tag, MPI_COMM_WORLD, &status);
    }
    w = w + powf(-1, p)/(2 * (p+1) - 1)*4;
    if(p!=n-1)
    {
        MPI_Send(&w, 1, MPI_FLOAT, p+1, tag, MPI_COMM_WORLD);
    }
    else
    {
        printf("Liczba PI wynosi = %f\n", w);
    }
    MPI_Finalize();
    return 0;
}