
import java.io.*

public class write_example {

	public static void main(String[] args){
		File outputFile = new File("output.txt");
		try{
			if (outputFile.exists()) {
                                outputFile.delete();
                        }
                        outputFile.createNewFile();

			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

			out.write("==Frequent itemsets (min_sup=" + (int) (support * 100) + "%)\n");
			out.write("\n");
			out.flush();

			out.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

}
