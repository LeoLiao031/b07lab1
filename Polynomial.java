import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class Polynomial{
	
	double [] coefficients;
	int [] exponents;
	
	public Polynomial(){
		
		coefficients = new double[0];
		exponents = new int[0];
		
	}
	
	public Polynomial(double [] coeff_array, int [] exponent) {
		
		coefficients = coeff_array;
		exponents = exponent;
		
	}

	public Polynomial(File file) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        String polynomialString = reader.readLine();
	        reader.close();

	        String[] terms = polynomialString.split("(?=[-+])");
	        double[] coefficients = new double[terms.length];
	        int[] exponents = new int[terms.length];

	        for (int i = 0; i < terms.length; i++) {
	            String term = terms[i];
	            int coefficientEndIndex = term.indexOf('x');

	            if (coefficientEndIndex == -1) {
	                coefficients[i] = Double.parseDouble(term);
	                exponents[i] = 0;
	            } else {
	                String coefficientString = term.substring(0, coefficientEndIndex);
	                String exponentString = term.substring(coefficientEndIndex + 1);
	                coefficients[i] = Double.parseDouble(coefficientString);

	                if (exponentString.isEmpty()) {
	                    exponents[i] = 1;
	                } else {
	                    exponents[i] = Integer.parseInt(exponentString);
	                }
	            }
	        }

	        this.coefficients = coefficients;
	        this.exponents = exponents;
	    } catch (FileNotFoundException error) {
	        System.out.println("File not found: " + error.getMessage());
	        this.coefficients = new double[0];
	        this.exponents = new int[0];
	    } catch (IOException error) {
	        System.out.println("Error reading file: " + error.getMessage());
	        this.coefficients = new double[0];
	        this.exponents = new int[0];
	    }
	}


	
	public Polynomial add(Polynomial polynomial) {
		
		// create a HashMap to store key value pair where key is the exponent and value is the corresponding coefficient
		HashMap<Integer, Double> new_polynomial = new HashMap<Integer, Double>();
		
		// now fill the HashMap with values from the two polynomials
		for(int i = 0; i < exponents.length; i++) {
			new_polynomial.put(exponents[i], coefficients[i]);
		}
		
		for(int x = 0; x < polynomial.exponents.length; x++) {
			new_polynomial.put(polynomial.exponents[x], new_polynomial.getOrDefault(polynomial.exponents[x], 0.0) + polynomial.coefficients[x]);
		}
		
		// these arrays will hold the values from the HashMap as we can't call the constructor with a HashMap
		double [] new_coefficients = new double [new_polynomial.size()];
		int [] new_exponents = new int [new_polynomial.size()];
		
		// now create a new Polynomial using the HashMaps values
		int counter = 0;
		for (Integer key : new_polynomial.keySet()) {
			new_coefficients[counter] = new_polynomial.get(key);
			new_exponents[counter] = key;
			counter++;
		}


		return new Polynomial(new_coefficients, new_exponents);
		
	}
	
	public double evaluate(double x) {
		double result = 0;

		// loop through the array and apply the formula coeff * (x)^exponent
		for (int i =0; i < coefficients.length; i++) {
			result += this.coefficients[i] * Math.pow(x, exponents[i]);
		}
		
		return result;
	}
	
	public boolean hasRoot(double x) {
		// has root if evaluate returns 0
		return evaluate(x) == 0;
	}
	
	public Polynomial multiply(Polynomial polynomial) {
		Polynomial multiplied = new Polynomial();
		Polynomial term = new Polynomial();
		
		// using for loops we can apply FOIL to multiply the polynomials
		int counter = 0;
		for(int x = 0; x < exponents.length; x++) {
			for(int y = 0; y < polynomial.exponents.length; y++) {
				double [] coefficient = {coefficients[x] * polynomial.coefficients[y]};
				int [] exponent = {exponents[x] + polynomial.exponents[y]};
				term = new Polynomial(coefficient, exponent);
				multiplied = multiplied.add(term);
			}
			
		}
		return multiplied;
	}
	
	public void saveToFile(String filename) {
	    if (coefficients == null || exponents == null || coefficients.length != exponents.length) {
	        System.out.println("Invalid polynomial data. Cannot save to file.");
	        return;
	    }

	    try {
	        File new_file = new File(filename);
	        BufferedWriter writer = new BufferedWriter(new FileWriter(new_file));

	        StringBuilder polynomialBuilder = new StringBuilder();

	        for (int i = 0; i < coefficients.length; i++) {
	            double coefficient = coefficients[i];
	            int exponent = exponents[i];

	            if (exponent == 0) {
	                polynomialBuilder.append(coefficient);
	            } else {
	                if (coefficient != 1 && coefficient != -1) {
	                    polynomialBuilder.append(coefficient);
	                } else if (coefficient == -1) {
	                    polynomialBuilder.append("-");
	                }

	                polynomialBuilder.append("x");

	                if (exponent != 1) {
	                    polynomialBuilder.append(exponent);
	                }
	            }

	            if (i < coefficients.length - 1) {
	                polynomialBuilder.append("+");
	            }
	        }

	        writer.write(polynomialBuilder.toString());
	        writer.close();
	        System.out.println("Polynomial saved to file successfully.");
	    } catch (FileNotFoundException error) {
	        System.out.println("File not found: " + error.getMessage());
	    } catch (IOException error) {
	        System.out.println("Error writing to file: " + error.getMessage());
	    }
	}


	
	public void printing() {
        for (int i=0;i<coefficients.length;i++) {
            System.out.println(coefficients[i]+"");
        }
        System.out.println("\n");

        for (int i=0;i<exponents.length;i++) {
            System.out.println(exponents[i]+"");
        }
        System.out.println("\n");

    }
}