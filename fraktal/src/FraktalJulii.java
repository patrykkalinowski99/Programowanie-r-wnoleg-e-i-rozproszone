import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
public class FraktalJulii extends Thread
{
	public void run()
	{
		int start=0;
		int stop=0;
		if (FL == 0)
		{
			start = 0;
			stop = (Res/4);
		}
		else if (FL == 1)
		{
			start = (Res/4);
			stop = (Res/4)*2;
		}
		else if (FL == 2)
		{
			start = (Res/4)*2;
			stop = (Res/4)*3;
		}
		else if (FL == 3)
		{
			start = (Res/4)*3;
			stop = Res;
		}
		for (int i = start; i < stop; i++)
		{
			for (int j = 0; j < Res; j++) {
				double Cr = (4.0 * i - (2 * Res))/Res;
				double Ci = (4.0 * j -(2 * Res))/Res;
				double Zr = Cr;
				double Zi = Ci;
				int k = 0;
				while (Zr * Zr + Zi * Zi <4.0 && k < CO) {
					double Nr = X + (Zr*Zr - Zi*Zi);
					double Ni = Y - (Zr*Zi + Zr*Zi);
					Zr = Nr;
					Zi = Ni;
					k++;
				}
				setImg[i][j] = k;
			}
		}
	}
	static final double Y = 0.2897;
	static final double X = -0.1;
	final static int Res = 9000; //resolution
	final static int CO = 100;
	static int[][] setImg = new int[Res][Res];
	public static void main(String[] args) throws Exception
	{
		long sT = System.currentTimeMillis();//start timera
		FraktalJulii watek0 = new FraktalJulii(0);
		FraktalJulii watek1 = new FraktalJulii(1);
		FraktalJulii thread2 = new FraktalJulii(2);
		FraktalJulii thread3 = new FraktalJulii(3);
		watek0.start();
		watek1.start();
		thread2.start();
		thread3.start();
		watek0.join();
		watek1.join();
		thread2.join();
		thread3.join();
		long eT = System.currentTimeMillis();//koniec timera
		System.out.println("Zajê³o " + (eT - sT) + " millisekund");
		BufferedImage img = new BufferedImage(Res, Res, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i< Res; i++)
		{
			for (int j = 0; j < Res; j++)
			{
				int k = setImg[i][j];
				
				float level;
				if (k < CO) {
					level = (float)k/ CO;
				}
				else {
					level = 0;
				}
				Color c = new Color(0, level, 0);
				img.setRGB(i,j,c.getRGB());				
			}
		}
		ImageIO.write(img, "png", new File("mandelbrot.png"));
	}
	int FL;
	public FraktalJulii(int FL)
	{
		this.FL = FL;
	}
}
//Patryk Kalinowski 80224