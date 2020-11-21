package functions;

import java.util.List;

import static java.lang.Math.*;

public class FunctionD implements  IFunction {



    @Override
    public double compute(double x,List<Double> functionArgs) {
        return functionArgs.get(0) * sin(functionArgs.get(1) * x + functionArgs.get(2)) * pow(Math.E, -x /
                functionArgs.get(3)) + functionArgs.get(4);
    }
}
