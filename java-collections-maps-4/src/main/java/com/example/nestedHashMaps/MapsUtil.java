package com.example.nestedHashMaps;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Super;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 12:38 PM
**/
public class MapsUtil {

    MapsUtil() {
        super();
    }

    public Map<Integer, String> buildInnerMap(List<String> batterList) {
        Map<Integer, String> innerBatterMap = new HashMap<>();

        int index = 1;
        for (String item : batterList) {
            innerBatterMap.put(index, item);
            index++;
        }
        return innerBatterMap;
    }

    public Map<Integer, Map<String, String>> createNestedMapfromStream(List<Employee> listEmployee) {
        Map<Integer, Map<String, String>> employeeAddressMap = listEmployee.stream()
                .collect(Collectors.groupingBy(e -> e.getAddress().getAddressId(),
                        Collectors.toMap(f -> f.getAddress().getAddressLocation(), Employee::getEmployeeName)));
        return employeeAddressMap;
    }
    public Map<Integer, Map<Integer, Address>> createNestedObjectMap(List<Employee> listEmployee) {
        Map<Integer, Map<Integer, Address>> employeeMap = new HashMap<>();

        employeeMap = listEmployee.stream().collect(Collectors.groupingBy((Employee emp) -> emp.getEmployeeId(),
                Collectors.toMap((Employee emp) -> emp.getAddress().getAddressId(), fEmpObj -> fEmpObj.getAddress())));

        return employeeMap;
    }
    public Map<String, String> flattenMap(Map<?, ?> source) {
        Map<String, String> converted = new HashMap<>();

        for (Map.Entry<?, ?> entry : source.entrySet()) {
            if (entry.getValue() instanceof Map) {
                flattenMap((Map<String, Object>) entry.getValue())
                        .forEach((key, value) -> converted.put(entry.getKey() + "." + key, value));
            } else {
                converted.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        return converted;
    }
}
