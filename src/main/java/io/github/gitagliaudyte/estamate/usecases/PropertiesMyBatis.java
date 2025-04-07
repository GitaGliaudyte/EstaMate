package io.github.gitagliaudyte.estamate.usecases;

import io.github.gitagliaudyte.estamate.mybatis.dao.OwnerMapper;
import io.github.gitagliaudyte.estamate.mybatis.dao.PropertyMapper;
import io.github.gitagliaudyte.estamate.mybatis.model.Owner;
import io.github.gitagliaudyte.estamate.mybatis.model.Property;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class PropertiesMyBatis {
//
//    @Inject
//    SqlSession sqlSession;

    @Inject
    private PropertyMapper propertyMapper;

    @Inject
    private OwnerMapper ownerMapper;

    @Getter @Setter
    private Owner owner;

    @Getter @Setter
    private Property property;

    @PostConstruct
    public void init(){

        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
        String propertyIdParam = params.get("propertyId");
        if (propertyIdParam != null) {
            try {
                this.property = propertyMapper.selectByPrimaryKey(Integer.parseInt(propertyIdParam));
                this.owner = ownerMapper.selectByPrimaryKey(this.property.getOwnerId());
            } catch (NumberFormatException e) {}
        }
    }
}
