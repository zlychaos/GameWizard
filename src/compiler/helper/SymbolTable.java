package compiler.helper;

import java.util.*;

public class SymbolTable {

	public static LinkedList<ScopeBlock> table = new LinkedList<ScopeBlock>();
	public static HashMap<String, ScopeBlock> current_Card_Character = new HashMap<String, ScopeBlock>();
	public static HashMap<String, ScopeBlock> all_IDs = new HashMap<String, ScopeBlock>();
	
	public static void initSymbolTable(){
		ScopeBlock globalBlock = new ScopeBlock();
		// Can not be used in GameWizard because they are types in Java 
		String[] java_keywords = {"IOException", "ArrayList", "Collections", 
				"HashMap", "Map", "LinkedList", "List", "Array"};
		// Can not be used in GameWizard because they are GameWizard keywords
		String[] game_wizard_keywords = {"define", "game", "cards", "characters", 
				"method", "Player", "skill", "void", "int", "String", "Integer", "boolean", 
				"init", "round_begin", "turn", "round_end", "true", "false", "in"};
		// Can not be used in GameWizard because they are used in templates of target code
		String[] game_wizard_reserved = {"CharacterBase", "ICard", "Card", "GameServer", "port", 
				"map", "currentPlayerIndex", "roundCount", "gameover", "nextOnlinePlayer"};
		
		
		for(String id : java_keywords){
			globalBlock.addRecord(id, SymbolType.JAVA);
			all_IDs.put(id, globalBlock);
		}
		for(String id : game_wizard_keywords){
			globalBlock.addRecord(id, SymbolType.KEYWORD);
			all_IDs.put(id, globalBlock);
		}
		for(String id : game_wizard_reserved){
			globalBlock.addRecord(id, SymbolType.BUILD_IN);
			all_IDs.put(id, globalBlock);
		}
		// Already declared in templates of target code, can be accessed, but can not be used for new ID
		String[] game_wizard_already = {"playerList", "cardStack", "droppedCardStack", "shuffle", 
				"roundSummary", "sendToOnePlayer", "broadcast", "close", "putCard", "drawCard", 
				"PlayersInfo", "HandCardInfo", "GameGeneralInfo"};
		SymbolRecord tmp = null;
		tmp = globalBlock.addRecord("playerList",SymbolType.GAME);
		tmp.setValue(true, new AttributeObj("playerList", Type.LISTOFPLAYER));
		tmp = globalBlock.addRecord("cardStack",SymbolType.GAME);
		tmp.setValue(true, new AttributeObj("cardStack", Type.LISTOFCARD));
		globalBlock.addRecord("droppedCardStack",SymbolType.GAME);
		tmp.setValue(true, new AttributeObj("droppedCardStack", Type.LISTOFCARD));
		tmp = globalBlock.addRecord("roundSummary",SymbolType.GAME);
		tmp.setValue(true, new AttributeObj("roundSummary", Type.DICTINTTOCARD));
		
		globalBlock.addRecord("shuffle",SymbolType.GAME);
		globalBlock.addRecord("sendToOnePlayer",SymbolType.GAME);
		globalBlock.addRecord("broadcast",SymbolType.GAME);
		globalBlock.addRecord("close",SymbolType.GAME);
		globalBlock.addRecord("putCard",SymbolType.GAME);
		globalBlock.addRecord("drawCard",SymbolType.GAME);
		globalBlock.addRecord("PlayersInfo",SymbolType.GAME);
		globalBlock.addRecord("HandCardInfo",SymbolType.GAME);
		globalBlock.addRecord("GameGeneralInfo",SymbolType.GAME);
		
		for(String id : game_wizard_already){
			//globalBlock.addRecord(id, SymbolType.GAME);
			all_IDs.put(id, globalBlock);
		}
		
		table.add(globalBlock);
		
	}
	
	public static void pushNewBlock() {
		table.addLast(new ScopeBlock());
	}

	public static void popBlock() {
		table.removeLast();
	}
	
	public static boolean isValidForGlobal(String newID){
		if(all_IDs.containsKey(newID))
			return false;
		return true;
	}
	
	public static boolean isValidForCurrentCardOrCharacter(String newID){
		if(current_Card_Character.containsKey(newID))
			return false;
		return true;
	}
	
	public static boolean isValidForLocal(String newID){
		for(ScopeBlock sb : table){
			if(sb.symbols.containsKey(newID))
				return false;
		}
		return true;
	}
	
	public static Type getTypeOfQuarlifiedNameAttribute(String quarlifiedName){
		String[] parts = quarlifiedName.split(".");
		int cur = 0;
		do{
		
		}while(cur < parts.length);
		return null;
	}
	
	public static FunctionObj getFuncOfQuarlifiedName(String quarlifiedName){
		return null;
	}

	public static void addRecordToCurrentBlock(String symbolName,
			SymbolType type, boolean isAttribute, Object attrOrfunc) {
		ScopeBlock curBlock = table.getLast();
		SymbolRecord sr = curBlock.addRecord(symbolName, type);
		sr.setValue(isAttribute, attrOrfunc);
		
	}
	
}
