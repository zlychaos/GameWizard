package compiler.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScopeBlock {
	
	public HashMap<String, SymbolRecord> symbols = new HashMap<String, SymbolRecord>();
	
	public SymbolRecord addRecord(String symbolName, SymbolType type){
		
		SymbolRecord sr = new SymbolRecord(symbolName, type);
		symbols.put(symbolName, sr);
		return sr;
		
	}
	
	public SymbolRecord accessSymbolInThisScope(String symbolName){
		return symbols.get(symbolName);
	}
	
	
	//public List<SymbolRecord> symbolList = new ArrayList<SymbolRecord>();

//	public void addRecord(String symbolName, SymbolType type) {
//
//		switch (type) {
//		case CARD:
//		case CHARACTER:
//			symbolList
//					.add(new SymbolRecord(symbolName, type, new ScopeBlock()));
//			break;
//		default:
//			symbolList.add(new SymbolRecord(symbolName, type, null));
//		}
//
//	}
//	
//	public List<String> genNamesOfSomeType(SymbolType type){
//		List<String> ret = new ArrayList<String>();
//		for (SymbolRecord record : symbolList) {
//			if (record.getSymbolType()==type) {
//				ret.add(record.getSymbolName());
//			}
//		}
//		return ret;
//	}
//
//	public SymbolType findRecordInThisBlock(String name) {
//		for (SymbolRecord record : symbolList) {
//			if (record.getSymbolName().equals(name)) {
//				return record.getSymbolType();
//			}
//		}
//		return SymbolType.UNDEFINED;
//	}
//
//	public ScopeBlock findMatchCardCharacter(String name) {
//		for (SymbolRecord record : symbolList) {
//			if (record.getSymbolName().equals(name)) {
//				SymbolType type = record.getSymbolType();
//				return record.getScopeBlock();
//			}
//		}
//		return null;
//	}
	
}