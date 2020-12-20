package guru.springframework.repository;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescriptionTeaspoon() {
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        Assert.assertEquals("Teaspoon", unitOfMeasure.getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByDescription("Cup").get();
        Assert.assertEquals("Cup", unitOfMeasure.getDescription());
    }
}