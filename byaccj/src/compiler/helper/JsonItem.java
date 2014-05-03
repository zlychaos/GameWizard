package compiler.helper;

import java.util.ArrayList;

public class JsonItem {
	
	public JsonItemType type;
	public AttributeObj attr;
	public FunctionObj func;
	public ArrayList<Skill> skills;
	
	public JsonItem(JsonItemType type) {
		super();
		this.type = type;
	}

}
