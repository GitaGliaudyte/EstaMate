package io.github.gitagliaudyte.estamate.rest.contracts;

import io.github.gitagliaudyte.estamate.entities.Property;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PropertyDto {
    private Integer id;
    private String address;
    private Double price;
    private Integer ownerId;

    public static PropertyDto convertToPropertyDto(Property property) {
        if(property != null) {
            PropertyDto propertyDto = new PropertyDto();
            propertyDto.setId(property.getId());
            propertyDto.setAddress(property.getAddress());
            propertyDto.setPrice(property.getPrice());
            propertyDto.setOwnerId(property.getOwner().getId());
            return propertyDto;
        }
        return null;
    }
}
