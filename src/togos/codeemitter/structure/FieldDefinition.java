package togos.codeemitter.structure;

public class FieldDefinition
{
	public String name;
	public MemberVisibility visibility;
	public LongName typeName;
	public Expression defaultValue;
	
	public FieldDefinition( String name ) {
		this.name = name;
	}
}
