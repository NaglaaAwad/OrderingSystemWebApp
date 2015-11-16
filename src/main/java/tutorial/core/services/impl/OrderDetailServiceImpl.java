package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.OrderDetail;
import tutorial.core.repositories.OrderDetailRepo;
import tutorial.core.services.OrderDetailService;

/**
 * Created by Naglaa on 10/19/2015.
 */
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepo OrderDetailRepo;

    @Override
    public OrderDetail findOrderDetail(Long id) {
        return OrderDetailRepo.findOrderDetail(id);
    }

    @Override
    public OrderDetail deleteOrderDetail(Long id) {
        return OrderDetailRepo.deleteOrderDetail(id);
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetail data) {
        return OrderDetailRepo.updateOrderDetail(id, data);
    }

}
