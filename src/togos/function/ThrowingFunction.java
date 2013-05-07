package togos.function;

public interface ThrowingFunction<I,O,Ex extends Throwable>
{
	public O apply(I in) throws Ex;
}
