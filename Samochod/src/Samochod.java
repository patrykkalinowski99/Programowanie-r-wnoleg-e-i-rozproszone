import java.util.Random;
public class Samochod extends Thread
{
	private boolean exit; //zmienna boolowska true/false
	private Random random;
	private String nrRej;
	private int zbiornik;
	private int paliwo;

	public Samochod(String nrRej, int zbiornik)
	{
		this.nrRej = nrRej;
	    this.zbiornik = zbiornik;
	    random = new Random();
	}
	private void tankuj(int paliwo)
	{
		if(paliwo > zbiornik + this.paliwo)
		{
            System.out.println("Przelano zbiornik auta " + nrRej);
            this.paliwo = this.zbiornik;
        }
        else
        	this.paliwo = paliwo;
	}
	private void sstart()
	{
		exit = false;
	}
	private void sstop()
	{
		exit = true;
	}
	public void run()
	{
		sstart();
        tankuj(random.nextInt(50));
        while(!exit)
        {
            try
			{
                Thread.sleep(1000); //co sekunde 1litr
				paliwo-=1;
            } catch (InterruptedException e)
			{
                e.printStackTrace();
            }
            if(paliwo < 1)
            {
                System.out.println("Koniec paliwa w samochodzie " + nrRej);
				sstop();
                break;
            }
            System.out.println("Auto nr: " + nrRej + " Poziom baku: [" + paliwo + "/" + zbiornik + "]");
        }
	}
}
//Patryk Kalinowski 80224