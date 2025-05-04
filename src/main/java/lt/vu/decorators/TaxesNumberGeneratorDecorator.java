package lt.vu.decorators;

import lt.vu.services.ITaxesNumberGenerator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class TaxesNumberGeneratorDecorator implements ITaxesNumberGenerator {
    @Inject
    @Delegate
    @Any
    private ITaxesNumberGenerator taxesNumberGenerator;

    @Override
    public Integer generateTaxesNumber() {
        try {
            Integer generatedTaxesNumber = taxesNumberGenerator.generateTaxesNumber();
            Thread.sleep(3000);
            System.out.println("DECORATOR TASK DONE");
            return generatedTaxesNumber * 10;
        } catch (InterruptedException e) {
            return 0;
        }
    }
}
