package lianxi;

public class MultipleNineChart {
	public static void main (String[] args){
		MultipleNineChart m = new MultipleNineChart();
		m.printChar();		
	}
	
	void printChar(){
		for (int i = 1; i <= 9; i++){
			for (int j=1; j <= i; j++){
				System.out.println( j + "*" + i + "  ");
			}
			System.out.println("\n");
		}
	}
}
