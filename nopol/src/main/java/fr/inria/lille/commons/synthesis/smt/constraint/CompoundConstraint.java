package fr.inria.lille.commons.synthesis.smt.constraint;

import java.util.Collection;
import java.util.List;

import org.smtlib.IExpr;
import org.smtlib.IExpr.IDeclaration;

import fr.inria.lille.commons.collections.ListLibrary;
import fr.inria.lille.commons.collections.SetLibrary;
import fr.inria.lille.commons.synthesis.smt.SMTLib;
import fr.inria.lille.commons.synthesis.smt.SMTLibEqualVisitor;
import fr.inria.lille.commons.synthesis.smt.locationVariables.LocationVariable;
import fr.inria.lille.commons.synthesis.smt.locationVariables.LocationVariableContainer;

public abstract class CompoundConstraint extends Constraint {

	public CompoundConstraint(String name, SMTLib smtlib, Collection<Constraint> subconstraints) {
		super(name, smtlib);
		this.subconstraints = subconstraints;
	}

	@Override
	public List<LocationVariable<?>> usedLocationVariables(LocationVariableContainer locationVariableContainer) {
		Collection<LocationVariable<?>> locationVariables = SetLibrary.newLinkedHashSet();
		for (Constraint constraint : subconstraints()) {
			locationVariables.addAll(constraint.usedLocationVariables(locationVariableContainer));
		}
		return ListLibrary.newLinkedList(locationVariables);
	}

	@Override
	protected List<IExpr> arguments(LocationVariableContainer locationVariableContainer) {
		List<IExpr> locationVariables = ListLibrary.newLinkedList();
		for (Constraint constraint : subconstraints()) {
			List<IExpr> arguments = constraint.arguments(locationVariableContainer);
			SMTLibEqualVisitor.addAllIfNotContained((List) arguments, (List) locationVariables);
		}
		return locationVariables;
	}

	@Override
	protected List<IDeclaration> parameters(LocationVariableContainer locationVariableContainer) {
		List<IDeclaration> locationVariables = ListLibrary.newLinkedList();
		for (Constraint constraint : subconstraints()) {
			List<IDeclaration> parameters = constraint.parameters(locationVariableContainer);
			SMTLibEqualVisitor.addAllIfNotContained((List) parameters, (List) locationVariables);
		}
		return locationVariables;
	}

	protected Collection<IExpr> subconstraintInvocations(LocationVariableContainer locationVariableContainer) {
		Collection<IExpr> invocations = ListLibrary.newLinkedList();
		for (Constraint constraint : subconstraints()) {
			invocations.add(constraint.invocation(locationVariableContainer));
		}
		return invocations;
	}
	
	protected Collection<Constraint> subconstraints() {
		return subconstraints;
	}
	
	private Collection<Constraint> subconstraints;
}
