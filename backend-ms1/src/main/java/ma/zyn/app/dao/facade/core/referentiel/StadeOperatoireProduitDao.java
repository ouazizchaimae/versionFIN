package ma.zyn.app.dao.facade.core.referentiel;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StadeOperatoireProduitDao extends AbstractRepository<StadeOperatoireProduit,Long>  {

    List<StadeOperatoireProduit> findByStadeOperatoireCode(String code);
    List<StadeOperatoireProduit> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<StadeOperatoireProduit> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);


}
