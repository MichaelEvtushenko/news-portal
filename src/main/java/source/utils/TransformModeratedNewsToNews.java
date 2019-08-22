package source.utils;

import source.entity.ModeratedNews;
import source.entity.News;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class TransformModeratedNewsToNews {
    public static Set<News> transform(Iterable<ModeratedNews> moderatedNews){
        if(moderatedNews==null)
            return null;
        Set<News> newsSet=new HashSet<>();
        for(ModeratedNews mn:moderatedNews)
            newsSet.addAll(mn.getNews());
        return newsSet;
    }
}
