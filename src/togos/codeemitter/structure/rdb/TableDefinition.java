package togos.codeemitter.structure.rdb;

import java.util.ArrayList;
import java.util.List;

public class TableDefinition
{
	public String name;
	public List<ColumnDefinition> columns;
	public List<String> primaryKeyColumnNames;
	public List<ForeignKeyConstraint> foreignKeyConstraints;
	public List<IndexDefinition> indexes;
	
	public TableDefinition(
		String name,
		List<ColumnDefinition> columns,
		List<String> primaryKeyColumnNames,
		List<ForeignKeyConstraint> foreignKeyConstraints,
		List<IndexDefinition> indexes
	) {
		this.name = name;
		this.columns = columns;
		this.primaryKeyColumnNames = primaryKeyColumnNames;
		this.foreignKeyConstraints = foreignKeyConstraints;
		this.indexes = indexes;
	}
	
	public TableDefinition(String name) {
		this( name, new ArrayList<ColumnDefinition>(), new ArrayList<String>(), new ArrayList<ForeignKeyConstraint>(), new ArrayList<IndexDefinition>() );
	}
}
