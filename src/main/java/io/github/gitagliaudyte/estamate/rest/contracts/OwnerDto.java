package io.github.gitagliaudyte.estamate.rest.contracts;

import io.github.gitagliaudyte.estamate.entities.Owner;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerDto {
    private Integer id;
    private String name;

    public static OwnerDto convertToOwnerDto(Owner owner) {
        if(owner != null) {
            OwnerDto ownerDto = new OwnerDto();
            ownerDto.setId(owner.getId());
            ownerDto.setName(owner.getName());
            return ownerDto;
        }
        return null;
    }
}
