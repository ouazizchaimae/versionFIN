package ma.zyn.app.dao.facade.core.expedition;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import java.util.List;


@Repository
public interface ExpeditionProduitDao extends AbstractRepository<ExpeditionProduit,Long>  {
    ExpeditionProduit findByCode(String code);
    int deleteByCode(String code);

    List<ExpeditionProduit> findByAnalyseChimiqueId(Long id);
    int deleteByAnalyseChimiqueId(Long id);
    long countByAnalyseChimiqueCode(String code);
    List<ExpeditionProduit> findByCharteChimiqueId(Long id);
    int deleteByCharteChimiqueId(Long id);
    long countByCharteChimiqueCode(String code);
    void deleteByExpeditionId(Long id);
    List<ExpeditionProduit> findByExpeditionId(Long id);
    @Query("SELECT NEW ExpeditionProduit(item.id,item.libelle) FROM ExpeditionProduit item")
    List<ExpeditionProduit> findAllOptimized();

}
