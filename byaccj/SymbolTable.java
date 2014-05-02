import java.util.*;

public class SymbolTable {

	public static LinkedList<ScopeBlock> table = new LinkedList<ScopeBlock>();

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
		ScopeBlock globalBlock = table.get(0);
		ScopeBlock cardCharacterDefineBlock = globalBlock
				.findMatchCardCharacter(cardCharacterName);
		cardCharacterDefineBlock.addRecord(varName, type);
		
	}

	public static SymbolType loopUpSymbolType(String name) {
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
