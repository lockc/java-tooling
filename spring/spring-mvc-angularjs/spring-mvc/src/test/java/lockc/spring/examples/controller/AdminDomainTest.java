/********************************************************************
 *                                                                  *
 *             Lockheed Martin Corporation Confidential             *
 *                                                                  *
 * Copyright (c) 2015 LOCKHEED MARTIN CORPORATION as an unpublished *
 * work. All rights reserved. This computer software is a TRADE     *
 * SECRET of Lockheed Martin Corporation and shall not be           *
 * reproduced, disclosed, or used without prior written permission  *
 * of Lockheed Martin Corporation.                                  *
 *                                                                  *
 ********************************************************************
 */
package lockc.spring.examples.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lockc.spring.examples.domain.ExampleEntity;

import org.junit.Before;
import org.junit.Test;

public class AdminDomainTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void testSearchStandingDataConstruction()
    {
        ExampleEntity obj = new ExampleEntity();
        obj.setRoles(new HashSet<String>(Arrays.asList("ROLE_A", "ROLE_B")));
        obj.setServiceProviders(new HashSet<String>(Arrays.asList("SP_ONE", "SP_TWO")));

        Set<String> expRoles = new HashSet<String>(Arrays.asList("ROLE_A", "ROLE_B"));
        Set<String> expServiceProviders = new HashSet<String>(Arrays.asList("SP_ONE", "SP_TWO"));

        assertEquals(expRoles, obj.getRoles());
        assertEquals(expServiceProviders, obj.getServiceProviders());
    }

}
