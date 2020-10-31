package functions;

import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class FunctionE implements IFunction {
    private final List<Double> listArgs;


    public FunctionE(List<Double> listArgs) {
        this.listArgs = listArgs;
    }

    public Double getArg(int i) {
        return listArgs.get(i);
    }



    @Override
    public double compute(double x) {
        return listArgs.get(0) * 1 / (1 + pow(Math.E, -x)) + listArgs.get(1);
    }
}
