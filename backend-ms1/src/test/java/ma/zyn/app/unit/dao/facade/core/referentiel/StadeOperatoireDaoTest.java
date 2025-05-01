package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.dao.facade.core.referentiel.StadeOperatoireDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.referentiel.Entite ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class StadeOperatoireDaoTest {

@Autowired
    private StadeOperatoireDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        StadeOperatoire entity = new StadeOperatoire();
        entity.setCode(code);
        underTest.save(entity);
        StadeOperatoire loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        StadeOperatoire loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        StadeOperatoire entity = new StadeOperatoire();
        entity.setId(id);
        underTest.save(entity);
        StadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        StadeOperatoire entity = new StadeOperatoire();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        StadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<StadeOperatoire> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<StadeOperatoire> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        StadeOperatoire given = constructSample(1);
        StadeOperatoire saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private StadeOperatoire constructSample(int i) {
		StadeOperatoire given = new StadeOperatoire();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setCapaciteMin(BigDecimal.TEN);
        given.setCapaciteMax(BigDecimal.TEN);
        given.setIndice(i);
        given.setEntite(new Entite(1L));
        return given;
    }

}
