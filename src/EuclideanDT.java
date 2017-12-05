import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EuclideanDT {
	
	private double[][] zeroFramedArray;
	private double neighborArray[];
	private int numRows;
	private int numCols;
	private int minVal;
	private int maxVal;
	private double newMin;
	private double newMax;
	private double sqroot2;
	private String inFile; // pointer to args[0]
	private String outFile; // pointer to args[1]
	private String outFile2; // pointer to args[2]
	
	public EuclideanDT(String inFile, String outFile, String outFile2) throws FileNotFoundException {  // Constructor

		this.inFile = inFile;
		this.outFile = outFile;
		this.outFile2 = outFile2;
		
		Scanner dataFile = new Scanner(new File(inFile));
		
		numRows = dataFile.nextInt();
		numCols = dataFile.nextInt();
		minVal = dataFile.nextInt();
		maxVal = dataFile.nextInt();
		
		dataFile.close(); // input file closed
		
		zeroFramedArray = new double[numRows+2][numCols+2];
		
		neighborArray = new double[5];
		sqroot2 = Math.sqrt(2);
		newMin = 0;
		newMax = 0;
	
	}
	
	public void loadImage() throws FileNotFoundException {
		
		Scanner dataFile = new Scanner(new File(inFile));
		
		dataFile.nextLine();	// skip header line
		
		for (int r = 1; r <= numRows; r++) {
			for (int c = 1; c <= numCols; c++) {
				zeroFramedArray[r][c] = (double) (dataFile.nextInt());
			}
		}
		
		dataFile.close(); 	// input file closed	
	}
	
	void EuclideanDT_Pass1() {	
		for (int r = 1; r <= numRows; r++) {
			for (int c = 1; c <= numCols; c++) {
			
				if (zeroFramedArray[r][c] > 0) {
					
					neighborArray[0] = zeroFramedArray[r-1][c-1] + sqroot2;
					neighborArray[1] = zeroFramedArray[r-1][c] + 1.0;
					neighborArray[2] = zeroFramedArray[r-1][c+1] + sqroot2;
					neighborArray[3] = zeroFramedArray[r][c-1] + 1.0;
										
					zeroFramedArray[r][c] = findMin(neighborArray, 4);	
				}
			}
		}			
	}
	
	void EuclideanDT_Pass2() {
		for (int r = numRows; r >= 1; r--) {
			for (int c = numCols; c >= 1; c--) {
				
				if (zeroFramedArray[r][c] > 0) {
					
					neighborArray[0] = zeroFramedArray[r][c];
					neighborArray[1] = zeroFramedArray[r][c+1] + 1.0;
					neighborArray[2] = zeroFramedArray[r+1][c-1] + sqroot2;
					neighborArray[3] = zeroFramedArray[r+1][c] + 1.0;
					neighborArray[4] = zeroFramedArray[r+1][c+1] + sqroot2;
					
					zeroFramedArray[r][c] = findMin(neighborArray, 5);
					
					if(newMin > zeroFramedArray[r][c]) newMin = zeroFramedArray[r][c];
					if(newMax < zeroFramedArray[r][c]) newMax = zeroFramedArray[r][c];
				}
			}
		}	
	}
	
	void printFullImage() throws IOException {
		
		PrintWriter out = new PrintWriter(new FileWriter(outFile));
		
		out.println(numRows + " " + numCols + " " + (int)newMin + " " + (int)newMax);
		
		for (int r = 1; r <= numRows; r++) {
			for (int c = 1; c <= numCols; c++) {
				out.print((int)zeroFramedArray[r][c] + " "); // cast to int for better output
			}
			out.println();
		}
				
		out.close();
		return;
	}
	
	double findMin(double[] neighborArray2, int size) {
		
		double min = neighborArray2[0];
		
		for (int i = 1; i < size; i++) {
			if (neighborArray2[i] < min) min = neighborArray2[i];
		}
		
		return min;
	}
	
	void prettyPrintArray(int num) throws IOException {
		
		PrintWriter out2 = new PrintWriter(new FileWriter(outFile2, true));
		
		out2.println("Pretty Print After Pass " + num);
		out2.println("=========================");
		
		for (int r = 1; r <= numRows; r++) {
			for (int c = 1; c <= numCols; c++) {
				if (zeroFramedArray[r][c] > 0) {
					out2.print((int)((zeroFramedArray[r][c]) + 0.5) + " ");		
				}
				else out2.print("  ");
			}
			out2.println();
		}
		
		out2.println();
		out2.println();
		out2.close();	// close output file
		return;
	}
	
}
