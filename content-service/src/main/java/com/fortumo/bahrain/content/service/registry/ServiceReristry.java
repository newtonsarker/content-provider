package com.fortumo.bahrain.content.service.registry;

import java.util.HashMap;
import java.util.Map;

public class ServiceReristry {

    private static Map<String, Register> registers = new HashMap<>();

    static {
        registers.put("TXT", new Register("TXT", "POST", "application/json", "http://bratwurst.fortumo.mobi/api/sms/txt"));
        registers.put("FOR", new Register("FOR", "POST", "application/json", "https://bratwurst.fortumo.mobi/api/sms/for"));
    }

    public static Register getRegister (String routeName) {
        return registers.get(routeName);
    }

}
