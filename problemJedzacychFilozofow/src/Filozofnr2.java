import java.util.concurrent.Semaphore ;
public class Filozofnr2 extends Thread
{
	public void run ( )
	{
		while ( true )
		{
			System.out.println ( "Myœlê ¦ " + mojNum) ;
			try
			{
				Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
			}
			catch ( InterruptedException e ) { }
			if (mojNum == 0)
			{
				widelec[(mojNum+1)%MAX].acquireUninterruptibly( );
				widelec[mojNum].acquireUninterruptibly();
			}
			else
			{
				widelec[mojNum].acquireUninterruptibly();
				widelec[(mojNum+1)%MAX].acquireUninterruptibly();
			}
			System.out.println("Zaczyna jeœæ "+mojNum);
			try
			{
				Thread.sleep((long)(3000 * Math.random())) ;
			}
			catch ( InterruptedException e ){ }
			System.out.println ( "Koñczy jeœæ "+mojNum) ;
			widelec [mojNum].release( ) ;
			widelec [(mojNum+1)%MAX].release( ) ;
		}
	}
	int MAX;
	Semaphore [] widelec;
	int mojNum;
	public Filozofnr2(int nr , double mAX2, Semaphore[] widelec)
	{
		mojNum=nr;
		MAX = (int) mAX2;
		this.widelec =  widelec;
	}
}
//Patryk Kalinowski 80224