package complex;

/**
 * Klasa z podtsawowymi działaniami na liczbach zespolonych
 */

public class Zespolona {
	
	/**
	 * Częśc całkowita liczby zespoloniej
	 */
	private double re;
	
	/**
	 * Częśc urojona liczby zespolonej
	 */
	private double im;
	
	/**
	 * Konstrukto tworzący liczbe zespolona 0 + i0
	 */
	public Zespolona() {
		this.re = 0;
		this.im = 0;
	}
	
	/**
	 * Konstruktor tworzący liczbę zespoloną re + i*im
	 * @param re Częśc całkowita liczby zespoloniej
	 * @param im Częśc urojona liczby zespolonej
	 */
	public Zespolona(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	/**
	 * Funckja do przedstawienia liczby zespolonej jako łańcuch tekstowy
	 * @return String - Liczba w postaci a+bi
	 */
	@Override
	public String toString() {
		String ret = String.valueOf(this.re);
		ret = (im < 0) ? ret + String.valueOf(this.im) + "i" : ret + "+" + String.valueOf(this.im) + "i";
		return ret;
	}
	
	/**
	 * Sprawdza czy dwie liczby zespolone są sobie równe
	 * @param a Liczba zespolona do porównania
	 * @return boolean - True jeżeli re i im są równe, False przeciwnie
	 */
	public boolean equals(Zespolona a) {
		if(this.re == a.re && this.im == a.im) return true;
		else return false;
	}
	
	/**
	 * Funkcja do tworzenia kopi liczb zespolonych
	 * @param a Liczba do skopiowania
	 * @return Zespolona - Kopia liczby zespolonej
	 */
	public static Zespolona copyOf(Zespolona a) {
		return new Zespolona(a.re, a.im);
	}
	
	/**
	 * Funckja zwracająca hashCode liczby 
	 * @return int - HashCode liczby
	 */
	@Override
	public int hashCode() {
		long ret = 0;
		ret = (int)(this.re*this.im + this.re*3 + this.im*5);
		return (int)ret;
	}
	
	/**
	 * Funkcja do dodawania dwóch liczb zespolonych
	 * @param a Liczba z lewej stornie dodawania
	 * @param b Liczba z prawej stronie dodawania
	 * @return Zespolona - Wynik dodawania
	 */
	public static Zespolona add(Zespolona a, Zespolona b) {
		return new Zespolona(a.re + b.re, a.im + b.im);
	}
	
	/**
	 * Funkcja do odejmowania dwóch liczb zespolonych
	 * @param a Liczba z lewej stornie odejmowania
	 * @param b Liczba z prawej stronie odejmowania
	 * @return Zespolona - Wynik odejmowania
	 */
	public static Zespolona subtract(Zespolona a, Zespolona b) {
		return new Zespolona(a.re - b.re, a.im - b.im);
	}
	
	/**
	 * Funkcja do mnorzenia dwóch liczb zespolonych
	 * @param a Liczba z lewej stornie mnorzenia
	 * @param b Liczba z prawej stronie mnorzenia
	 * @return Zespolona - Wynik mnorzenia
	 */
	public static Zespolona multiply(Zespolona a, Zespolona b) {
		return new Zespolona(a.re*b.re - a.im*b.im, a.re*b.im + a.im*b.re);
	}
	
	/**
	 * Funkcja zwracająca moduł liczby zespolonej
	 * @return double - Moduł liczby zespolonej
	 */
	public double modulo() {
		return Math.sqrt(this.re*this.re + this.im*this.im);
	}
	
	/**
	 * Funkcja do porównania dwóch liczb zespolonych
	 * @param a Liczba zespolona do porównania
	 * @return int - 0 jeżeli równe, -1 jeżeli podana liczba jest większa, 1 jeżeli podana liczba jest mniejsza
	 */
	public int compareTo(Zespolona a) {
		if(this.re == a.re) {
			if(this.im == a.im) return 0;
			else if(this.im < a.im) return -1;
			else return 1;
		} else if(this.re < a.re) return -1;
		else return 1;
	}
	
	/**
	 * Funckja zwracająca argument liczby zespolonej od -PI/2 do 3/2*PI
	 * @return double - Argument liczby zespolonej
	 * @throws ArithmeticException Gdy moduł liczby jest zerowy
	 */
	public double arg() throws ArithmeticException{
		double z = this.modulo();
		if(z == 0) throw new ArithmeticException("Complex number cannot be 0 + 0i");
		double sin = this.im/this.modulo();
		double cos = this.re/this.modulo();
		if(cos > 0) return Math.asin(sin);
		else {
			if(sin > 0) return Math.acos(cos);
			else return Math.PI + Math.asin(-sin);
		}
	}
	
	public static void main(String arg[]) {
		Zespolona a = new Zespolona();
		Zespolona b = new Zespolona(1, -1);
		System.out.printf("%s \n%s\n", a.toString(), b.toString());
		
		// Test equals
		System.out.println("a == b ? \n" + a.equals(b));
		
		// Test dodawania
		System.out.println("a + b = \n" + Zespolona.add(a, b).toString());
		
		// Test odejmowania
		System.out.println("a - b = \n" + Zespolona.subtract(a,b).toString());
		
		// Test mnorzenia
		System.out.println("a * b = \n" + Zespolona.multiply(a, b).toString());
		
		// Test modulo
		System.out.println("|a| = " + a.modulo());
		System.out.println("|b| = " + b.modulo());
		
		// Test arg
		System.out.println("arg(b) = " + b.arg());
		
		b = new Zespolona(1,1);
		System.out.println(b.arg());
		
		b = new Zespolona(-1,1);
		System.out.println(b.arg());
		
		b = new Zespolona(-1,-1);
		System.out.println(b.arg());
		
		try {
			System.out.println("Test ArithmeticException:");
			System.out.println(a.arg());
		} catch(ArithmeticException e) {
			System.out.println("Modulo is 0");
			e.printStackTrace();
		}
	}

}
