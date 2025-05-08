public class M3 {

    final static double h = 0.1;

    // Method = False -> Difference Equation, Method = True -> Euler's Method
    private static Matrix lvModel(double whales, double krill, int time, boolean method) {
        Matrix t;
        Matrix population;
        TriFunction<Double, Double, Double, Double> whaleEq = (ti, b, k) -> -0.05 * b + (0.02 / 500) * b * k;
        TriFunction<Double, Double, Double, Double> krillEq = (ti, b, k) -> 0.25 * k - (0.10 / 150000) * b * k;

        if (!method) { //Difference equation
            time++;
            t = new Matrix(time,1);
            population = new Matrix(time,2); //Col 1 is whales, col 2 is krill
            population.setValue(1, 1,whales);
            population.setValue(1, 2,krill);

            //Iterating the difference equation (Euler's Method with h = 1)
            for (int i = 2; i <= time; i++) {
                t.setValue(i, 1, i - 1);
                whales += whaleEq.apply(0.0, whales, krill);
                krill += krillEq.apply(0.0, population.getValue(i - 1, 1), krill);
                whales = Math.round(whales);
                krill = Math.round(krill);
                population.setValue(i, 1, whales);
                population.setValue(i, 2, krill);
            }
        }
        else { //Euler's Method
            population = ODE.eulerSystem(whaleEq, krillEq, 0, whales, krill, time , h);
            t = LinearAlgebra.linSpace(0, time , h);
        }

        Matrix whalePop = LinearAlgebra.scaleMatrix(LinearAlgebra.vectorFromColumn(population, 1), 0.01);
        Matrix krillPop = LinearAlgebra.vectorFromColumn(population, 2);

        //Plotting the populations
        PyChart.plot(t, whalePop, "Whale Population (Divided by 100)", krillPop, "Krill Population Density (Tons per Acre)", "Year", "Population", "Whale and Krill Population");
        return population;
    }

    public static void main(String[] args) {
        //Part 1
        Matrix finalPop;

        // Q5
//        lvModel(75000, 150, 100, false);
        // Q7a
//        lvModel(75000, 300, 100, false);
        // Q7b
//        lvModel(150000, 300, 100, false);
        // Q8a
//        lvModel(150000, 100, 100, false);
        // Q8b
//        lvModel(150000, 400, 100, false);
        // Q8c
//        lvModel(100000, 300, 100, false);

        //Part 2

        // Q4
//        finalPop = lvModel(75000, 150, 5, true);

        // Q5
//        lvModel(75000, 150, 100, true);
        // Q7a
//        lvModel(75000, 300, 100, true);
        // Q7b
//        lvModel(150000, 300, 100, true);
        // Q8a
//        lvModel(150000, 100, 100, true);
        // Q8b
//        lvModel(150000, 400, 100, true);
        // Q8c
//        lvModel(100000, 300, 100, true);





    }

}
