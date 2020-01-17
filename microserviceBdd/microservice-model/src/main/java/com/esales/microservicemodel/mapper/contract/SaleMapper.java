package com.esales.microservicemodel.mapper.contract;

import com.esales.microservicemodel.dto.SaleDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.Sale;
import org.springframework.stereotype.Service;

@Service
public interface SaleMapper {
    SaleDto fromSaleToSaleDto(Sale sale);
    Address fromSaleDtoToAddress(SaleDto saleDto);
    Sale fromSaleDtoToSale(SaleDto saleDto);
}
