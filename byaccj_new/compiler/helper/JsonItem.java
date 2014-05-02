package compiler.helper;

public class JsonItem {
	
	public boolean isAttribute;// true for attribute, false for function
	public AttributeObj attr;
	public FunctionObj func;
	
	public JsonItem(boolean isAttribute) {
		super();
		this.isAttribute = isAttribute;
	}

}
