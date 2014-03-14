package togos.codeemitter.structure.rdb;

import java.util.List;

public class ForeignKeyConstraint
{
	public final String name;
	public final List<String> localColumnNames;
	public final String foreignTableName;
	public final List<String> foreignColumnNames;
	public final String onUpdateAction, onDeleteAction;
	
	public ForeignKeyConstraint(
		String name, List<String> localColumnNames,
		String foreignTableName, List<String> foreignColumnNames,
		String onUpdateAction, String onDeleteAction
	) {
		this.name = name;
		this.localColumnNames = localColumnNames;
		this.foreignTableName = foreignTableName;
		this.foreignColumnNames = foreignColumnNames;
		this.onUpdateAction = onUpdateAction;
		this.onDeleteAction = onDeleteAction;
	}

	public ForeignKeyConstraint(
		String name, List<String> localColumnNames,
		String foreignTableName, List<String> foreignColumnNames
	) {
		this( name, localColumnNames, foreignTableName, foreignColumnNames, null, null );
	}
}
