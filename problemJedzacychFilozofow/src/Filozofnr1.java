import java.util.concurrent.Semaphore ;
public class Filozofnr1 extends Thread
{
	public void run ( )
	{
		while ( true )
		{
			System.out.println ( "Myœlê ¦ " + mojNum) ;
			try
			{
				Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
			}
			catch ( InterruptedException e ) { }
			widelec [mojNum].acquireUninterruptibly ( ) ;
			widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
			// jedzenie
			System.out.println ( "Zaczyna jeœæ "+mojNum) ;
			try
			{
				Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
			}
			catch ( InterruptedException e ) { }
			System.out.println ( "Koñczy jeœæ "+mojNum) ;
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