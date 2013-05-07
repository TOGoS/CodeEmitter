package togos.codeemitter.structure.rdb;

import togos.codeemitter.structure.Expression;

public class ColumnDefinition
{
	public String name;
	public String type;
	public boolean nullable;
	public Expression defaultValue;
	
	public ColumnDefinition( String name, String type, boolean nullable, Expression defaultValue ) {
		this.name = name;
		this.type = type;
		this.nullable = nullable;
		this.defaultValue = defaultValue;
	}
}
