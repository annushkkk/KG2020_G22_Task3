package functions;

import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class FunctionA implements IFunction {

    @Override
    public double compute(double x,List<Double> functionArgs) {
        return (functionArgs.get(0) * pow(x, 3) + functionArgs.get(1) * pow(x, 2) +
                functionArgs.get(2) * x + functionArgs.get(3));
    }
}
