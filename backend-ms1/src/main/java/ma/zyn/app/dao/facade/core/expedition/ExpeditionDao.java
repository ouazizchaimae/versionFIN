package ma.zyn.app.dao.facade.core.expedition;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.expedition.Expedition;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.expedition.Expedition;
import java.util.List;


@Repository
public interface ExpeditionDao extends AbstractRepository<Expedition,Long>  {
    Expedition findByCode(String code);
    int deleteByCode(String code);

    List<Expedition> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);
    List<Expedition> findByTypeExpeditionCode(String code);
    List<Expedition> findByTypeExpeditionId(Long id);
    int deleteByTypeExpeditionId(Long id);
    int deleteByTypeExpeditionCode(String code);
    long countByTypeExpeditionCode(String code);

    @Query("SELECT NEW Expedition(item.id,item.libelle) FROM Expedition item")
    List<Expedition> findAllOptimized();

}
