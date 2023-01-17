package com.game.entity;

import com.game.controller.PlayerOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PlayerPageable {
    private PlayerOrder order;
    private Integer pageNumber;
    private Integer pageSize;

    private static PlayerOrder ORDER_DEFAULT = PlayerOrder.ID;
    private static Integer PAGE_NUMBER_DEFAULT = 0;
    private static Integer PAGE_SIZE_DEFAULT = 3;

    public PlayerPageable(PlayerOrder order, Integer pageNumber, Integer pageSize) {
        this.order = (order != null) ? order : ORDER_DEFAULT;
        this.pageNumber = (pageNumber != null) ? pageNumber : PAGE_NUMBER_DEFAULT;
        this.pageSize = (pageSize != null) ? pageSize : PAGE_SIZE_DEFAULT;
    }

    public PageRequest getPageable() {
        return PageRequest.of(pageNumber, pageSize, Sort.by(String.valueOf(order.getFieldName())));
    }

}
