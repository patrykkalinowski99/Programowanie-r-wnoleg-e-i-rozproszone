package symulator_taxi;
import java.util.Random;
public class taxi extends Thread
{
	//definicja stanu pojazdu
	static int MIASTO =1;
	static int START=2;
	static int JAZDA =3;
	static int KONIEC_JAZDY =4;
	static int AWARIA =5;
	static int KRADZIEZ = 6;
	static int WYPADEK = 7;
	static int TANKUJ=1000;
	static int REZERWA=500;

	int numer;
	int paliwo;
	int stan;
	miasto M;
	Random rand;
	public taxi(int numer, int paliwo, miasto M)
	{
		this.numer=numer;
		this.paliwo=paliwo;
		this.stan= JAZDA;
		this.M = M;
		rand=new Random();
	}
	
	public void run()
	{
		while(true)
		{
			if(stan== MIASTO)
			{
				if(rand.nextInt(2)==1)
				{
					stan=START;
					paliwo=TANKUJ;
					System.out.println("Oczekiwanie na klienta "+numer);
					stan= M.start(numer);
					
				} else
				{
					System.out.println("Przerwa "+numer);
				}
			}
			else if(stan==START)
			{
				System.out.println("Jazda do klienta "+numer);
				stan= JAZDA;
			}
			else if(stan== JAZDA)
			{
				paliwo-=rand.nextInt(500);
				if(paliwo<=REZERWA)
				{
					stan= KONIEC_JAZDY;

				}
				else try
				{
					sleep(rand.nextInt(1000));
				}catch (Exception e){}
			}
			else if(stan== KONIEC_JAZDY)
			{
				System.out.println("Wysadzenie klienta "+numer+" ilosc paliwa "+paliwo);
				stan= M.laduj();
				if(stan== KONIEC_JAZDY)
				{
					paliwo-=rand.nextInt(500);
					System.out.println("REZERWA "+paliwo);
					if(paliwo<=0) stan = AWARIA;
				}
			}
			else if(stan== AWARIA)
			{
				System.out.println("Awaria auta "+numer);
				M.zmniejsz();
			}
			else if(stan== KRADZIEZ)
			{
				System.out.println("Kradzież w aucie "+numer);
				M.zmniejsz();
			}
			else if(stan== WYPADEK)
			{
				System.out.println("Wypadek auta "+numer);
				M.zmniejsz();
			}
		}
	}
}
//Patryk Kalinowski 80224