package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.UnitOfMeasureCommand;
import jaep.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import jaep.springframework.recipeapp.domain.UnitOfMeasure;
import jaep.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository repository;
    private final UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository,
                                    UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommand) {
        this.repository = repository;
        this.toUnitOfMeasureCommand = toUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> getAllUnitOfMeasurement() {

        return StreamSupport.stream(repository.findAll()
                .spliterator(), false)
                .map(toUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitOfMeasureCommand getUnitOfMeasurementId(Long id) {
        Optional<UnitOfMeasure> result = repository.findById(id);

        if (!result.isPresent()){
            log.debug("Not unit of measure find for ID: " + id);
            throw new RuntimeException("Not unit of measure find for ID: " + id);
        }

        return toUnitOfMeasureCommand.convert(result.get());
    }
}
