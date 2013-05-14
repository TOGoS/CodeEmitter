package togos.codeemitter.structure;

import java.util.ArrayList;
import java.util.List;

public class ClassDefinition
{
	public LongName name;
	
	public List<FieldDefinition> primaryConstructorInitializedFields;
	public List<FieldDefinition> instanceFields;
	
	public ClassDefinition( LongName name ) {
		this.name = name;
		primaryConstructorInitializedFields = new ArrayList<FieldDefinition>();
		instanceFields = new ArrayList<FieldDefinition>();
	}
}
