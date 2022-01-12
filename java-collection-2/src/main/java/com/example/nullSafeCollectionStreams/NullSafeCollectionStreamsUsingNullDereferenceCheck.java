package com.example.nullSafeCollectionStreams;

import java.util.Collection;
import java.util.stream.Stream;

/*
    Create by Atiye Mousavi 
    Date: 1/10/2022
    Time: 4:01 PM
**/
public class NullSafeCollectionStreamsUsingNullDereferenceCheck {

    public Stream<String> collectionAsStream(Collection<String> collection){
        return collection==null ? Stream.empty() : collection.stream();
    }
}
