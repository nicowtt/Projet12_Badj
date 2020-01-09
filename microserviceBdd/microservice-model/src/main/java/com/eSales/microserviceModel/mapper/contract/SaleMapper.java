package com.eSales.microserviceModel.mapper.contract;

import com.eSales.microserviceModel.dto.SaleDto;
import com.eSales.microserviceModel.entity.Sale;
import org.springframework.stereotype.Service;

@Service
public interface SaleMapper {
    SaleDto fromSaleToSaleDto(Sale sale);
}
