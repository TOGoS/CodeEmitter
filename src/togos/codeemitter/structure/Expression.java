package togos.codeemitter.structure;

import togos.codeemitter.ExpressionEmitter;

public interface Expression
{
	public <Ex extends Throwable> void emit( ExpressionEmitter<? extends Ex> emitter ) throws Ex;
}
