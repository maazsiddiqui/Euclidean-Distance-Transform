
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		
		EuclideanDT eDT = new EuclideanDT(args[0], args[1], args[2]);
		eDT.loadImage();
		
		// PASS 1
		eDT.EuclideanDT_Pass1();
		eDT.prettyPrintArray(1);
		
		// PASS 2
		eDT.EuclideanDT_Pass2();
		eDT.prettyPrintArray(2);
		
		eDT.printFullImage();

	}

}
