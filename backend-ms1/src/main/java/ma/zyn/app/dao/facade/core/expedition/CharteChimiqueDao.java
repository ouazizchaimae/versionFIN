package ma.zyn.app.dao.facade.core.expedition;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.expedition.CharteChimique;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.expedition.CharteChimique;
import java.util.List;


@Repository
public interface CharteChimiqueDao extends AbstractRepository<CharteChimique,Long>  {
    CharteChimique findByCode(String code);
    int deleteByCode(String code);

    List<CharteChimique> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);
    List<CharteChimique> findByProduitMarchandCode(String code);
    List<CharteChimique> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);

    @Query("SELECT NEW CharteChimique(item.id,item.libelle) FROM CharteChimique item")
    List<CharteChimique> findAllOptimized();

}
