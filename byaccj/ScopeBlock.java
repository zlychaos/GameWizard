import java.util.ArrayList;
import java.util.List;

public class ScopeBlock {
	private class SymbolRecord {
		private String symbolName;
		private SymbolType type;
		private ScopeBlock ptr;

		public SymbolRecord(String symbolName, SymbolType type, ScopeBlock ptr) {
			this.symbolName = symbolName;
			this.type = type;
			this.ptr = ptr;
		}

		public void setSymbolName(String symbolName) {
			this.symbolName = symbolName;
		}

		public void setSymbolType(SymbolType type) {
			this.type = type;
		}

		public void setScopeBlock(ScopeBlock ptr) {
			this.ptr = ptr;
		}

		public String getSymbolName() {
			return this.symbolName;
		}

		public SymbolType getSymbolType() {
			return this.type;
		}

		public ScopeBlock getScopeBlock() {
			return this.ptr;
		}
	}

	public List<SymbolRecord> symbolList = new ArrayList<SymbolRecord>();

	public void addRecord(String symbolName, SymbolType type) {

		switch (type) {
		case CARD:
		case CHARACTER:
			symbolList
					.add(new SymbolRecord(symbolName, type, new ScopeBlock()));
			break;
		default:
			symbolList.add(new SymbolRecord(symbolName, type, null));
		}

	}
	
	public List<String> genNamesOfSomeType(SymbolType type){
		List<String> ret = new ArrayList<String>();
		for (SymbolRecord record : symbolList) {
			if (record.getSymbolType()==type) {
				ret.add(record.getSymbolName());
			}
		}
		return ret;
	}

	public SymbolType findRecordInThisBlock(String name) {
		for (SymbolRecord record : symbolList) {
			if (record.getSymbolName().equals(name)) {
				return record.getSymbolType();
			}
		}
		return SymbolType.UNDEFINED;
	}

	public ScopeBlock findMatchCardCharacter(String name) {
		for (SymbolRecord record : symbolList) {
			if (record.getSymbolName().equals(name)) {
				SymbolType type = record.getSymbolType();
				return record.getScopeBlock();
			}
		}
		return null;
	}
	
}