package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "unite")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="unite_seq",sequenceName="unite_seq",allocationSize=1, initialValue = 1)
public class Unite  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    @Column(length = 500)
    private String style;



    public Unite(){
        super();
    }

    public Unite(Long id){
        this.id = id;
    }

    public Unite(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Unite(String libelle){
        this.libelle = libelle ;
    }
    public Unite(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="unite_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unite unite = (Unite) o;
        return id != null && id.equals(unite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

