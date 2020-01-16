package com.esales.microservicemodel.mapper.impl;

import com.esales.microservicemodel.dto.SaleDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.mapper.contract.SaleMapper;
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

    /**
     * from saleDto to address
     * @param saleDto
     * @return
     */
    @Override
    public Address fromSaleDtoToAddress(SaleDto saleDto) {
        Address address = new Address();
        address.setStreet(saleDto.getAddress().getStreet());
        address.setPostalCode(saleDto.getAddress().getPostalCode());
        address.setCity(saleDto.getAddress().getCity());
        return address;
    }

    /**
     * from saleDto to sale
     * @param saleDto
     * @return
     */
    @Override
    public Sale fromSaleDtoToSale(SaleDto saleDto) {
        Sale sale = new Sale();
        sale.setDescription(saleDto.getDescription());
        sale.setType(saleDto.getType());
        sale.setDateBegin(saleDto.getDateBegin());
        sale.setDateEnd(saleDto.getDateEnd());
        return sale;
    }
}
