package togos.codeemitter.sql.expr;

import togos.codeemitter.sql.QueryQuoter;

public class RawSQLExpression extends StringBaseQueryExpression
{
	final String sql;
	
	public RawSQLExpression( String sql ) {
		this.sql = sql;
	}
	
	@Override public String toSql(QueryQuoter quoter) {
		return sql;
	}
}
