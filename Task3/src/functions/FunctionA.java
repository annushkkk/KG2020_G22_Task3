package functions;

import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class FunctionA implements IFunction {
    private final List<Double> listArgs;


    public FunctionA(List<Double> listArgs) {
        this.listArgs = listArgs;
    }

    public Double getArg(int i) {
        return listArgs.get(i);
    }


    @Override
    public double compute(double x) {
        return (listArgs.get(0) * pow(x, 3) + listArgs.get(1) * pow(x, 2) +
                listArgs.get(2) * x + listArgs.get(3));
    }
}
