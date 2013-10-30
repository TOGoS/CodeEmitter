package togos.codeemitter.sql;

import togos.codeemitter.structure.rdb.ColumnDefinition;

public class MySQLEmitter extends SQLEmitter
{
	public MySQLEmitter( Appendable dest ) {
		super(dest);
	}
	
	@Override
	public String quoteIdentifier(String ident) {
		return '`' + doubleCharEscape(ident, '`') + '`';
	}
	
	protected boolean needExplicitNullModifier(ColumnDefinition cd) {
		return "TIMESTAMP".equals(cd.type);
	}
}
