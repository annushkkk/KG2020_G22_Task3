package functions;

import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class FunctionC implements IFunction {


    @Override
    public double compute(double x,List<Double> functionArgs) {
        return functionArgs.get(0) * sin(functionArgs.get(1) * x + functionArgs.get(2)) * (functionArgs.get(3) *
                cos(functionArgs.get(4) * x + functionArgs.get(5)) + functionArgs.get(6)) + functionArgs.get(7);
    }
}
