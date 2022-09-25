public class Polynomial {
	double[] poly;
	
	public Polynomial(){
		poly = new double[1];
		poly[0] = 0;
	}
	public Polynomial(double[] array) {
		int size = array.length;
		poly = new double[size];
		for (int i = 0; i<size; i++) {
			poly[i] = array[i];
		}
	}
	public Polynomial add(Polynomial apoly) {
		int a = poly.length;
		int b = apoly.poly.length;
		int mini = Math.min(a,b);
		int maxi = Math.max(a,b);
		
		double[] temp = new double[maxi];
		
		for (int i = 0; i<mini; i++) {
			 temp[i] = apoly.poly[i]+poly[i];
		}
		
		if (a>b) {
			for (int j = mini; j<maxi; j++) {
				temp[j] = poly[j];
			}
		}
		else {
			for (int j = mini;j<maxi;j++) {
				temp[j] = apoly.poly[j];
			}
		}
		
		Polynomial nice = new Polynomial(temp);
		return nice;
	}
	
	public double evaluate(double num) {
		int len = poly.length;
		double result = 0.0;
		for (int i = 0; i<len; i++) {
			double current = 0.0;
			current = Math.pow(num, i);
			current = poly[i]*current;
			result += current;
		}
		return result;
	}
	
	public boolean hasRoot(double num) {
		double result = this.evaluate(num);
		if (result == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}