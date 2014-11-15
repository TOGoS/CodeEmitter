package togos.codeemitter.structure.rdb;

import togos.codeemitter.ExpressionEmitter;
import togos.codeemitter.structure.Expression;

public final class NextSequenceValueExpression implements Expression
{
	public final String[] sequencePath;
	
	public NextSequenceValueExpression( String[] sequencePath ) {
		this.sequencePath = sequencePath;
	}
	
	@Override
	public <Ex extends Throwable> void emit(ExpressionEmitter<? extends Ex> emitter) {
		throw new UnsupportedOperationException("Auto-increment is a marker expression and must be handled specially by the emitter");
	}
}
