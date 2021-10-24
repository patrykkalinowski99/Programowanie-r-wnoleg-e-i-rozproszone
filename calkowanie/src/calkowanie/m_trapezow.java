package calkowanie;
class m_trapezow extends Thread{
	public double ai, bi, n, wynik;
	public m_trapezow(double a, double b, double n)
	{
		this.ai = a;
		this.bi = b;
		this.n = n;
	}
	public void run()
	{
		main f = new main();
		double dx, y = 0;
		dx = (bi - ai) / (double) n; 
		for (int i = 1; i < n; i++)
		{
			y += f.fx(ai + (i/n) * (bi - ai));
		}
		y += (f.fx(ai) + f.fx(bi))/2;
		y *= dx;
		wynik = y;
	}
}
//Patryk Kalinowski 80224