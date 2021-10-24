package calkowanie;
class m_simpsona extends Thread
{
	public double ai, bi, n, wynik;
	public m_simpsona(double a, double b, double n)
	{
		this.ai = a;
		this.bi = b;
		this.n = n;
	}
	public void run()
	{
		main funkcja = new main();
		double dx, calka, y, z;
		dx = (bi - ai) / (double)n;
		 
		calka = 0;
		y = 0;
		for (int i=1; i < n; i++)
		{
			z = ai + i*dx;
			y += funkcja.fx(z - dx / 2);
			calka += funkcja.fx(z);
		}
		y += funkcja.fx(bi - dx / 2);
		calka = (dx/6) * (funkcja.fx(ai) + funkcja.fx(bi) + 2*calka + 4*y);
		wynik = calka;
	}
}
//Patryk Kalinowski 80224