import java.util.*;

public class SymbolTable {

	public static LinkedList<ScopeBlock> table = new LinkedList<ScopeBlock>();
	
	public static void addKeywordsAndBuildIn(){
		/*add all keywords and build-in method to symbol table first*/
		ScopeBlock globalBlock = table.getFirst();
		globalBlock.addRecord("define",SymbolType.KEYWORD);
		globalBlock.addRecord("game",SymbolType.KEYWORD);
		globalBlock.addRecord("cards",SymbolType.KEYWORD);
		globalBlock.addRecord("characters",SymbolType.KEYWORD);
		globalBlock.addRecord("method",SymbolType.KEYWORD);
		globalBlock.addRecord("Player",SymbolType.KEYWORD);
		globalBlock.addRecord("player",SymbolType.KEYWORD);
		globalBlock.addRecord("skill",SymbolType.KEYWORD);
		globalBlock.addRecord("dealer",SymbolType.KEYWORD);
		globalBlock.addRecord("void",SymbolType.KEYWORD);
		globalBlock.addRecord("int",SymbolType.KEYWORD);
		globalBlock.addRecord("String",SymbolType.KEYWORD);
		globalBlock.addRecord("Integer",SymbolType.KEYWORD);
		globalBlock.addRecord("boolean",SymbolType.KEYWORD);
		globalBlock.addRecord("true",SymbolType.KEYWORD);
		globalBlock.addRecord("false",SymbolType.KEYWORD);
		globalBlock.addRecord("Card",SymbolType.KEYWORD);
		globalBlock.addRecord("in",SymbolType.KEYWORD);
		
		/*below are the build-in methods and variables*/
		
		globalBlock.addRecord("IOException",SymbolType.BUILD_IN);
		globalBlock.addRecord("ArrayList",SymbolType.BUILD_IN);
		globalBlock.addRecord("Collections",SymbolType.BUILD_IN);
		globalBlock.addRecord("HashMap",SymbolType.BUILD_IN);
		globalBlock.addRecord("Map",SymbolType.BUILD_IN);
		globalBlock.addRecord("LinkedList",SymbolType.BUILD_IN);
		globalBlock.addRecord("List",SymbolType.BUILD_IN);
		globalBlock.addRecord("GameServer",SymbolType.BUILD_IN);
		globalBlock.addRecord("port",SymbolType.BUILD_IN);
		globalBlock.addRecord("currentPlayerIndex",SymbolType.BUILD_IN);
		globalBlock.addRecord("roundCount",SymbolType.BUILD_IN);
		globalBlock.addRecord("Collections",SymbolType.BUILD_IN);
		globalBlock.addRecord("Array",SymbolType.BUILD_IN);
		globalBlock.addRecord("ICard",SymbolType.BUILD_IN);
		globalBlock.addRecord("CharacterBase",SymbolType.BUILD_IN);
		globalBlock.addRecord("nextOnlinePlayer",SymbolType.BUILD_IN);
		
		
		
		/*below are those needed to be Game.XXX */
		globalBlock.addRecord("playerList",SymbolType.GAME_JAVA);
		globalBlock.addRecord("map",SymbolType.GAME_JAVA);
		globalBlock.addRecord("cardStack",SymbolType.GAME_JAVA);
		globalBlock.addRecord("droppedCardStack",SymbolType.GAME_JAVA);
		globalBlock.addRecord("gameover",SymbolType.GAME_JAVA);
		globalBlock.addRecord("roundSummary",SymbolType.GAME_JAVA);
		globalBlock.addRecord("game_name",SymbolType.GAME_JAVA);
		globalBlock.addRecord("num_of_players",SymbolType.GAME_JAVA);
		globalBlock.addRecord("maximum_round",SymbolType.GAME_JAVA);
		globalBlock.addRecord("init",SymbolType.GAME_JAVA);
		globalBlock.addRecord("round_begin",SymbolType.GAME_JAVA);
		globalBlock.addRecord("round_end",SymbolType.GAME_JAVA);
		globalBlock.addRecord("turn",SymbolType.GAME_JAVA);
		globalBlock.addRecord("dying",SymbolType.GAME_JAVA);
		globalBlock.addRecord("shuffle",SymbolType.GAME_JAVA);
		globalBlock.addRecord("sendToOnePlayer",SymbolType.GAME_JAVA);
		globalBlock.addRecord("broadcast",SymbolType.GAME_JAVA);
		globalBlock.addRecord("close",SymbolType.GAME_JAVA);
		globalBlock.addRecord("waitForChoice",SymbolType.GAME_JAVA);
		globalBlock.addRecord("waitForSkill",SymbolType.GAME_JAVA);
		globalBlock.addRecord("waitForTarget",SymbolType.GAME_JAVA);
		globalBlock.addRecord("putCard",SymbolType.GAME_JAVA);
		globalBlock.addRecord("drawCard",SymbolType.GAME_JAVA);
		globalBlock.addRecord("PlayersInfo",SymbolType.GAME_JAVA);
		globalBlock.addRecord("HandCardInfo",SymbolType.GAME_JAVA);
		globalBlock.addRecord("GameGeneralInfo",SymbolType.GAME_JAVA);
	}

	public static void pushNewBlock() {
		table.add(new ScopeBlock());
	}

	public static void popBlock() {
		table.removeLast();
	}

	public static void addRecordToCurrentBlock(String symbolName,
			SymbolType type) {
		ScopeBlock curBlock = table.getLast();
		curBlock.addRecord(symbolName, type);
	}
	
	public static void addRecordToCardCharacterBlock(String cardCharacterName,
			String varName, SymbolType type){
		ScopeBlock globalBlock = table.getFirst();
		ScopeBlock cardCharacterDefineBlock = globalBlock
				.findMatchCardCharacter(cardCharacterName);
		cardCharacterDefineBlock.addRecord(varName, type);
		
	}

	public static SymbolType lookUpSymbolType(String name) {
		Iterator<ScopeBlock> iter = table.descendingIterator();

		while (iter.hasNext()) {
			ScopeBlock curBlock = iter.next();
			SymbolType ret = curBlock.findRecordInThisBlock(name);
			if (ret != SymbolType.UNDEFINED) {
				return ret;
			}
		}
		return SymbolType.UNDEFINED;
	}
	
	public static List<String> giveMeNameOfSomesTypeFromGloBlock(SymbolType type){
		ScopeBlock global = table.getFirst();
		return global.genNamesOfSomeType(type);
	}

	public static boolean checkCardCharacterVar(String cardCharacterName,
			String varName) {
		ScopeBlock globalBlock = table.get(0);
		ScopeBlock cardCharacterDefineBlock = globalBlock
				.findMatchCardCharacter(cardCharacterName);

		SymbolType type = SymbolType.UNDEFINED;
		if (cardCharacterDefineBlock != null) {

			type = cardCharacterDefineBlock.findRecordInThisBlock(varName);
		}

		if (type == SymbolType.CARD_VARIABLE
				|| type == SymbolType.CHARACTER_VARIABLE
				|| type == SymbolType.CHARACTER_SKILL) {
			return true;
		} else {
			return false;
		}
	}

}
