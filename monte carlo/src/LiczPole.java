package carlo;
import java.util.Random;
public class LiczPole extends Thread
{
			private Random random;
		   	int thr = 0, suc = 0;
		   	double x = 0, y = 0;
		   	public LiczPole()
			{
			    this.random = new Random();
		   	}
		   	public void run()
			{
		   		while(true)
				{
					try
					{
						 Thread.sleep(100);
					   	 for (int i = 0; i < 10000000 ; i++)
					   	 {
					   	    x = Math.random();
					   	    y = Math.random();
					   	    thr++;
					   	    if ( x*x + y*y <= 1 )
					   	       suc++;
					   	 }
					   	 double PI = 4*((double)suc / (double)thr);
					   	 System.out.println("Przybliżone pole wynosi: " + PI);
					   	}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
		   	}
}
//Patryk Kalinowski 80224