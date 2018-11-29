package com.smilepasta.urchin.ui.common;

import java.io.Serializable;
import java.util.Map;

/**
 * Author: huangxiaoming
 * Date: 2018/8/15
 * Desc:
 * Version: 1.0
 */
public class SerializableMap implements Serializable {

    private Map<String, Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
