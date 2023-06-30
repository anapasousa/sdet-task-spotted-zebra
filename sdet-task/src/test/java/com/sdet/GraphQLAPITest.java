package com.sdet;
import java.io.IOException;

public class GraphQLAPITest {
    public static void main(String[] args) {
        try {
            runTests();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runTests() throws IOException {
        PositiveScenariosTest positiveScenarioTest = new PositiveScenariosTest();
        positiveScenarioTest.runPositiveScenarios();

        NegativeScenariosTest negativeScenarioTest = new NegativeScenariosTest();
        negativeScenarioTest.runNegativeScenarios();
    }

}
