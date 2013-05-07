package togos.codeemitter.php;

public class PHPClassSource
{
	public String name;
	public String extendsClassName;
	public String[] implementsInterfaceNames = new String[0];
	public String bodySource;
	
	public PHPClassSource( String name, String extendsClassName, String[] implementsInterfaceNames, String bodySource ) {
		this.name = name;
		this.extendsClassName = extendsClassName;
		this.implementsInterfaceNames = implementsInterfaceNames;
		this.bodySource = bodySource;
	}
	
	public String getClassSource() {
		String header = "class "+name;
		if( extendsClassName != null ) header += " extends "+extendsClassName;
		if( implementsInterfaceNames.length > 0 ) {
			header += " implements ";
			for( int i=0; i<implementsInterfaceNames.length; ++i ) {
				if( i > 0 ) header += ", ";
				header += implementsInterfaceNames[i];
			}
		}
		
		boolean indented = bodySource.startsWith("\t");
		String okBody = bodySource.trim();
		okBody = "\t" + (indented ? okBody : okBody.replace("\n", "\n\t"));
		
		return
			header + "\n" +
			"{\n" +
			okBody + "\n" +
			"}";
	}
}
