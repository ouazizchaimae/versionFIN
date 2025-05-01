package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import java.util.List;


@Repository
public interface StadeOperatoireDao extends AbstractRepository<StadeOperatoire,Long>  {
    StadeOperatoire findByCode(String code);
    int deleteByCode(String code);

    List<StadeOperatoire> findByEntiteCode(String code);
    List<StadeOperatoire> findByEntiteId(Long id);
    int deleteByEntiteId(Long id);
    int deleteByEntiteCode(String code);
    long countByEntiteCode(String code);

    @Query("SELECT NEW StadeOperatoire(item.id,item.libelle) FROM StadeOperatoire item")
    List<StadeOperatoire> findAllOptimized();

}
