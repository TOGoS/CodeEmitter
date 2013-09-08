package togos.codeemitter.sql.expr;

import java.io.IOException;

import togos.codeemitter.sql.QueryQuoter;

public interface QueryExpression {
	public String toSql( QueryQuoter quoter );
	public void toSql( QueryQuoter quoter, Appendable dest ) throws IOException;
}
