package com.example.collection.convertArrayPrimitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.ArrayUtils;


/**
 * Create by Atiye Mousavi
 * Date: 1/8/2022
 * Time: 10:39 AM
 **/
public class ConvertPrimitivesArrayToList {
    public static void failConvert(){
        int[] input=new int[]{1,2,3,4};
    }
    public static List<Integer> iterateConvert(int[] input){
        List<Integer> output=new ArrayList<>();
        for (int value:input){
            output.add(value);
        }
        return output;
    }
    public static List<Integer> streamConvert(int[] input){
        List<Integer> output= Arrays.stream(input).boxed().collect(Collectors.toList());
        return output;
    }
    public static List streamConvertIntStream(int[] input){
        List<Integer> output= IntStream.of(input).boxed().collect(Collectors.toList());
        return output;
    }
    public static List<Integer> guavaConvert(int[] input ){
        List<Integer> output=Ints.asList(input);
        return output;
    }
    public static List<Integer> apacheCommonConvert(int[] input){
        Integer[] outputBoxed= ArrayUtils.toObject(input);
        List<Integer> output=Arrays.asList(outputBoxed);
        return output;
    }
}
