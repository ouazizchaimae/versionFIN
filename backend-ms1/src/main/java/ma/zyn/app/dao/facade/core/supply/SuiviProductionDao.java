package ma.zyn.app.dao.facade.core.supply;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.supply.SuiviProduction;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.supply.SuiviProduction;
import java.util.List;


@Repository
public interface SuiviProductionDao extends AbstractRepository<SuiviProduction,Long>  {
    SuiviProduction findByCode(String code);
    int deleteByCode(String code);

    List<SuiviProduction> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<SuiviProduction> findByStadeOperatoireCode(String code);
    List<SuiviProduction> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<SuiviProduction> findByUniteCode(String code);
    List<SuiviProduction> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);

    @Query("SELECT NEW SuiviProduction(item.id,item.libelle) FROM SuiviProduction item")
    List<SuiviProduction> findAllOptimized();

}
