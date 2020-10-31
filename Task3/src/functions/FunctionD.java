package functions;

import java.util.List;

import static java.lang.Math.*;

public class FunctionD implements  IFunction {
    private final List<Double> listArgs;


    public FunctionD(List<Double> listArgs) {
        this.listArgs = listArgs;
    }

    public Double getArg(int i) {
        return listArgs.get(i);
    }



    @Override
    public double compute(double x) {
        return listArgs.get(0) * sin(listArgs.get(1) * x + listArgs.get(2)) * pow(Math.E, -x /
                listArgs.get(3)) + listArgs.get(4);
    }
}
