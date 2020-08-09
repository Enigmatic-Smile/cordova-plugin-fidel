package com.fidelcordovalibrary.helpers;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public final class AssertHelpers {
    static public void assertMapContainsMap(@Nullable Map<String, Object> map,
                                            @Nullable Map<String, Object> mapToContain) {
        if (map == null || mapToContain == null) {
            fail("The maps to check should not be null");
        }
        Set<Map.Entry<String, Object>> mapEntriesSet = map.entrySet();
        for (Map.Entry<String, Object> entry : mapToContain.entrySet()) {
            assertThat(mapEntriesSet, hasItem(entry));
        }
    }


    static public void assertMapEqualsWithJSONObject(HashMap<String, Object> map, JSONObject json) {
        assertEquals(map.keySet().size(), json.length());
        if (json.length() > 0) {
            Iterator<String> jsonKeyIterator = json.keys();
            while (jsonKeyIterator.hasNext()) {
                String key = jsonKeyIterator.next();
                assertThat(map.keySet(), hasItem(key));
                try {
                    Object mapValue = map.get(key);
                    Object jsonValue = json.get(key);
                    Class<?>[] interfaces = mapValue.getClass().getInterfaces();
                    boolean didFindInternalObjects = false;
                    for (Class<?> interfaceObj: interfaces) {
                        if (interfaceObj == JSONObject.class) {
                            if (jsonValue.getClass() == JSONObject.class) {
                                HashMap<String, Object> internalMap = new Gson().fromJson(mapValue.toString(), HashMap.class);
                                assertMapEqualsWithJSONObject(internalMap, (JSONObject) jsonValue);
                                didFindInternalObjects = true;
                            }
                            else {
                                fail("Unkown internal JSON type");
                            }
                            break;
                        }
                    }
                    if (!didFindInternalObjects) {
                        if ((mapValue.getClass() == Double.class || mapValue.getClass() == double.class) &&
                                (jsonValue.getClass() == Integer.class || jsonValue.getClass() == int.class)) {
                            Integer intValue = ((Double)mapValue).intValue();
                            assertEquals(intValue, jsonValue);
                        } else {
                            assertEquals(mapValue, jsonValue);
                        }
                    }
                }
                catch (JSONException e) {
                    fail(e.toString());
                }
            }
        }
    }
}
