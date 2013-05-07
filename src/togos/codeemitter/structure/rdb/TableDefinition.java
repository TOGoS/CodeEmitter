package togos.codeemitter.structure.rdb;

import java.util.List;

public class TableDefinition
{
	public String name;
	public List<ColumnDefinition> columns;
	public List<ForeignKeyConstraint> foreignKeyConstraints;
	public List<IndexDefinition> indexes;
}
