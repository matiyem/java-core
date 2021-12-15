package com.example.spliteratorAPI;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 2:24 PM
 **/
public class RelatedAuthorCounter {

    private final int counter;
    private final boolean isRelated;

    public RelatedAuthorCounter(int counter, boolean isRelated) {
        this.counter = counter;
        this.isRelated = isRelated;
    }
    public RelatedAuthorCounter accumlate(Author author){
        if (author.getRelatedArticleId() == 0){
            return isRelated ? this :new RelatedAuthorCounter(counter,true);
        }else{
            return isRelated ? new RelatedAuthorCounter(counter+1,false):this;

        }
    }
    public RelatedAuthorCounter combine(RelatedAuthorCounter relatedAuthorCounter){
        return new RelatedAuthorCounter(counter + relatedAuthorCounter.counter,relatedAuthorCounter.isRelated);
    }

    public int getCounter() {
        return counter;
    }
}
