package compiler.helper;

public class AttributeObj {
	
	public Type type;
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
