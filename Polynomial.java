public class Polynomial{
	
	double [] coefficients;
	
	public Polynomial(){
		
		this.coefficients = new double[0];
		
	}
	
	public Polynomial(double [] coeff_array) {
		
		this.coefficients = coeff_array;
		
	}
	
	public Polynomial add(Polynomial polynomial) {
		// find which polynomial is of largest degree for looping later
		int largest_degree = Math.max(this.coefficients.length, polynomial.coefficients.length);
		
		// create a new array to hold the new coefficients based off the largest size we calculated earlier
		double[] newCoefficients = new double[largest_degree];
		
		// loop through and create the new coefficients
		for(int i =0; i < largest_degree; i++) {
			// find the coefficients at the index but check whether it's a valid index
			double coeff1 = 0;
			if (i < coefficients.length) {
				coeff1 = coefficients[i];
			}
			double coeff2 = 0;
			if (i < polynomial.coefficients.length) {
				coeff2 = polynomial.coefficients[i];
			}
			
			// now sum and write it into the array
			newCoefficients[i] = coeff1 + coeff2;
			
		}
		
		return new Polynomial(newCoefficients);
		
	}
	
	public double evaluate(double x) {
		double result = 0;
		
		// calc the degree to use in calculations
		int degree = this.coefficients.length;
		
		for (int i =0; i < degree; i++) {
			result += this.coefficients[i] * Math.pow(x, i);
		}
		
		return result;
	}
	
	public boolean hasRoot(double x) {
		// has root if evaluate returns 0
		return evaluate(x) == 0;
	}
	
	
}