package functions;


import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class FunctionG implements IFunction {


  @Override
  public double compute(double y,List<Double> functionArgs) {
    return functionArgs.get(0) * pow(y, 3) + functionArgs.get(1) * pow(y, 2) + functionArgs.get(2) *
            y + functionArgs.get(3);
  }
}
