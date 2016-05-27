import java.util.Scanner;

public class Main {
	public static void live(String[] a) {		
			Scanner sc=new Scanner(System.in);
			int y=90;
			int b=1;
			int j=0;
			b=b+2;
			if(y==0) {
				y++;
			}
			while(j<2) {
				System.out.println(compute(y));
				j++;				
			}
	}
}
