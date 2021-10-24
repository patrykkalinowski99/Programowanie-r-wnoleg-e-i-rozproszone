package symulator_taxi;
public class miasto
{
	static int MIASTO =1;
	static int START=2;
	static int JAZDA =3;
	static int KONIEC_JAZDY =4;
	static int AWARIA =5;
	
	int ILOSC_DROG;
	int ILOSC_MIEJSC_W_TAXI;
	int ILOSC_TAXI;
	miasto(int ILOSC_DROG, int ILOSC_TAXI)
	{
		this.ILOSC_DROG = ILOSC_DROG;
		this.ILOSC_TAXI = ILOSC_TAXI;
		this.ILOSC_MIEJSC_W_TAXI =0;
	}
	synchronized int start(int numer)
	{
		ILOSC_MIEJSC_W_TAXI--;
		System.out.println("Oczekiwanie na klienta "+numer);
		return START;
	}
	synchronized int  laduj()
	{
		try { Thread.currentThread().sleep(1000);} catch(Exception ie) { }
		if(ILOSC_MIEJSC_W_TAXI < ILOSC_DROG)
		{
			ILOSC_MIEJSC_W_TAXI++;
			System.out.println("Pozwolenie na zmiane czesci miasta "+ ILOSC_MIEJSC_W_TAXI);
			return MIASTO;
		} else {return KONIEC_JAZDY;}

	}
	synchronized void zmniejsz()
	{
		ILOSC_TAXI--;
		System.out.println("ZABILEM");
		if(ILOSC_TAXI == ILOSC_DROG) System.out.println("ILOSC TAXI JEST TAKA SAMA JAK ILOSC ULIC");
	}
}
//Patryk Kalinowski 80224