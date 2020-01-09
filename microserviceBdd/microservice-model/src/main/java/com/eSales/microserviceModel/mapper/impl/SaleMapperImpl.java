package com.eSales.microserviceModel.mapper.impl;

import com.eSales.microserviceModel.dto.SaleDto;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.mapper.contract.SaleMapper;
import org.springframework.stereotype.Component;

@Component
public class SaleMapperImpl implements SaleMapper {

    /**
     * from Sale to SaleDto
     * @param sale -> input
     * @return saleDto
     */
    @Override
    public SaleDto fromSaleToSaleDto(Sale sale) {
        SaleDto saleDto = new SaleDto();
        saleDto.setId(sale.getId());
        saleDto.setType(sale.getType());
        saleDto.setDescription(sale.getDescription());
        saleDto.setDateBegin(sale.getDateBegin());
        saleDto.setDateEnd(sale.getDateEnd());
        saleDto.setAddress(sale.getAddress());

        return saleDto;
    }

}
