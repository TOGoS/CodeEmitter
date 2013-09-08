package togos.codeemitter.sql.expr;

import java.io.IOException;

import togos.codeemitter.sql.QueryQuoter;

class InfixOperatorExpression extends AppendBaseQueryExpression
{
	protected final String op;
	protected final QueryExpression arg0, arg1;
	
	public InfixOperatorExpression( QueryExpression arg0, String op, QueryExpression arg1 ) {
		this.op   = op;
		this.arg0 = arg0;
		this.arg1 = arg1;
	}
	
	@Override public void toSql(QueryQuoter quoter, Appendable dest) throws IOException {
		arg0.toSql(quoter, dest);
		dest.append(" ");
		dest.append(op);
		dest.append(" ");
		arg1.toSql(quoter, dest);
	}
}
