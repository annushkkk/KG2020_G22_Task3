package functions;

import java.util.List;

import static java.lang.Math.sin;

public class FunctionB implements IFunction {
    private final List<Double> listArgs;


    public FunctionB(List<Double> listArgs) {
        this.listArgs = listArgs;
    }

    public Double getArg(int i) {
        return listArgs.get(i);
    }



    @Override
    public double compute(double x) {
        return (listArgs.get(0) * sin(listArgs.get(1) * x + listArgs.get(2)) + listArgs.get(3));
    }
}
