package togos.codeemitter.structure;

import togos.codeemitter.ExpressionEmitter;

public interface Expression
{
	public void emit( ExpressionEmitter<?> emitter );
}
