package fr.inria.lille.spirals.repair.expressionV2.access;


import fr.inria.lille.repair.common.config.Config;
import fr.inria.lille.spirals.repair.expressionV2.ExpressionImpl;
import fr.inria.lille.spirals.repair.expressionV2.value.Value;

public class LiteralImpl extends ExpressionImpl implements Literal {

    public LiteralImpl(Value value) {
        super(value);
        value.setConstant(true);
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public double getWeight() {
        return Config.INSTANCE.getConstantWeight() * getPriority();
    }

    @Override
    public String asPatch() {
        return this.toString();
    }
}