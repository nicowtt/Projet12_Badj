package com.eSales.microserviceBusiness.impl;

import com.eSales.microserviceBusiness.contract.SaleManager;
import com.eSales.microserviceDao.ArticleDao;
import com.eSales.microserviceDao.SaleDao;
import com.eSales.microserviceModel.dto.SaleDto;
import com.eSales.microserviceModel.dto.UserDto;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
import com.eSales.microserviceModel.mapper.contract.SaleMapper;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SaleManagerImpl implements SaleManager {

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private SaleMapper saleMapper;

    static final Log logger = LogFactory.getLog(SaleManagerImpl.class);

    @Override
    public List<Sale> getSalesByDateBeginAfterToday() {
        return saleDao.getSalesByDateBeginAfterToday();
    }

    @Override
    public List<SaleDto> getSalesByDateBeginAfterTodayWithNbrOfPreArticleRecord(List<Sale> saleList, User user) {
        List<SaleDto> saleDtoList = new ArrayList<>();
        SaleDto saleDto;

        for (Sale inputSale: saleList) {
            saleDto = saleMapper.fromSaleToSaleDto(inputSale);
            if (user.getEmail() != null) {
                // get article number pre record
                Integer nbrArticlePreRecord = articleDao.getNbrOfArticlesForOneUserAndOneSale(user.getId(),saleDto.getId());
                saleDto.setNbrArticlesPreRecordForUser(nbrArticlePreRecord);
            }
            saleDtoList.add(saleDto);
        }
        return  saleDtoList;
    }

    @Override
    public List<SaleDto> getSalesByDateBeginAfterToday(List<Sale> saleList) {
        List<SaleDto> saleDtoList = new ArrayList<>();
        SaleDto saleDto;

        for (Sale inputSale: saleList) {
            saleDto = saleMapper.fromSaleToSaleDto(inputSale);
            saleDtoList.add((saleDto));
        }
        return saleDtoList;
    }


}
