package togos.codeemitter.structure;

import togos.lang.SourceLocation;

public abstract class BaseExpression implements Expression
{
	public final SourceLocation sLoc;
	public BaseExpression( SourceLocation sLoc ) {
		this.sLoc = sLoc;
	}
}
