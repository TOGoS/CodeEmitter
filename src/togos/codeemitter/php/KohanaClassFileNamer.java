package togos.codeemitter.php;

import togos.function.Function;

public class KohanaClassFileNamer implements Function<String,String>
{
	public String apply( String className ) {
		return className.replace("_", "/").toLowerCase()+".php";
	}
}
