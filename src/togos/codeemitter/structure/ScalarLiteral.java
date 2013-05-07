package togos.codeemitter.structure;

import togos.codeemitter.ExpressionEmitter;
import togos.lang.SourceLocation;

public class ScalarLiteral extends BaseExpression
{
	public final Object value;
	public ScalarLiteral( Object value, SourceLocation sLoc ) {
		super(sLoc);
		this.value = value;
	}
	
	@Override
	public <Ex extends Throwable> void emit(ExpressionEmitter<? extends Ex> emitter) throws Ex {
		emitter.emitScalarLiteral(value, sLoc);
	}
}
