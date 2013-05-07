package togos.codeemitter.structure.rdb;

import java.util.List;

public class ForeignKeyConstraint
{
	public String name;
	public List<String> localColumnNames;
	public String foreignTableName;
	public List<String> foreignColumnNames;
	
	public ForeignKeyConstraint(
		String name, List<String> localColumnNames,
		String foreignTableName, List<String> foreignColumnNames
	) {
		this.name = name;
		this.localColumnNames = localColumnNames;
		this.foreignTableName = foreignTableName;
		this.foreignColumnNames = foreignColumnNames;
	}
}
