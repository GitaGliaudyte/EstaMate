package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Property;

@Getter @Setter
public class PropertyDto {
    public String address;
    public Double price;
    public Integer id;
    public Integer taxesIdNo;

    public static PropertyDto convertToPropertyDto(Property property) {
        if (property != null) {
            PropertyDto dto = new PropertyDto();
            dto.setAddress(property.getAddress());
            dto.setPrice(property.getPrice());
            dto.setId(property.getId());
            dto.setTaxesIdNo(property.getTaxesIdNo());
            return dto;
        }
        return null;
    }
}
