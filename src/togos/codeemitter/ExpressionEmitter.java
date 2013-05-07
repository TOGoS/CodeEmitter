package togos.codeemitter;

import togos.lang.SourceLocation;

public interface ExpressionEmitter<Ex extends Throwable>
{
	public void emitScalarLiteral( Object v, SourceLocation sLoc ) throws Ex;
}
