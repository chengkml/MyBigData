package com.ck.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ck.controller.base.BaseControllerTest;
import com.ck.domain.DailyPlanItem;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.repository.DailyPlanItemRepository;
import com.ck.service.DailyPlanItemService;

public class DailyPlanItemControllerTest extends BaseControllerTest<DailyPlanItemPo,DailyPlanItem,DailyPlanItemDao,DailyPlanItemRepository,DailyPlanItemService,DailyPlanItemController>{

    @Test
    public void test() throws Exception {
        ResultActions resultActions = mock.perform(MockMvcRequestBuilders.get("/planItem/test"));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
        assertTrue(result.contains("test")&&!result.equals("test"));
    }
    
    @Test
    public void testSelectByConds() throws Exception {
        ResultActions resultActions = mock.perform(MockMvcRequestBuilders.get("/planItem/selectByConds?pageNum=0&pageSize=50"));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
    }

}
