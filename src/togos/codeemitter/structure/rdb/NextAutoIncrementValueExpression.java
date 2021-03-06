package togos.codeemitter.structure.rdb;

import togos.codeemitter.ExpressionEmitter;
import togos.codeemitter.structure.Expression;

public final class NextAutoIncrementValueExpression implements Expression
{
	public static final NextAutoIncrementValueExpression INSTANCE = new NextAutoIncrementValueExpression();
	
	private NextAutoIncrementValueExpression() { }
	
	@Override
	public <Ex extends Throwable> void emit(ExpressionEmitter<? extends Ex> emitter) {
		throw new UnsupportedOperationException("Auto-increment is a marker expression and must be handled specially by the emitter");
	}
}
