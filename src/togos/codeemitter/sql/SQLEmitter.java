package togos.codeemitter.sql;

import java.io.IOException;
import java.io.Writer;

import togos.codeemitter.structure.rdb.TableDefinition;

public class SQLEmitter
{
	Writer w;
	public SQLEmitter( Writer w ) {
		this.w = w;
	}
	
	protected String quoteIdentifier( String ident ) {
		return '"' + ident.replace("\"", "\"\"") + '"';
	}
	
	public void emitCreateTable( TableDefinition td ) throws IOException {
		w.write("CREATE TABLE "+quoteIdentifier(td.name) + " (\n");
		w.write(");\n");
	}
}
