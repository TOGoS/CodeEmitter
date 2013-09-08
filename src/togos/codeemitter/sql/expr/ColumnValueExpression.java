package togos.codeemitter.sql.expr;

import togos.codeemitter.sql.QueryQuoter;

class ColumnValueExpression extends StringBaseQueryExpression
{
	public final QueryExpression table;
	public final String columnName;
	
	public ColumnValueExpression( QueryExpression table, String columnName ) {
		this.table = table;
		this.columnName = columnName;
	}
	
	public String toSql( QueryQuoter quoter ) {
		return table.toSql(quoter)+"."+quoter.quoteIdentifier(columnName);
	}
}
