package ma.zyn.app.dao.facade.core.referentiel;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.RatioUnite;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RatioUniteDao extends AbstractRepository<RatioUnite,Long>  {

    List<RatioUnite> findByEntiteCode(String code);
    List<RatioUnite> findByEntiteId(Long id);
    int deleteByEntiteId(Long id);
    int deleteByEntiteCode(String code);
    long countByEntiteCode(String code);
    List<RatioUnite> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);


}
