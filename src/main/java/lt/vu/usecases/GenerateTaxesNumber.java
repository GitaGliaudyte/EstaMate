package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.TaxesNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateTaxesNumber implements Serializable {
    @Inject
    TaxesNumberGenerator taxesNumberGenerator;

    @Getter @Setter
    private Integer taxesNumber;

    @Setter @Getter
    private Integer generatedNum;

    private CompletableFuture<Integer> taxesNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewTaxesNumber() {
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        taxesNumberGenerationTask = CompletableFuture.supplyAsync(() -> taxesNumberGenerator.generateTaxesNumber())
                .thenApply(number -> {
                    // Store the generated number
                    this.generatedNum = number;
                    return number;
                });
        return  "/taxesNumber.xhtml?faces-redirect=true&propertyId=" + requestParameters.get("propertyId");
    }

    public boolean isGenerationRunning() {
        return taxesNumberGenerationTask != null && !taxesNumberGenerationTask.isDone();
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if (taxesNumberGenerationTask == null) {
            return null;
        } else if (isGenerationRunning()) {
            return "Taxes number generation status is in progress..";
        }
        generatedNum = taxesNumberGenerationTask.get();
        return "Suggested taxes number: " + generatedNum;
    }
}
