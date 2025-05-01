package ma.zyn.app.unit.dao.facade.core.expedition;

import ma.zyn.app.bean.core.expedition.Expedition;
import ma.zyn.app.dao.facade.core.expedition.ExpeditionDao;

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

import ma.zyn.app.bean.core.expedition.TypeExpedition ;
import ma.zyn.app.bean.core.referentiel.Client ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ExpeditionDaoTest {

@Autowired
    private ExpeditionDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Expedition entity = new Expedition();
        entity.setCode(code);
        underTest.save(entity);
        Expedition loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Expedition loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Expedition entity = new Expedition();
        entity.setId(id);
        underTest.save(entity);
        Expedition loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Expedition entity = new Expedition();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Expedition loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Expedition> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Expedition> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Expedition given = constructSample(1);
        Expedition saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Expedition constructSample(int i) {
		Expedition given = new Expedition();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setClient(new Client(1L));
        given.setTypeExpedition(new TypeExpedition(1L));
        return given;
    }

}
