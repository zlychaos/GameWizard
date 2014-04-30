import java.io.*;
public class Util {
	public static void writeGameJava(String name, int player_count, int port){
		try {
			 
			String content = "public class Game{\n"
							+"	public static String name="+name+";\n"
							+"	public static int player_count="+player_count+";\n"
							+"	public static int port="+port+";\n"
							+"}\n";
 
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

    public static void writeCardsJava(String card_name, String method_content){
         File outputFile = new File("gen_"+card_name+".java");
         try{
            if(outputFile.exists()) {
                outputFile.delete();
            }
            outputFile.createNewFile();          
            BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
            out.write("public class "+"gen_"+card_name+" implements Card{\n");
            out.write("public void method(Player dealer){\n");
            out.write(method_content);
            out.write("\n}\n}");
            out.flush();
            out.close();
         }catch(IOException ex){
             ex.printStackTrace();
         }
    }
    
    public static void writeCharacterJava(String character_name, String variable_list, String method_content){
        File outputFile = new File("gen_"+character_name+".java");
        try{
           if(outputFile.exists()) {
               outputFile.delete();
           }
           outputFile.createNewFile();          
           BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
           out.write("public class gen_"+character_name+" implements Character{\n");
           out.write(variable_list);
           out.write("\npublic void method(Player dealer){\n");
           out.write(method_content);
           out.write("\n}\n}");
           out.flush();
           out.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
   }



}