package com.eSales.microserviceBusiness.contract;
import com.eSales.microserviceModel.dto.SaleDto;
import com.eSales.microserviceModel.entity.Sale;
import com.eSales.microserviceModel.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface SaleManager {
    List<Sale> getSalesByDateBeginAfterToday();
    List<SaleDto> getSalesByDateBeginAfterTodayWithNbrOfPreArticleRecord(List<Sale> saleList, User user);
    List<SaleDto> getSalesByDateBeginAfterToday(List<Sale> saleList);
    Sale addSale(SaleDto saleDto);
    Sale getSale(String dateBegin);
}
