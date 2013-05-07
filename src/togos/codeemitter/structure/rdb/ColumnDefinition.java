package togos.codeemitter.structure.rdb;

import togos.codeemitter.structure.Expression;

public class ColumnDefinition
{
	String name;
	String type;
	boolean nullable;
	Expression defaultValue;
}
