package jpabook.jpashop.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleData<T>{
    private T data;


}