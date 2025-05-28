package iti.jets.model.mappers;

import iti.jets.model.entities.Size;
import iti.jets.model.enums.ShoeSize;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SizeMapper {

    default ShoeSize toShoeSize(Size size) {
        if (size == null) return null;
        return ShoeSize.fromValue(size.getSize());
    }

    default Size toSize(ShoeSize shoeSize) {
        if (shoeSize == null) return null;
        return new Size(shoeSize.getValue());
    }
}