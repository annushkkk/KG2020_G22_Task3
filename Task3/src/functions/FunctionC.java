package functions;

import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class FunctionC implements IFunction {
    private final List<Double> listArgs;


    public FunctionC(List<Double> listArgs) {
        this.listArgs = listArgs;
    }

    public Double getArg(int i) {
        return listArgs.get(i);
    }



    @Override
    public double compute(double x) {
        return listArgs.get(0) * sin(listArgs.get(1) * x + listArgs.get(2)) * (listArgs.get(3) *
                cos(listArgs.get(4) * x + listArgs.get(5)) + listArgs.get(6)) + listArgs.get(7);
    }
}
