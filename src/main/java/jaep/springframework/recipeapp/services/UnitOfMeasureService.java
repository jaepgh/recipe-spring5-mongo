package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> getAllUnitOfMeasurement();
    UnitOfMeasureCommand getUnitOfMeasurementId(String id);
}
