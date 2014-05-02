package compiler.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Util {
	
	public static String[] cards;
	public static String[] characters;
	
	public static ArrayList<JsonItem> game;
	
	public static void genAllCards(ArrayList<Config> config_list){
		
		String targetPath = "target/mygame/cards/";
		File f = new File(targetPath);
		if(!f.exists())
			f.mkdirs();
		
		int length = config_list.size();
		cards = new String[length];
		for(int i=0;i<length;i++){
			Config card = config_list.get(i);
			genCard(card, i);
		}
	}
	
	public static void genCard(Config card, int i){
		String name = card.id;
		cards[i] = name;
		
		String templatePath = "compiler/helper/CardTemplate.txt";
		String targetPath = "target/mygame/cards/" + name + ".java";
		
		BufferedReader reader; 
		BufferedWriter writer;
		String line = null;
		StringBuilder sb = null;
		
		try {
			reader = new BufferedReader(new FileReader(templatePath));
			writer  = new BufferedWriter(new FileWriter(targetPath));
			
			int count = 0;
			String data = reader.readLine();  
			while( data!=null){  
			      
				if(data.indexOf("###")!=-1){
					count++;
					//String[] parts = data.split("###");
					switch (count){
					case 1:
						line = data.replaceAll("###", name);
						break;
					case 2:
						sb = new StringBuilder();
						for(JsonItem ji : card.json){
							if(ji.isAttribute){
								sb.append("public ");
								switch(ji.attr.type){
								case BOOLEAN:
									sb.append("boolean ");
									break;
								case STRING:
									sb.append("String ");
									break;
								case INTEGER:
									sb.append("int ");
									break;
								case DOUBLE:
									sb.append("double ");
									break;
								default:
									System.out.println("What happened???");
									break;
								}
								sb.append(ji.attr.id + "=" + ji.attr.value + "\n");
							}
						}
						line = data.replaceAll("###", sb.toString());
						break;
					case 3:
						line = data.replaceAll("###", name);
						break;
					case 4:
						line = data;
						break;
					case 5:
						sb = new StringBuilder();
						for(JsonItem ji : card.json){
							if(ji.isAttribute){
								sb.append("sb.append(\"\\\\t" + ji.attr.id + "=\");\n");
								sb.append("\t\tsb.append(" + ji.attr.id + ");\n");
								sb.append("\t\tsb.append(\"\\\\n\");");
							}
						}
						line = data.replaceAll("###", sb.toString());
						break;
					default:
						System.out.println("What happened???");
						break;
					}
					writer.write(line + "\n");
				}
				else{
					writer.write(data + "\n");
				}
				
				data = reader.readLine();   
			} 
			writer.flush();  
		    reader.close();  
		    writer.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 
	}
	
	public static void genAllCharacters(ArrayList<Config> config_list){
		
		String targetPath = "target/mygame/characters/";
		File f = new File(targetPath);
		if(!f.exists())
			f.mkdirs();
		
		int length = config_list.size();
		characters = new String[length];
		for(int i=0;i<length;i++){
			Config character = config_list.get(i);
			genCharacter(character, i);
		}
	}

	private static void genCharacter(Config character, int i) {
		String name = character.id;
		characters[i] = name;

		String templatePath = "compiler/helper/CharacterTemplate.txt";
		String targetPath = "target/mygame/characters/" + name + ".java";
		
		BufferedReader reader; 
		BufferedWriter writer;
		String line = null;
		StringBuilder sb = null;
		
		try {
			reader = new BufferedReader(new FileReader(templatePath));
			writer  = new BufferedWriter(new FileWriter(targetPath));
			
			int count = 0;
			String data = reader.readLine();  
			while( data!=null){  
			      
				if(data.indexOf("###")!=-1){
					count++;
					//String[] parts = data.split("###");
					switch (count){
					case 1:
						line = data.replaceAll("###", name);
						break;
					case 2:
						sb = new StringBuilder();
						for(JsonItem ji : character.json){
							if(ji.isAttribute){
								sb.append("public ");
								switch(ji.attr.type){
								case BOOLEAN:
									sb.append("boolean ");
									break;
								case STRING:
									sb.append("String ");
									break;
								case INTEGER:
									sb.append("int ");
									break;
								case DOUBLE:
									sb.append("double ");
									break;
								default:
									System.out.println("What happened???");
									break;
								}
								sb.append(ji.attr.id + "=" + ji.attr.value + "\n");
							}
						}
						line = data.replaceAll("###", sb.toString());
						break;
					case 3:
						line = data.replaceAll("###", "0");
						break;
					case 4:
						line = data.replaceAll("###", "");
						break;
					case 5:
						line = data.replaceAll("###", name);
						break;
					case 6:
						line = data.replaceAll("###", "");
						break;
					case 7:
						sb = new StringBuilder();
						for(JsonItem ji : character.json){
							if(ji.isAttribute){
								sb.append("sb.append(\"\\\\t" + ji.attr.id + "=\");\n");
								sb.append("\t\tsb.append(" + ji.attr.id + ");\n");
								sb.append("\t\tsb.append(\"\\\\n\");");
							}
						}
						line = data.replaceAll("###", sb.toString());
						break;
					default:
						System.out.println("What happened???");
						break;
					}
					writer.write(line + "\n");
				}
				else{
					writer.write(data + "\n");
				}
				
				data = reader.readLine();   
			} 
			writer.flush();  
		    reader.close();  
		    writer.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
