import java.util.Random;
import java.util.concurrent.Semaphore ;
public class Filozofnr3 extends Thread
{
	public void run ( )
	{
		while ( true )
		{
			System.out.println ( "Myœlê ¦ " + mojNum) ;
			try {
				Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
			} catch ( InterruptedException e ) { }
			int strona = losuj.nextInt ( 2 ) ;
			boolean podnioslDwaWidelce = false ;
			do
			{
				if ( strona == 0)
				{
					widelec [mojNum].acquireUninterruptibly ( ) ;
					if( ! ( widelec [ (mojNum+1)%MAX].tryAcquire ( ) ) )
					{
						widelec[mojNum].release ( ) ;
					}
					else
					{
						podnioslDwaWidelce = true ;
					}
				}
				else
				{
					widelec[(mojNum+1)%MAX].acquireUninterruptibly ( ) ;
					if ( ! (widelec[mojNum].tryAcquire ( ) ) )
					{
						widelec[(mojNum+1)%MAX].release ( ) ;
					}
					else
					{
						podnioslDwaWidelce = true ;
					}
				}
			}
			while ( podnioslDwaWidelce == false ) ;
			System.out.println ( "Zaczyna jeœæ "+mojNum) ;
			try
			{
				Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
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
	Random losuj ;
	
	public Filozofnr3(int nr , double mAX2, Semaphore[] widelec )
	{
		mojNum=nr;
		MAX = (int) mAX2;
		this.widelec =  widelec;
		losuj = new Random(mojNum) ;
	}
}
//Patryk Kalinowski 80224