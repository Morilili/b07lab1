import java.util.Arrays;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException; 

public class Driver { 
  public static void main(String[] args) {

    double[] coe1 = { 0, 1, 1, 5 };
    int[] expo1 = { 0, 4, 2, 1 };
    Polynomial p1 = new Polynomial(coe1, expo1);
    double[] coe2 = { 0, -2, 3, -3, -9 };
    int[] expo2 = { 2, 3, 0, 5, 6 };
    Polynomial p2 = new Polynomial(coe2, expo2);
    Polynomial s = p1.add(p2);
    System.out.println(Arrays.toString(s.coe));
    System.out.println(Arrays.toString(s.expo));
    System.out.println(s.evaluate(2));
    if (s.hasRoot(0))
      System.out.println("1 is a root of s");
    else
      System.out.println("1 is not a root of s");
    double[] coe3 = { 5,-4};
    int[] expo3 = { 2, 3 };
    Polynomial p3 = new Polynomial(coe3, expo3);
    double[] coe4 = {-3, -9 };
    int[] expo4 = { 4, 3 };
    Polynomial p4 = new Polynomial(coe4, expo4);
    Polynomial t = p3.multiply(p4);
    System.out.println(Arrays.toString(t.coe));
    System.out.println(Arrays.toString(t.expo));
    File f = new File("a.txt");
    Polynomial p5 = new Polynomial(f);
    System.out.println(Arrays.toString(p5.coe));
    System.out.println(Arrays.toString(p5.expo));
    System.out.println(p5.saveToFile("b.txt"));

  }
 
 
} 