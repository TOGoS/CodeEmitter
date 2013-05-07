package togos.codeemitter.structure.rdb;

import java.util.List;

public class IndexDefinition
{
	public String name;
	public List<String> indexedColumnNames;
	
	public IndexDefinition( String name, List<String> indexedColumnNames ) {
		this.name = name;
		this.indexedColumnNames = indexedColumnNames;
	}
}
