import java.io.*;
public class Util {
	public static void writeGameJava(String name, int player_count, int port){
		try {
			 
			String content = "public class Game{"
							+"	public static String name="+name+";"
							+"	public static int player_count="+player_count+";"
							+"	public static int port="+port+";"
							+"}";
 
			File file = new File("Game.java");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
