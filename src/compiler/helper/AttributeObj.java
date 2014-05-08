package compiler.helper;

public class AttributeObj {
	
	public Type type;
	public Type subType1; // for List and key of Dict
	public Type subType2; // for value of Dict
	public String value;
	public String id;
	
	public AttributeObj(Type type, String value) {
		super();
		this.type = type;
		this.value = value;
		
	}

	public AttributeObj(String id, Type type) {
		super();
		this.type = type;
		this.id = id;
	}

}
