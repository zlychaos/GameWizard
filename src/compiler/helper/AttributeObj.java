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
	
	@Override
	public boolean equals(Object attr){
		if(! (attr instanceof AttributeObj))
			return false;
		AttributeObj aobj = (AttributeObj)attr;
		return (this.type.equals(aobj.type) && this.id.equals(aobj.id));	
	}
	
	public boolean equalsAsPara(AttributeObj para){
		return this.type.equals(para.type);
	}

}
