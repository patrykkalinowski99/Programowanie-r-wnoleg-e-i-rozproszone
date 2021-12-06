#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include "mpi.h"

#define REZERWA 500
#define TANKUJ 1000
#define MIASTO 1
#define START 2
#define TRASA 3
#define KONIEC_TRASY 4
#define AWARIA 5

int WyjedzTab[2];
int Zabierz[2];
MPI_Status mpi_status;
int paliwo = 2000;
int POSTOJ=1;
int NIE_POSTOJ=0;
int argsNum;
int arg;
int ilosc_Taxi;
int ulice = 4;
int brakPrzejazdu = 0;


void Miasto(int argsNum)
{
    int taxiNr, status;
    ilosc_Taxi = argsNum - 1;

    printf("Zyczymy Panstwu, przyjemnej podrozy \n");
    printf("Dysponujemy %d trasami\n", ulice);
    sleep(2);

    while(ulice <= ilosc_Taxi)
        {
            MPI_Recv(&Zabierz, 2, MPI_INT,MPI_ANY_SOURCE,1,MPI_COMM_WORLD, &mpi_status);
            taxiNr = Zabierz[0];
            status = Zabierz[1];
            if(status == 1)
            {
                printf("Oczekiwanie na klienta Taxi nr %d\n", taxiNr);
            }
            if(status == 2)
            {
                printf("Taxi %d wyjezdza na trase nr %d\n", taxiNr, brakPrzejazdu);
                brakPrzejazdu--;
            }
            if(status == 3)
            {
                printf("Taxi %d w trasie\n", taxiNr);
            }
            if(status == 4)
            {
                if(brakPrzejazdu<ulice)
                {
                    brakPrzejazdu++;
                    MPI_Send(&POSTOJ, 1, MPI_INT, taxiNr, 1, MPI_COMM_WORLD);
                }
                else
                {
                    MPI_Send(&PO, 1, MPI_INT, taxiNr, 1, MPI_COMM_WORLD);
                }
            }
            if(status == 5)
            {
                ilosc_Taxi--;
                printf("Ilosc Taxi %d\n", ilosc_Taxi);
            }
        }
}

void Wyjedz(int taxiNr, int stan) 
{
    WyjedzTab[0]=taxiNr;
    WyjedzTab[1]=stan;
    MPI_Send(&Wyjedz, 2, MPI_INT, 0, 1, MPI_COMM_WORLD);
    sleep(1);
}

void Taxi()
{
    int stan,suma,i;
    stan=TRASA; 
    while(1)
    {
        if(stan == 1)
        {
            if(rand()%2 == 1)
            {
                stan=START;
                paliwo=TANKUJ;
                printf("Jazda do klienta %d",arg);
                printf("\n");
                Wyjedz(arg,stan);
            }
            else
            {
                Wyjedz(arg,stan);
            }
        }
        else if(stan == 2)
        {
            printf("Wyjezdzam na trase, Taxi %d\n",arg);
            stan=TRASA;
            Wyjedz(arg,stan);
        }
        else if(stan == 3)
        {
            paliwo-=rand()%500; 
            if(paliwo<=REZERWA)
            {
                stan=KONIEC_TRASY;
                printf("Taxi konczy prace\n");
                Wyjedz(arg,stan);
            }

            else
            {
                for(i=0; rand()%10000;i++);
            }
        }
        else if(stan == 4)
        {
            int temp;
            MPI_Recv(&temp, 1, MPI_INT, 0, 1, MPI_COMM_WORLD, &mpi_status);
            if(temp==POSTOJ)
            {
                stan=MIASTO;
                printf("Rozliczenie z klientem %d\n", arg);
            }
            else
            {
                paliwo-=rand()%500;
                if(paliwo>0)
                {
                    Wyjedz(arg,stan);
                }
                else
                {
                    stan=AWARIA;
                    printf("Awaria auta\n");
                    Wyjedz(arg,stan);
                    return;
                }
            }
        }
    }
}
int main(int argc, char *argv[])
{
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD,&arg);
    MPI_Comm_size(MPI_COMM_WORLD,&argsNum);
    srand(time(NULL));
    if(arg == 0) 
    {
        Miasto(argsNum);
    }
    else 
    {
        Taxi();
        MPI_Finalize();
    }
    return 0;
}