import java.awt.*;
import java.util.Scanner;

public class MainClass
{
	static Scanner in = new Scanner(System.in);

	// Numero di pixel di altezza (righe)
	static int altezza = 400;

	// Numero di pixel di larghezza (colonne)
	static int larghezza = 400;

	// Numero di pixel di larghezza (colonne)
	static int fps = 10;

	// Creo una finestra con un titolo 
	static Finestra finestra = new Finestra("Finestra", larghezza, altezza, fps);

	// Creo una matrice di pixel
	//static Color[][] immagine = new Color[altezza][larghezza];

	public static void main(String[] args) 
	{
		while(true)
		{
			stampaMenu();
			switch (Integer.parseInt(in.nextLine())) 
			{
			case 1: 
				es1();
				break;

			case 2:
				es2();
				break;

			case 3:
				es3();
				break;

			case 4:
				es4();
				break;

			case 5:
				es5();
				break;

			default:
				System.out.println("Scelta errata, riporva!");
			}
		}
	}

	static void stampaMenu()
	{
		String s = 
				"1 - Rinomina con nome esesercizio 1\n" +
						"2 - Rinomina con nome esesercizio 2\n" +
						"3 - Rinomina con nome esesercizio 3\n" +
						"4 - Rinomina con nome esesercizio 4\n" +
						"5 - Rinomina con nome esesercizio 5\n";
		System.out.println(s);
	}

	// Esercizio di prova
	public static void prova()
	{
		// 255 fotogrammi
		for(int k = 0; k < 255; k++)
		{
			// creo un nuovo fotogramma
			Color[][] immagine = new Color[altezza][larghezza];
			// righe del fotogramma
			for(int i = 0; i < immagine.length; i++) 
			{
				// colonne del fotogramma
				for(int j = 0; j < immagine[0].length; j++) 
				{
					// colore del fotogramma
					immagine[i][j] = new Color(k,k,k);		
				}
			}
			// Quando finisco di disegnare il fotogramma lo metto in coda per il disegno
			finestra.disegna(immagine);
		}
	}
	
	public static void es1()
	{
		System.out.println("Esercizio 1");
	}

	public static void es2()
	{
		System.out.println("Esercizio 2");
	}

	public static void es3()
	{
		System.out.println("Esercizio 3");

	}

	public static void es4()
	{
		System.out.println("Esercizio 4");
	}

	public static void es5()
	{
		System.out.println("Esercizio 5");
	}
}