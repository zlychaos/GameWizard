package compiler.helper;

import java.util.ArrayList;

public class Type {
	
	public static Type INTEGER = new Type(PrimaryType.INTEGER, null, null);
	public static Type BOOLEAN = new Type(PrimaryType.BOOLEAN, null, null);
	public static Type VOID = new Type(PrimaryType.VOID, null, null);
	public static Type NULL = new Type(PrimaryType.NULL, null, null);
	public static Type STRING = new Type(PrimaryType.STRING, null, null);
	public static Type PLAYER = new Type(PrimaryType.PLAYER, null, null);
	public static Type CHARACTER = new Type(PrimaryType.CHARACTER, null, null);
	public static Type CARD = new Type(PrimaryType.CARD, null, null);
	
	public PrimaryType primary_type;
	public Type second_type; // element of list OR key of dict
	public Type third_type; // value of dict
	
	public Type(PrimaryType primary_type, Type second_type, Type third_type) {
		super();
		this.primary_type = primary_type;
		this.second_type = second_type;
		this.third_type = third_type;
	}
	
	public static Type unaryOP(String op, Type type){
		if("!".equals(op) || "~".equals(op)){
			if(type.primary_type==PrimaryType.BOOLEAN){
				return new Type(PrimaryType.BOOLEAN, null, null);
			}
		}
		return null;
	}
	
	public static Type binaryOP(String op, Type type1, Type type2){
		if("&&".equals(op) || "||".equals(op)){
			if(type1.primary_type==PrimaryType.BOOLEAN && type2.primary_type==PrimaryType.BOOLEAN){
				return new Type(PrimaryType.BOOLEAN, null, null);
			}
		}
		if(">".equals(op) || "<".equals(op) || "==".equals(op) || ">=".equals(op) || "<=".equals(op) || "!=".equals(op) ){
			if(type1.primary_type==PrimaryType.INTEGER && type2.primary_type==PrimaryType.INTEGER){
				return new Type(PrimaryType.BOOLEAN, null, null);
			}
		}
		if("-".equals(op) || "*".equals(op) || "/".equals(op) || "%".equals(op) ){
			if(type1.primary_type==PrimaryType.INTEGER && type2.primary_type==PrimaryType.INTEGER){
				return new Type(PrimaryType.INTEGER, null, null);
			}
		}
		if("+".equals(op)){
			if(type1.primary_type==PrimaryType.INTEGER && type2.primary_type==PrimaryType.INTEGER){
				return new Type(PrimaryType.INTEGER, null, null);
			}
			if(type1.primary_type==PrimaryType.STRING && type2.primary_type!=PrimaryType.LIST 
					&& type2.primary_type!=PrimaryType.DICT){
				return new Type(PrimaryType.STRING, null, null);
			}
				
		}
		return null;
	}
	
	public static SymbolRecord dotOP(Type type, String id){
		
		if(type.equals(Type.CARD)){
			return SymbolTable.baseCardBlock.accessSymbolInThisScope(id);
		}
		else if(type.equals(Type.PLAYER)){
			SymbolRecord sr = SymbolTable.playerBlock.accessSymbolInThisScope(id);
			if(sr == null)
				return SymbolTable.baseCharacterBlock.accessSymbolInThisScope(id);
			return sr;
		}
		else if(type.primary_type == PrimaryType.LIST){
			if(id.equals("add")){
				FunctionObj func = new FunctionObj();
				func.id = "add";
				func.return_type = Type.VOID;
				func.parameters = new ArrayList<AttributeObj>();
				func.parameters.add(new AttributeObj("obj", type.second_type));
				SymbolRecord sr = new SymbolRecord("add");
				sr.setValue(false, func);
				return sr;
			}
			else if(id.equals("get")){
				FunctionObj func = new FunctionObj();
				func.id = "get";
				func.return_type = type.second_type;
				func.parameters = new ArrayList<AttributeObj>();
				func.parameters.add(new AttributeObj("index", type.INTEGER));
				SymbolRecord sr = new SymbolRecord("get");
				sr.setValue(false, func);
				return sr;
			}
			else if(id.equals("clear")){
				FunctionObj func = new FunctionObj();
				func.id = "clear";
				func.return_type = Type.VOID;
				func.parameters = new ArrayList<AttributeObj>();
				SymbolRecord sr = new SymbolRecord("clear");
				sr.setValue(false, func);
				return sr;
			}
			return null;
		}
		else if(type.primary_type == PrimaryType.DICT){
			if(id.equals("put")){
				FunctionObj func = new FunctionObj();
				func.id = "put";
				func.return_type = Type.VOID;
				func.parameters = new ArrayList<AttributeObj>();
				func.parameters.add(new AttributeObj("key", type.second_type));
				func.parameters.add(new AttributeObj("value", type.third_type));
				SymbolRecord sr = new SymbolRecord("put");
				sr.setValue(false, func);
				return sr;
			}
			else if(id.equals("get")){
				FunctionObj func = new FunctionObj();
				func.id = "get";
				func.return_type = type.third_type;
				func.parameters = new ArrayList<AttributeObj>();
				func.parameters.add(new AttributeObj("key", type.second_type));
				SymbolRecord sr = new SymbolRecord("get");
				sr.setValue(false, func);
				return sr;
			}
			
			return null;
		}
			
		
		return null;
	}
	
	@Override
	public boolean equals(Object type){
		if(type instanceof Type){
			Type t = (Type)type;
			if(this.primary_type!=t.primary_type){
				if((this.primary_type == PrimaryType.CARD || this.primary_type == PrimaryType.PLAYER)
						&& t.primary_type == PrimaryType.NULL)
					return true;
				return false;
			}
			if(this.primary_type!=PrimaryType.LIST && this.primary_type!=PrimaryType.DICT){
				return true;
			}
			if(!this.second_type.equals(t.second_type)){
				return false;
			}
			if(this.primary_type!=PrimaryType.DICT){
				return true;
			}
			return this.third_type.equals(t.third_type);
		}
		return false;
	}
	
	@Override
	public String toString(){
		switch(primary_type){
		case INTEGER:
			return "int";
		case  DOUBLE:
			return "double";
		case  STRING:
			return "String";
		case  BOOLEAN:
			return "boolean";
		case  PLAYER:
			return "Player";
		case  CHARACTER:
			return "CharacterBase";
		case  CARD:
			return "CardBase";
		case  VOID:
			return "void";
		case  LIST:
			return "ArrayList<"+second_type.toString()+">";
		case  DICT:
			return "HashMap<"+second_type.toString()+","+third_type.toString()+">";	
		default:
			System.out.println("An unknown type!");
			return null;
		}
	}

}
