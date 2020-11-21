package functions;

import java.util.List;

import static java.lang.Math.pow;

public class FunctionE implements IFunction {


    @Override
    public double compute(double x,List<Double> functionArgs) {
        return functionArgs.get(0) * 1 / (1 + pow(Math.E, -x)) + functionArgs.get(1);
    }
}
