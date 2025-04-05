package io.github.gitagliaudyte.estamate.rest.contracts;

import io.github.gitagliaudyte.estamate.entities.Owner;
import io.github.gitagliaudyte.estamate.entities.Property;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerPropertyDto {
    public String address;
    public Double price;
    public Owner owner;

    public static OwnerPropertyDto convertToOwnerPropertyDto(Property property) {
        if(property != null) {
            OwnerPropertyDto ownerPropertyDto = new OwnerPropertyDto();
            ownerPropertyDto.setAddress(property.getAddress());
            ownerPropertyDto.setPrice(property.getPrice());
            ownerPropertyDto.setOwner(property.getOwner());
            return ownerPropertyDto;
        }
        return null;
    }
}
