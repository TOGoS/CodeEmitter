package togos.codeemitter.structure;

import java.util.List;

public class ClassDefinition
{
	public LongName name;
	
	public List<FieldDefinition> primaryConstructorInitializedFields;
	
	public List<FieldDefinition> instanceFields;
}
