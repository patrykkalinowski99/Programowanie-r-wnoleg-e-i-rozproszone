import java.util.Scanner;
import java.util.concurrent.Semaphore;
public class Glowna
{
	static double ILOSC = 100;
	static Semaphore[] widelec = new Semaphore [(int) ILOSC] ;
	public static void main (String [] args)
	{
		int metoda;
		Scanner myObj = new Scanner(System.in);
	    System.out.print("Wybierz wariant: ");
	    metoda = myObj.nextInt();
		if (metoda < 1 || metoda > 3)
		{
			System.out.println("Brak metody (dostêpne: 1, 2, 3)");
		}
		else
		{
		    System.out.print("Iloœæ filozofów: ");
		    ILOSC = myObj.nextDouble();
		    if (ILOSC >= 2 && ILOSC <= 100 )
		    {
		    	for (int i = 0; i< ILOSC; i++)
		    	{
					widelec [i]=new Semaphore( 1 ) ;
				}
				for (int i = 0; i< ILOSC; i++)
				{
					if (metoda == 1)
					{
						new Filozofnr1(i, ILOSC, widelec).start();
					}
					else if (metoda == 2)
					{
						new Filozofnr2(i, ILOSC, widelec).start();
					}
					else if (metoda == 3)
					{
						new Filozofnr3(i, ILOSC, widelec).start();
					}
				}
		    }
		    else
			{
		    	System.out.println("Poda³eœ z³¹ iloœæ filozofów ");
			}
		}
	}
}
//Patryk Kalinowski 80224