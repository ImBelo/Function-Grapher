package model.token;

import java.util.Optional;
import java.util.function.Supplier;

public interface Type {
	public String getData();
	public Supplier<? extends Token> getSupplier();
	public int getPriority();
	public boolean equals(Object o);
}
