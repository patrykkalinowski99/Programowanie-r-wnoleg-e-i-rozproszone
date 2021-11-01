public class Czasomierz extends Thread
{
    int s = 0;
    int m = 0;
    int h = 0;
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(1000);
                s++;
                if(s == 60)
                {
                    m++;
                    s = 0;
                }
                if(m == 60)
                {
                    h++;
                    m = 0;
                }
                System.out.println(h + " : " + m + " : " +s);
            }catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }
}
//Patryk Kalinowski 80224