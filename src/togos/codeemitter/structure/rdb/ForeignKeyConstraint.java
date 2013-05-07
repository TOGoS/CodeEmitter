package togos.codeemitter.structure.rdb;

import java.util.List;

public class ForeignKeyConstraint
{
	public List<String> localColumnNames;
	public String foreignTableName;
	public List<String> foreignColumnNames;
}
