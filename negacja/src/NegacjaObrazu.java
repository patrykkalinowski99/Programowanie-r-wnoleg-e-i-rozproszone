import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class NegacjaObrazu extends Thread
{
	BufferedImage image;
	int w, h;
	public NegacjaObrazu()
	{
		try
		{
			//wczytanie obrazka barwnego
			File wejscie = new File("src\\barwny.jpg");
			image = ImageIO.read(wejscie);
			w = image.getWidth();
			h = image.getHeight();
			//podmiana poszczególnych pixeli
			for(int i = 0; i <= h-1; i++)
			{
					for(int j = 0; j <= w-1; j++)
					{
						 Color c = new Color(image.getRGB(j, i));
						 int red = (int)(c.getRed());
						 int green = (int)(c.getGreen());
						 int blue = (int)(c.getBlue());

						 int f_red = 255-red;
						 int f_green = 255-green;
						 int f_blue = 255-blue;
						 Color n_Color = new Color(f_red, f_green, f_blue);
						 image.setRGB(j,i,n_Color.getRGB());
					}
				}
			//tworzenie nowego obrazka
			File wyjscie = new File("src\\negacja.jpg");
			ImageIO.write(image, "jpg", wyjscie);
			System.out.println("Zakoñczono.");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	static public void main(String args[])
	{
		new NegacjaObrazu().run();
	}
}
//Patryk Kalinowski 80224