package togos.codeemitter.sql;

public class MySQLEmitter extends SQLEmitter
{
	@Override
	public String quoteIdentifier(String ident) {
		return '`' + doubleCharEscape(ident, '`') + '`';
	}
}
