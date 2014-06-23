package fr.inria.lille.commons.synthesis.operator;

import static fr.inria.lille.commons.synthesis.smt.SMTLib.smtlib;

import java.util.Arrays;

import org.smtlib.IExpr.ISymbol;

public class TernaryOperator<S, U, V, T> extends DoubleOperator<T> {

	public static TernaryOperator<Boolean, Number, Number, Number> ifThenElse() {
		return new TernaryOperator<Boolean, Number, Number, Number>(Number.class, "?", ":", smtlib().ifThenElse(),
				Parameter.aBoolean(), Parameter.aNumber(), Parameter.aNumber());
	}
	
	public TernaryOperator(Class<T> resultType, String firstSymbol, String secondSymbol, ISymbol smtlibIdentifier, 
			Parameter<S> firstParameter, Parameter<U> secondParameter, Parameter<V> thirdParameter) {
		super(resultType, firstSymbol, secondSymbol, smtlibIdentifier, Arrays.asList(firstParameter, secondParameter, thirdParameter));
	}

	@Override
	public <K> K accept(OperatorVisitor<K> visitor) {
		return visitor.visitTernaryOperator(this);
	}

	
}