package pl.com.bottega.factory.shortages.prediction;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pl.com.bottega.factory.demand.forecasting.DemandEvents;
import pl.com.bottega.factory.shortages.prediction.monitoring.ShortagePredictionProcess;
import pl.com.bottega.factory.shortages.prediction.monitoring.ShortagePredictionProcessRepository;

@AllArgsConstructor
@Component
@Lazy
public class ShortagePredictionMapping implements DemandEvents {

    private ShortagePredictionProcessRepository repository;

    @Override
    public void emit(DemandedLevelsChanged event) {
        ShortagePredictionProcess model = repository.get(event.getId());
        model.onDemandChanged();
        repository.save(model);
    }

    //public void emit(ProductionChanged event) { service.onPlanChanged(event.getId().getRefNo()); }

    //public void emit(StockChanged event) { service.onStockChanged(event.getId().getRefNo()); }
}