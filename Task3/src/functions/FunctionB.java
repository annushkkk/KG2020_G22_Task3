package functions;

import java.util.List;

import static java.lang.Math.sin;

public class FunctionB implements IFunction {


    @Override
    public double compute(double x,List<Double> functionArgs) {
        return (functionArgs.get(0) * sin(functionArgs.get(1) * x + functionArgs.get(2)) + functionArgs.get(3));
    }
}
