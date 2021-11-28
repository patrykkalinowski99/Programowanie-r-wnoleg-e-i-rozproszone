#include <stdio.h>
#include "mpi.h"
float func(float x)
{
    return x * x;
}
int main(int argc, char **argv)
{
    int p, n;
    int tag = 50;
    MPI_Init( &argc, &argv );
    MPI_Comm_rank( MPI_COMM_WORLD, &p );
    MPI_Comm_size( MPI_COMM_WORLD, &n);
    MPI_Status status;
    float w = 0;
    float a = 1;
    float b = 10;
    float dx = (b-a)/n;
    if(p==n-1)
    {
	w += func(a + (p*dx)+1);
        MPI_Send(&w, 1, MPI_FLOAT, p-1, tag, MPI_COMM_WORLD);
    }
    if((p>=0) && (p < n - 1))
	{
        MPI_Recv(&w, 1, MPI_FLOAT, p+1, tag, MPI_COMM_WORLD, &status);
	w += func(a + (p+1)*dx);
 	  if(p!=0)
 	   {
 	       MPI_Send(&w, 1, MPI_FLOAT, p-1, tag, MPI_COMM_WORLD);
 	   }
 	   else
 	   {
		w += (func(a) + func(b))/2;
 	       printf("Calka jest rowna = %f\n", dx * w);
        }
    }
    MPI_Finalize();
    return 0;
}