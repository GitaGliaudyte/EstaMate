package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class TaxesNumberGenerator implements ITaxesNumberGenerator {
    public Integer generateTaxesNumber() {
        try {
            Thread.sleep(3000);
            System.out.println("THREAD SLEEP DONE");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return new Random().nextInt(100);
    }
}
