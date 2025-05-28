package iti.jets.model.mappers;

import iti.jets.model.dtos.OrderDTO;
import iti.jets.model.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);
    Order toOrder(OrderDTO orderDTO);
}
