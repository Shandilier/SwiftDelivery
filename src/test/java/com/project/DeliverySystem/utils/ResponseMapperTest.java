package com.project.DeliverySystem.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ResponseMapperTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getResponse() {
        Map<String, Object> response = ResponseMapper.getResponse(Constants.SUCCESS, "SUCCESS");
        Assert.assertEquals("SUCCESS", response.get("status"));
    }
}