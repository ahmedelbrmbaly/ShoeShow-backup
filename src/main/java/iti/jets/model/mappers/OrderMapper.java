package iti.jets.model.mappers;

import iti.jets.model.dtos.OrderDTO;
import iti.jets.model.dtos.OrderManageDTO;
import iti.jets.model.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);

    @Mapping(source = "user.email", target = "email")
    OrderManageDTO toOrderManageDto(Order order);

    Order toOrder(OrderDTO orderDTO);
}
