package togos.codeemitter.structure.rdb;

import java.util.ArrayList;
import java.util.List;

public class TableDefinition
{
	public String[] path;
	public List<ColumnDefinition> columns;
	public List<String> primaryKeyColumnNames;
	public List<ForeignKeyConstraint> foreignKeyConstraints;
	public List<IndexDefinition> indexes;
	
	public TableDefinition(
		String[] path,
		List<ColumnDefinition> columns,
		List<String> primaryKeyColumnNames,
		List<ForeignKeyConstraint> foreignKeyConstraints,
		List<IndexDefinition> indexes
	) {
		this.path = path;
		this.columns = columns;
		this.primaryKeyColumnNames = primaryKeyColumnNames;
		this.foreignKeyConstraints = foreignKeyConstraints;
		this.indexes = indexes;
	}
	
	public TableDefinition(String[] path) {
		this( path, new ArrayList<ColumnDefinition>(), new ArrayList<String>(), new ArrayList<ForeignKeyConstraint>(), new ArrayList<IndexDefinition>() );
	}
}
