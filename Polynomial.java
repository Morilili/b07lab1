import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import javax.swing.text.PlainDocument;

public class Polynomial {
	double[] coe;
	int[] expo;

	public Polynomial(){
		coe = new double[1];
		coe[0] = 0;
		expo = new int[1];
		coe[0] = 0;
	}
	public Polynomial(double[] ncoe, int[] nexpo) {
		int size = ncoe.length;
		coe = new double[size];
		expo = new int[size];
		for (int i = 0; i<size; i++) {	
			coe[i] = ncoe[i];
			expo[i] = nexpo[i];
		}
	}

	public Polynomial(File f) {
		try{
			Scanner read = new Scanner(f);
			String data = read.nextLine();
			int datalen = data.length();
			for (int index = 0; index < datalen; index++) {
				
				char c = data.charAt(index);
				if (c == '-') {
					data = data.substring(0, index) + "@" + data.substring(index, datalen);
					index++;
					datalen++;
				}
			}
			String[] split = data.split("\\+|\\@", 0);

			int size = split.length;
			coe = new double[size];
			expo = new int[size];
			for (int i = 0; i < size; i++) {
				int len = split[i].length();
				int index = split[i].indexOf("x");
				if (index == -1) {
					Double d = Double.parseDouble(split[i]);
					coe[i] = d;
					expo[i] = 0;
				}
				else {
					
					Double dd = Double.parseDouble(split[i].substring(0, index));
					int ee = Integer.parseInt(split[i].substring(index + 1, len));
					coe[i] = dd;
					expo[i] = ee;
				}
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		}
	} 
	
	public int highest(int[] array) {
		int size = array.length;
		int highest = 0;
		for (int i = 0; i < size; i++) {
			if (array[i] > highest) {
				highest = array[i];
			}
		}
		return highest;
	}

	public Polynomial add(Polynomial newp) {
		int highog = highest(expo);
		int highnew = highest(newp.expo);
		int sizeog = expo.length;
		int sizenew = newp.expo.length;
		

		int maxi = Math.max(highog, highnew) +1;
		
		
		double[] tempcoe = new double[maxi];
		int[] tempexpo = new int[maxi];

		for (int init = 0; init < sizeog; init++) {
			tempcoe[init] = coe[init];
			tempexpo[init] = expo[init];
		}
		
		for (int i = 0; i < sizeog; i++) {
			for (int j = 0; j < sizenew; j++) {
				if (tempexpo[i] == newp.expo[j]) {
					double temp = tempcoe[i] + newp.coe[j];
					tempcoe[i] = temp;
					newp.expo[j] = 0;
					newp.coe[j] = 0;
				}
			}
		}
		
		for (int iter = 0; iter < sizenew; iter++) {
			
			if (newp.expo[iter] == 0 & newp.coe[iter] == 0) {
				;
			}
			else {
				tempcoe[sizeog] = newp.coe[iter];
				tempexpo[sizeog] = newp.expo[iter];
				sizeog++;
			}
		}
		
		Polynomial re = new Polynomial(tempcoe, tempexpo);
		return re;
	}
	
	public double evaluate(double num) {
		int len = expo.length;
		double result = 0.0;
		for (int i = 0; i<len; i++) {
			// double current = 0.0;
			// current = Math.pow(num, i);
			// current = poly[i]*current;
			// result += current;
			double current = 0.0;
			current = Math.pow(num, expo[i]);
			current = coe[i] * current;
			result += current;
		}
		return result;
	}
	
	public boolean hasRoot(double num) {
		double result = this.evaluate(num);
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

	public Polynomial multiply(Polynomial newp) {

		int lenog = expo.length;
		int lennewp = newp.expo.length;
		int tlen = (lenog * lennewp);
		int counter = 0;

		double[] tempcoe = new double[tlen];
		int[] tempexpo = new int[tlen];

		for (int i = 0; i < lenog; i++) {
			for (int j = 0; j < lennewp; j++) {
				double currcoe = coe[i] * newp.coe[j];
				int currexpo = expo[i] + newp.expo[j];
				tempcoe[counter] = currcoe;
				tempexpo[counter] = currexpo;
				counter++;
			}
		}

		for (int checkdup = 0; checkdup < tlen; checkdup++) {
			for (int iter = checkdup + 1; iter < tlen; iter++) {
				if (tempexpo[checkdup] == tempexpo[iter]) {
					tempcoe[checkdup] += tempcoe[iter];
					tempcoe[iter] = 0;
					tempexpo[iter] = 0;
				}
			}
		}
		int countofNumbers = 0;
		for (int last = 0; last < tlen; last++) {
			if (tempcoe[last] != 0 & tempexpo[last] != 0)
				countofNumbers++;
		}

		double[] actualcoe = new double[countofNumbers];
		int[] actualexpo = new int[countofNumbers];

		int indexingofactual = 0;
		for (int actuallast = 0; actuallast < tlen; actuallast++) {
			if (tempcoe[actuallast] != 0 & tempexpo[actuallast] != 0) {
				actualcoe[indexingofactual] = tempcoe[actuallast];
				actualexpo[indexingofactual] = tempexpo[actuallast];
				indexingofactual++;
			}
		}
		Polynomial re = new Polynomial(actualcoe, actualexpo);
		return re;
	}

	public File saveToFile(String fname) {
		String poly = "";
		int lenpoly = expo.length;
		for (int i = 0; i < lenpoly; i++) {
			double currcoe = coe[i];
			int currexpo = expo[i];
			String ncoe = Double.toString(currcoe);
			String nexpo = Integer.toString(currexpo);

			String first = ncoe.substring(0, 1);
			if (i != 0 & first.equals("-") == false) {
				ncoe = "+" + ncoe;
			}
			String curr = ncoe + "x" + nexpo;
			poly += curr;
		}
		
		File myf = new File(fname);
		try {
			
			if (myf.createNewFile()) {
				FileWriter out = new FileWriter(fname);
				out.write(poly);
				out.close();
			}

		} 
		catch (IOException e) {
			System.out.println("An error occurred.");

		}
		return myf;
	}
}