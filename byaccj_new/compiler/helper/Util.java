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
	
	public static void genMakefile(){
		String templatePath = "compiler/helper/MakefileTemplate.txt";
		String targetPath = "target/Makefile";
		
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
						sb = new StringBuilder();
						for(String card : cards){
							sb.append(" \\\\\n\tmygame/cards/" + card + ".java");
						}
						for(String character : characters){
							sb.append(" \\\\\n\tmygame/characters/" + character + ".java");
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
	
	public static void genGame(){
		String templatePath = "compiler/helper/GameTemplate.txt";
		String targetPath = "target/mygame/Game.java";
		
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
						boolean init_defined = false;
						boolean round_begin_defined = false;
						boolean round_end_defined = false;
						boolean turn_defined = false;
						sb = new StringBuilder();
						for(JsonItem ji : game){
							switch(ji.type){
							case Attribute:
								sb.append("\tpublic static ");
								String type = getStrType(ji.attr.type);
								sb.append(type + " ");
								sb.append(ji.attr.id + "=" + ji.attr.value + "\n");
								break;
							case Function:
								if("init".equals(ji.func.id)){
									init_defined = true;
								}
								else if("round_begin".equals(ji.func.id)){
									round_begin_defined = true;
								}
								else if("round_end".equals(ji.func.id)){
									round_end_defined = true;
								}
								else if("turn".equals(ji.func.id)){
									turn_defined = true;
								}
								
								sb.append("\tpublic static ");
								sb.append(getStrType(ji.func.return_type) + " ");
								sb.append(ji.func.id + "(");
								boolean first = true;
								for(AttributeObj para : ji.func.parameters){
									if(first){
										first = false;
									}
									else{
										sb.append(", ");
									}
									sb.append(getStrType(para.type) + " " + para.id);
								}
								sb.append(") {\n");
								sb.append(ji.func.body);
								sb.append("\n\t}\n");
								break;
							default:
								System.out.println("What happened!!!!");
							}
							
						}
						if( !init_defined ){
							sb.append("\tpublic static void init(){ }\n");
						}
						if( !round_begin_defined ){
							sb.append("\tpublic static void round_begin(){ }\n");
						}
						if( !round_end_defined ){
							sb.append("\tpublic static void round_end(){ }\n");
						}
						if( !turn_defined ){
							sb.append("\tpublic static void turn(Player player){ }\n");
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
	
	public static String getStrType(Type type){
		String result = null;
		switch(type){
		case BOOLEAN:
			result = "boolean";
			break;
		case STRING:
			result = "String";
			break;
		case INTEGER:
			result = "int ";
			break;
		case DOUBLE:
			result = "double";
			break;
		case PLAYER:
			result = "Player";
			break;
		case CARD:
			result = "ICard";
			break;
		case VOID:
			result = "void";
			break;
		default:
			System.out.println("What happened???");
			break;
		}
		return result;
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
						boolean method_defined = false;
						sb = new StringBuilder();
						for(JsonItem ji : card.json){
							switch(ji.type){
							case Attribute:
								sb.append("\tpublic ");
								String type = getStrType(ji.attr.type);
								sb.append(type + " ");
								sb.append(ji.attr.id + "=" + ji.attr.value + "\n");
								break;
							case Function:
								if("method".equals(ji.func.id)){
									method_defined = true;
									sb.append("\t@Override\n");
								}
								sb.append("\tpublic ");
								sb.append(getStrType(ji.func.return_type) + " ");
								sb.append(ji.func.id + "(");
								boolean first = true;
								for(AttributeObj para : ji.func.parameters){
									if(first){
										first = false;
									}
									else{
										sb.append(", ");
									}
									sb.append(getStrType(para.type) + " " + para.id);
								}
								sb.append(") {\n");
								sb.append(ji.func.body);
								sb.append("\n\t}\n");
								break;
							default:
								System.out.println("What happened!!!!");
							}
							
						}
						if( !method_defined ){
							sb.append("\t@Override\n\tpublic void method(Player dealer){ }");
						}
						line = data.replaceAll("###", sb.toString());
						break;
					case 3:
						line = data.replaceAll("###", name);
						break;
					case 4:
						sb = new StringBuilder();
						for(JsonItem ji : card.json){
							if(ji.type == JsonItemType.Attribute){
								sb.append("sb.append(\"\\\\t" + ji.attr.id + "=\");\n");
								sb.append("\t\tsb.append(" + ji.attr.id + ");\n");
								sb.append("\t\tsb.append(\"\\\\n\")\n;");
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
	
	public static ArrayList<Skill> getSkills(Config character){
		for(JsonItem ji : character.json){
			if(ji.type == JsonItemType.SkillList){
				ArrayList<Skill> skills = ji.skills;
				character.json.remove(ji);
				return skills;
			}
		}
		return new ArrayList<Skill>();
	}

	private static void genCharacter(Config character, int i) {
		String name = character.id;
		characters[i] = name;

		String templatePath = "compiler/helper/CharacterTemplate.txt";
		String targetPath = "target/mygame/characters/" + name + ".java";
		
		ArrayList<Skill> skills = getSkills(character);
		
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
							switch(ji.type){
							case Attribute:
								sb.append("\tpublic ");
								sb.append(getStrType(ji.attr.type) + " ");
								sb.append(ji.attr.id + "=" + ji.attr.value + "\n");
								break;
							case Function:
								sb.append("\tpublic ");
								String type = getStrType(ji.func.return_type);
								sb.append(type + " ");
								sb.append(ji.func.id + "(");
								boolean first = true;
								for(AttributeObj para : ji.func.parameters){
									if(first){
										first = false;
									}
									else{
										sb.append(", ");
									}
									sb.append(getStrType(para.type) + " " + para.id);
								}
								sb.append(") {\n");
								sb.append(ji.func.body);
								sb.append("\n}\n");
								break;
							default:
								System.out.println("What happened!!!!");
							}
						}
						line = data.replaceAll("###", sb.toString());
						break;
					case 3:
						line = data.replaceAll("###", Integer.toString(skills.size()));
						break;
					case 4:
						sb = new StringBuilder();
						int len = skills.size();
						for(int j=0;j<len;j++){
							sb.append("\t\tskillList["+ j +"] = \""+ skills.get(j).id +"\";\n");
						}
						line = data.replaceAll("###", sb.toString());
						break;
					case 5:
						line = data.replaceAll("###", name);
						break;
					case 6:
						sb = new StringBuilder();
						for(Skill skill : skills){
							sb.append("\t\tif(\""+skill.id+"\".equals(skillName){\n");
							sb.append(skill.body);
							sb.append("return true;");
							sb.append("\n\t\t}\n");
						}
						line = data.replaceAll("###", sb.toString());
						break;
					case 7:
						sb = new StringBuilder();
						for(JsonItem ji : character.json){
							if(ji.type == JsonItemType.Attribute){
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
