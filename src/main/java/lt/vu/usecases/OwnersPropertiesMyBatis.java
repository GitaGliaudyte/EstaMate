package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.OwnerMapper;
import lt.vu.mybatis.model.Owner;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class OwnersPropertiesMyBatis implements Serializable {

    @Inject
    private OwnerMapper ownerMapper;

    @Getter @Setter
    private List<Owner> owners;

    @PostConstruct
    public void init() {
        this.owners = ownerMapper.selectAllWithProperties();
    }

}
