import java.util.concurrent.Semaphore ;
public class Filozofnr1 extends Thread
{
	public void run ( )
	{
		while ( true )
		{
			System.out.println ( "My�l� � " + mojNum) ;
			try
			{
				Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
			}
			catch ( InterruptedException e ) { }
			widelec [mojNum].acquireUninterruptibly ( ) ;
			widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
			// jedzenie
			System.out.println ( "Zaczyna je�� "+mojNum) ;
			try
			{
				Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
			}
			catch ( InterruptedException e ) { }
			System.out.println ( "Ko�czy je�� "+mojNum) ;
			widelec [mojNum].release ( ) ;
			widelec [ (mojNum+1)%MAX].release ( ) ;
		}
	}
	int MAX;
	Semaphore [] widelec;
	int mojNum;
	public Filozofnr1(int nr , double mAX2, Semaphore[] widelec)
	{
		mojNum=nr;
		MAX = (int) mAX2;
		this.widelec =  widelec;
	}
}
//Patryk Kalinowski 80224