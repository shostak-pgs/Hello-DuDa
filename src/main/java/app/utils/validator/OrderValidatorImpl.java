package app.utils.validator;

import app.entity.Good;
import app.service.impl.GoodsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatorImpl {
   private static final String GOOD_NOT_FOUND = "Good not Found";

   private GoodsServiceImpl goodsService;

   public OrderValidatorImpl(GoodsServiceImpl goodsService) {
      this.goodsService = goodsService;
   }

   /**
    * Validate if the good is exist
    * @param good the good to check
    */
   public void validate(Good good) {
      if (!goodsService.getGoods().contains(good)) {
         throw new IllegalArgumentException(GOOD_NOT_FOUND);
       }
   }
}
