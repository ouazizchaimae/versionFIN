package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import java.util.List;


@Repository
public interface ElementChimiqueDao extends AbstractRepository<ElementChimique,Long>  {
    ElementChimique findByCode(String code);
    int deleteByCode(String code);

    List<ElementChimique> findByUniteCode(String code);
    List<ElementChimique> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);

    @Query("SELECT NEW ElementChimique(item.id,item.libelle) FROM ElementChimique item")
    List<ElementChimique> findAllOptimized();

}
