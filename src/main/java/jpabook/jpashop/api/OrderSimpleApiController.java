package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.SimpleData;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne (ManyToOne,OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("api/v2/simple-orders")
    public SimpleData<List<OrderSimpleQueryDto>> orderV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderSimpleQueryDto> collect = orders.stream()
                .map(OrderSimpleQueryDto::new)
                .collect(Collectors.toList());
        return new SimpleData<List<OrderSimpleQueryDto>>(collect);
    }


    @GetMapping("api/v3/simple-orders")
    public SimpleData<List<OrderSimpleQueryDto>> orderV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return new SimpleData<>(orders.stream()
                .map(o -> new OrderSimpleQueryDto(o))
                .collect(Collectors.toList()));
    }

    @GetMapping("api/v4/simple-orders")
    public SimpleData<List<OrderSimpleQueryDto>> orderV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }






}

