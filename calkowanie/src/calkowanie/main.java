package calkowanie;
import java.util.Scanner;
public class main {
	public double fx(double x)
	{
		return (x*x)*Math.sin(x)*Math.cos(x);
	}
	public static void main(String[] args) {
		double ai = 0;
		double bi = 0;
		double n = 0;
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Poczatek przedzialu "); ai = scanner.nextDouble();
	    System.out.print("Koniec przedzialu "); bi = scanner.nextDouble();
	    System.out.print("Dokladnosc przedzialu "); n = scanner.nextDouble();
		m_prostokatow prostokat = new m_prostokatow(ai, bi, n); prostokat.start();
		m_simpsona simpson = new m_simpsona(ai, bi, n); simpson.start();
		m_trapezow trapez = new m_trapezow(ai, bi, n); trapez.start();
		try
		{
			prostokat.join(); System.out.println("Wynik dla metody prostokatow: " + prostokat.wynik_p);
			simpson.join(); System.out.println("Wynik dla metody Simpsona: " + simpson.wynik);
			trapez.join(); System.out.println("Wynik dla metody trapezow: " + trapez.wynik);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
//Patryk Kalinowski 80224