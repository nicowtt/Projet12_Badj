package com.esales.microservicebusiness.contract;
import com.esales.microservicemodel.dto.SaleDto;
import com.esales.microservicemodel.entity.Sale;
import com.esales.microservicemodel.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleManager {
    List<Sale> getSalesByDateEndAfterToday();
    List<SaleDto> getSalesByDateBeginAfterTodayWithNbrOfPreArticleRecord(List<Sale> saleList, User user);
    List<SaleDto> getSalesByDateBeginAfterToday(List<Sale> saleList);
    Sale addSale(SaleDto saleDto);
    Sale getSale(String dateBegin);
    boolean deleteSaleIfBeginDateIsAfterToday(SaleDto saleDto);
}
