package togos.codeemitter.structure.rdb;

import java.util.List;

public class ForeignKeyConstraint
{
	public final String name;
	public final List<String> localColumnNames;
	public final String[] foreignTablePath;
	public final List<String> foreignColumnNames;
	public final String onUpdateAction, onDeleteAction;
	
	public ForeignKeyConstraint(
		String name, List<String> localColumnNames,
		String[] foreignTablePath, List<String> foreignColumnNames,
		String onUpdateAction, String onDeleteAction
	) {
		this.name = name;
		this.localColumnNames = localColumnNames;
		this.foreignTablePath = foreignTablePath;
		this.foreignColumnNames = foreignColumnNames;
		this.onUpdateAction = onUpdateAction;
		this.onDeleteAction = onDeleteAction;
	}

	public ForeignKeyConstraint(
		String name, List<String> localColumnNames,
		String[] foreignTablePath, List<String> foreignColumnNames
	) {
		this( name, localColumnNames, foreignTablePath, foreignColumnNames, null, null );
	}
}
