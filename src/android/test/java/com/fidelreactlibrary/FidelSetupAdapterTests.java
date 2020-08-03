package com.fidelcordovalibrary;

import com.fidel.sdk.Fidel;
import com.fidelcordovalibrary.adapters.FidelSetupAdapter;
import com.fidelcordovalibrary.fakes.ReadableMapStub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class FidelSetupAdapterTests {

    private static final String TEST_API_KEY = "pk_123123123";
    private static final String TEST_PROGRAM_ID = "234234";
    
    private FidelSetupAdapter sut;
    
    @Before
    public final void setUp() {
        sut = new FidelSetupAdapter();
    }
    
    @After
    public final void tearDown() {
        sut = null;
        Fidel.apiKey = null;
        Fidel.programId = null;
    }

    @Test
    public void test_WhenDataHasNoApiKey_DontSetItToSDK() {
        ReadableMapStub mapStub = ReadableMapStub.mapWithNoKey();
        sut.process(mapStub);
        assertNull(Fidel.apiKey);
    }

    @Test
    public void test_WhenDataHasNoProgramIDKey_DontSetItToSDK() {
        ReadableMapStub mapStub = ReadableMapStub.mapWithNoKey();
        sut.process(mapStub);
        assertNull(Fidel.programId);
    }

    @Test
    public void test_WhenApiKeyIsSet_SetItToSDK() {
        String expectedValue = TEST_API_KEY;
        processWithString(FidelSetupAdapter.API_KEY, expectedValue);
        assertEquals(expectedValue, Fidel.apiKey);
    }

    @Test
    public void test_WhenProgramIDIsSet_SetItToSDK() {
        String expectedValue = TEST_PROGRAM_ID;
        processWithString(FidelSetupAdapter.PROGRAM_ID_KEY, expectedValue);
        assertEquals(expectedValue, Fidel.programId);
    }

    private void processWithString(String key, String value) {
        ReadableMapStub mapStub = ReadableMapStub.mapWithExistingKey(key);
        mapStub.stringForKeyToReturn.put(key, value);
        sut.process(mapStub);
    }
}
