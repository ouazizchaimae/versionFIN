package ma.zyn.app.dao.facade.core.demande;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.demande.Demande;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.demande.Demande;
import java.util.List;


@Repository
public interface DemandeDao extends AbstractRepository<Demande,Long>  {
    Demande findByCode(String code);
    int deleteByCode(String code);

    List<Demande> findByProduitMarchandCode(String code);
    List<Demande> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);
    List<Demande> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);
    List<Demande> findByTypeDemandeCode(String code);
    List<Demande> findByTypeDemandeId(Long id);
    int deleteByTypeDemandeId(Long id);
    int deleteByTypeDemandeCode(String code);
    long countByTypeDemandeCode(String code);
    List<Demande> findByEtatDemandeCode(String code);
    List<Demande> findByEtatDemandeId(Long id);
    int deleteByEtatDemandeId(Long id);
    int deleteByEtatDemandeCode(String code);
    long countByEtatDemandeCode(String code);

    @Query("SELECT NEW Demande(item.id,item.libelle) FROM Demande item")
    List<Demande> findAllOptimized();

}
