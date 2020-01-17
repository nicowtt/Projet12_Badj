package com.esales.microservicebusiness.impl;

import com.esales.microservicebusiness.contract.SaleManager;
import com.esales.microservicedao.AddressDao;
import com.esales.microservicedao.ArticleDao;
import com.esales.microservicedao.SaleDao;
import com.esales.microservicemodel.dto.SaleDto;
import com.esales.microservicemodel.entity.Address;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.User;
import com.esales.microservicemodel.mapper.contract.SaleMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AddressDao addressDao;

    static final Log logger = LogFactory.getLog(SaleManagerImpl.class);

    @Override
    public List<Sale> getSalesByDateBeginAfterToday() {
        return saleDao.getSalesByDateBeginAfterToday();
    }

    /**
     * get Sales By DateBegin After Today With Nbr Of PreArticle Record
     * @param saleList
     * @param user
     * @return
     */
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

    /**
     * get Sales By Date Begin After Today
     * @param saleList
     * @return
     */
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

    /**
     * For add sale on bdd
     * @param saleDto
     * @return
     */
    @Override
    @Transactional
    public Sale addSale(SaleDto saleDto) {
        Address addressInputFromSaleDto;
        Address newAddress;
        Sale newSaleFromDto;

        // new address -> bdd
        addressInputFromSaleDto =saleMapper.fromSaleDtoToAddress(saleDto);

        newAddress = addressDao.save(addressInputFromSaleDto);

        // new sale -> bdd
        newSaleFromDto = saleMapper.fromSaleDtoToSale(saleDto);
        newSaleFromDto.setAddress(newAddress);
        Sale sale = saleDao.save(newSaleFromDto);

        return sale;
    }

    /**
     * get sale
     * @param dateBegin
     * @return
     */
    @Override
    public Sale getSale(String dateBegin) {
        return saleDao.getSale(dateBegin);
    }
}
