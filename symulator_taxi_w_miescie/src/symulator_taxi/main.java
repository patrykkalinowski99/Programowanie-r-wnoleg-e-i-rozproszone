package symulator_taxi;
public class main
{
	static int ilosc_taxi = 10000;
	static int ilosc_ulic = 500;
	static miasto taxi;
	public static void main(String[] args)
	{
		taxi =new miasto(ilosc_ulic, ilosc_taxi);
		for(int i = 0; i< ilosc_taxi; i++) new taxi(i,1000, taxi).start();
	}
}
//Patryk Kalinowski 80224