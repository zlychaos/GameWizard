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
		globalBlock.addRecord("init",SymbolType.KEYWORD);
		globalBlock.addRecord("round",SymbolType.KEYWORD);
		globalBlock.addRecord("round_begin",SymbolType.KEYWORD);
		globalBlock.addRecord("turn",SymbolType.KEYWORD);
		globalBlock.addRecord("round_end",SymbolType.KEYWORD);
		globalBlock.addRecord("dying",SymbolType.KEYWORD);
		globalBlock.addRecord("true",SymbolType.KEYWORD);
		globalBlock.addRecord("false",SymbolType.KEYWORD);
		
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
		globalBlock.addRecord("playerList",SymbolType.BUILD_IN);
		globalBlock.addRecord("map",SymbolType.BUILD_IN);
		globalBlock.addRecord("cardStack",SymbolType.BUILD_IN);
		globalBlock.addRecord("droppedCardStack",SymbolType.BUILD_IN);
		globalBlock.addRecord("currentPlayerIndex",SymbolType.BUILD_IN);
		globalBlock.addRecord("roundCount",SymbolType.BUILD_IN);
		globalBlock.addRecord("gameover",SymbolType.BUILD_IN);
		globalBlock.addRecord("roundSummary",SymbolType.BUILD_IN);
		globalBlock.addRecord("shuffle",SymbolType.BUILD_IN);
		globalBlock.addRecord("Collections",SymbolType.BUILD_IN);
		globalBlock.addRecord("Array",SymbolType.BUILD_IN);
		globalBlock.addRecord("sendToOnePlayer",SymbolType.BUILD_IN);
		globalBlock.addRecord("broadcast",SymbolType.BUILD_IN);
		globalBlock.addRecord("close",SymbolType.BUILD_IN);
		globalBlock.addRecord("putCard",SymbolType.BUILD_IN);
		globalBlock.addRecord("ICard",SymbolType.BUILD_IN);
		globalBlock.addRecord("CharacterBase",SymbolType.BUILD_IN);
		globalBlock.addRecord("drawCard",SymbolType.BUILD_IN);
		globalBlock.addRecord("nextOnlinePlayer",SymbolType.BUILD_IN);
		globalBlock.addRecord("PlayersInfo",SymbolType.BUILD_IN);
		globalBlock.addRecord("HandCardInfo",SymbolType.BUILD_IN);
		globalBlock.addRecord("GameGeneralInfo",SymbolType.BUILD_IN);
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
