package calkowanie;
class m_prostokatow extends Thread{
	double ai, bi, n, wynik_p;
	public m_prostokatow(double a, double b, double n)
	{
		this.ai = a;
		this.bi = b;
		this.n = n;
	}
	public void run()
	{
		main funkcja = new main();
		double dlt, wynik = 0;
		dlt = (bi - ai) / n;
		for (int i = 0; i < n; i++)
		{
			wynik += dlt * funkcja.fx(ai + dlt*(i + 0.5));
		}
		wynik_p = wynik;
	}
}
//Patryk Kalinowski 80224