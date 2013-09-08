package togos.codeemitter.sql.expr;

import togos.codeemitter.sql.QueryQuoter;

class SQLIdentifierExpression extends StringBaseQueryExpression
{
	protected final String name;
	
	public SQLIdentifierExpression( String name ) {
		this.name = name;
	}
	
	@Override public String toSql(QueryQuoter quoter) {
		return quoter.quoteIdentifier(name);
	}
}
