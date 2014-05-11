package compiler.helper;

import java.util.*;

public class SymbolTable {
	
	public static int current; // 1 for Game, 2 for cards, 3 for characters, 4 for procedures
	
	public static ScopeBlock playerBlock = new ScopeBlock();
	public static ScopeBlock listBlock = new ScopeBlock();
	public static ScopeBlock dictBlock = new ScopeBlock();
	
	public static ScopeBlock reservedBlock = new ScopeBlock();
	public static ScopeBlock gameBlock = new ScopeBlock();
	
	public static boolean firstCard = true; 
	public static ScopeBlock baseCardBlock = new ScopeBlock();
	public static boolean firstCharacter = true;
	public static ScopeBlock baseCharacterBlock = new ScopeBlock();
	
	public static ScopeBlock currentCardCharacterBlock = null;
	
	public static FunctionObj curFunction = null;
	public static LinkedList<ScopeBlock> function_locals = new LinkedList<ScopeBlock>();
	
	public static HashSet<String> current_skill_IDs = null;
	public static HashSet<String> current_all_IDs = null;
	public static HashSet<String> all_IDs = new HashSet<String>();
	
	public static HashSet<String> card_names = new HashSet<String>();
	public static HashSet<String> character_names = new HashSet<String>();
	
	
	public static void newLocalBlock() {
		function_locals.addFirst(new ScopeBlock());
	}

	public static void popLocalBlock() {
		function_locals.removeFirst();
	}
	
	public static SymbolRecord accessID(String ID){
		for(ScopeBlock sb : function_locals){
			if(sb.alreadyHave(ID))
				return sb.accessSymbolInThisScope(ID);
		}
		if((current == 2||current == 3) && currentCardCharacterBlock.alreadyHave(ID)){
			return currentCardCharacterBlock.accessSymbolInThisScope(ID);
		}
		if(gameBlock.alreadyHave(ID)){
			return gameBlock.accessSymbolInThisScope(ID);
		}
		return null;
	}
	
	public static boolean putInGame(String id, boolean isAttribute, Object attrOrFunc){
		if(reservedBlock.alreadyHave(id)){
			return false;
		}
		
		if(gameBlock.alreadyHave(id) || all_IDs.contains(id)){
			return false;
		}
		else{
			SymbolRecord sr = gameBlock.addRecord(id);
			sr.setValue(isAttribute, attrOrFunc);
			all_IDs.add(id);
			return true;
		}
	}
	
	public static boolean putNewCardName(String id){
		if(reservedBlock.alreadyHave(id)){
			return false;
		}
		
		if(gameBlock.alreadyHave(id) || all_IDs.contains(id)){
			return false;
		}
		else{
			card_names.add(id);
			all_IDs.add(id);
			return true;
		}
	}
	
	public static boolean putNewCharacterName(String id){
		if(reservedBlock.alreadyHave(id)){
			return false;
		}
		
		if(gameBlock.alreadyHave(id) || all_IDs.contains(id)){
			return false;
		}
		else{
			character_names.add(id);
			all_IDs.add(id);
			return true;
		}
	}
	
	public static boolean putInCard(String id, boolean isAttribute, Object attrOrFunc){
		if(reservedBlock.alreadyHave(id)){
			return false;
		}
		
		if(gameBlock.alreadyHave(id) || currentCardCharacterBlock.alreadyHave(id) || current_all_IDs.contains(id) ){
			return false;
		}
		else{
			SymbolRecord sr = currentCardCharacterBlock.addRecord(id);
			sr.setValue(isAttribute, attrOrFunc);
			
			if(firstCard){
				sr = baseCardBlock.addRecord(id);
				sr.setValue(isAttribute, attrOrFunc);
			}
			
			all_IDs.add(id);
			current_all_IDs.add(id);
			return true;
		}
	}
	
	public static boolean putInCharacter(String id, boolean isAttribute, Object attrOrFunc){
		if(reservedBlock.alreadyHave(id)){
			return false;
		}
		
		if(gameBlock.alreadyHave(id) || currentCardCharacterBlock.alreadyHave(id) || current_all_IDs.contains(id) ){
			return false;
		}
		else{
			SymbolRecord sr = currentCardCharacterBlock.addRecord(id);
			sr.setValue(isAttribute, attrOrFunc);
			
			if(firstCharacter){
				sr = baseCharacterBlock.addRecord(id);
				sr.setValue(isAttribute, attrOrFunc);
			}
			
			all_IDs.add(id);
			current_all_IDs.add(id);
			return true;
		}
	}
	
