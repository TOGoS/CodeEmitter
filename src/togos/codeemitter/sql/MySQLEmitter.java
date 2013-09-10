package togos.codeemitter.sql;

public class MySQLEmitter extends SQLEmitter
{
	public MySQLEmitter( Appendable dest ) {
		super(dest);
	}
	
	@Override
	public String quoteIdentifier(String ident) {
		return '`' + doubleCharEscape(ident, '`') + '`';
	}
}
