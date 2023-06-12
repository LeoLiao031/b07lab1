import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;


public class Driver {
	public static void main(String [] args) {
		
		//Polynomial p = new Polynomial(); System.out.println(p.evaluate(3));
		double [] c1 = {6,2,3,5};
		int [] d1 = {0,2,3,4};
		Polynomial p1 = new Polynomial(c1, d1 );
		
		double [] c2 = {2,2,2,2,2};		
		int [] d2 = {0,1,2,3,4};
		Polynomial p2 = new Polynomial(c2, d2); 
	
		double [] c3 = {1,2,3,4,5};		
		int [] d3 = {0,1,2,3,4};
		Polynomial p3 = new Polynomial(c3, d3); 

		double [] c4 = {1,3,2,5,4};		
		int [] d4 = {0,2,1,4,3};
		Polynomial p4 = new Polynomial(c4, d4); 

		
		double [] c5 = {5,4};		
		int [] d5 = {4,3};
		Polynomial p5 = new Polynomial(c5, d5); 
		
		Polynomial s = p1.add(p3); 
		Polynomial s2 = p1.add(p4); 
		Polynomial s3 = p1.add(p5); 
				
		//s.printing();
		//s2.printing();
		//s3.printing();
		System.out.println("s(2) = " + s3.evaluate(2)); 
        if(s3.hasRoot(1))
        System.out.println("1 is a root of s"); 
        else
        System.out.println("1 is not a root of s");

        Polynomial m1 = p1.multiply(p5);
        m1.printing();
        
        System.out.println("File testing...");        
        File file = new File("test.txt");
        Polynomial text = new Polynomial(file);
        text.printing();
        
        
        p1.saveToFile("Tester.txt");
        
        System.out.println("File testing...");        
        File file2 = new File("Tester.txt");
        Polynomial text2 = new Polynomial(file2);
        text2.printing();
        
	} 
}