	public static boolean putInSkill(String id){
		if(current_skill_IDs.contains(id))
			return false;
		current_skill_IDs.add(id);
		return true;
	}
	
	public static boolean putInLocal(String id, AttributeObj attr){
		if(reservedBlock.alreadyHave(id)){
			return false;
		}
		
		if(gameBlock.alreadyHave(id))
			return false;
		for(ScopeBlock sb : function_locals){
			if(sb.alreadyHave(id))
				return false;
		}
		if(( current == 2 || current == 3) && currentCardCharacterBlock.alreadyHave(id) )
			return false;
		if( current == 2 && baseCardBlock.alreadyHave(id) )
			return false;
		if( current == 3 && baseCharacterBlock.alreadyHave(id))
			return false;
		
		ScopeBlock sb = function_locals.getFirst();
		SymbolRecord sr = sb.addRecord(id);
		sr.setValue(true, attr);
		
		return true;
	}
	
	public static void initSymbolTable(){
				
		// Can not be used in GameWizard because they are types in Java 
		String[] java_keywords = {"IOException", "ArrayList", "Collections", 
				"HashMap", "Map", "LinkedList", "List", "Array", "Integer"};
		// Can not be used in GameWizard because they are GameWizard keywords
//		String[] game_wizard_keywords = {"define", "game", "cards", "characters", 
//				"method", "Player", "skill", "void", "int", "String", "boolean", 
//				"init", "round_begin", "turn", "round_end", "true", "false", "in"};
		// Can not be used in GameWizard because they are used in templates of target code
		String[] game_wizard_reserved = {"CharacterBase", "CardBase", "GameServer", "port", 
				"map", "currentPlayerIndex", "roundCount", "nextOnlinePlayer"};
		
		
		for(String id : java_keywords){
			reservedBlock.addRecord(id);
			all_IDs.add(id);
		}
		for(String id : game_wizard_reserved){
			reservedBlock.addRecord(id);
			all_IDs.add(id);
		}
//		for(String id : game_wizard_reserved){
//			globalBlock.addRecord(id, SymbolType.BUILD_IN);
//			all_IDs.put(id, globalBlock);
//		}
//		// Already declared in templates of target code, can be accessed, but can not be used for new ID
//		String[] game_wizard_already = {"playerList", "cardStack", "droppedCardStack", "shuffle", 
//				"roundSummary", "sendToOnePlayer", "broadcast", "close", "putCard", "drawCard", 
//				"PlayersInfo", "HandCardInfo", "GameGeneralInfo"};
//		SymbolRecord tmp = null;
//		tmp = globalBlock.addRecord("playerList",SymbolType.GAME);
//		tmp.setValue(true, new AttributeObj("playerList", PrimaryType.LISTOFPLAYER));
//		tmp = globalBlock.addRecord("cardStack",SymbolType.GAME);
//		tmp.setValue(true, new AttributeObj("cardStack", PrimaryType.LISTOFCARD));
//		globalBlock.addRecord("droppedCardStack",SymbolType.GAME);
//		tmp.setValue(true, new AttributeObj("droppedCardStack", PrimaryType.LISTOFCARD));
//		tmp = globalBlock.addRecord("roundSummary",SymbolType.GAME);
//		tmp.setValue(true, new AttributeObj("roundSummary", PrimaryType.DICTINTTOCARD));
//		
		
//		"gameover",
//		globalBlock.addRecord("shuffle",SymbolType.GAME);
//		globalBlock.addRecord("sendToOnePlayer",SymbolType.GAME);
//		globalBlock.addRecord("broadcast",SymbolType.GAME);
//		globalBlock.addRecord("close",SymbolType.GAME);
//		globalBlock.addRecord("putCard",SymbolType.GAME);
//		globalBlock.addRecord("drawCard",SymbolType.GAME);
//		globalBlock.addRecord("PlayersInfo",SymbolType.GAME);
//		globalBlock.addRecord("HandCardInfo",SymbolType.GAME);
//		globalBlock.addRecord("GameGeneralInfo",SymbolType.GAME);
//		
//		for(String id : game_wizard_already){
//			//globalBlock.addRecord(id, SymbolType.GAME);
//			all_IDs.put(id, globalBlock);
//		}
//		
//		table.add(globalBlock);
		
	}

	
}
