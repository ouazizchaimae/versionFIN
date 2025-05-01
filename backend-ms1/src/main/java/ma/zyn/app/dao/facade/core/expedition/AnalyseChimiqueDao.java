package ma.zyn.app.dao.facade.core.expedition;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import java.util.List;


@Repository
public interface AnalyseChimiqueDao extends AbstractRepository<AnalyseChimique,Long>  {
    AnalyseChimique findByCode(String code);
    int deleteByCode(String code);

    List<AnalyseChimique> findByProduitMarchandCode(String code);
    List<AnalyseChimique> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);

    @Query("SELECT NEW AnalyseChimique(item.id,item.libelle) FROM AnalyseChimique item")
    List<AnalyseChimique> findAllOptimized();

}
