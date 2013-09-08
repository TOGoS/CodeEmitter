package togos.codeemitter.sql.expr;

import togos.codeemitter.sql.QueryQuoter;

class LiteralNumberExpression extends StringBaseQueryExpression
{
	Number n;
	
	public LiteralNumberExpression( Number n ) {
		this.n = n;
	}
	
	@Override public String toSql(QueryQuoter quoter) {
		return n.toString();
	}
}
