package org.tttamics.scrapper.retrieval.federacio.barcelonesa.dto;

import java.util.Collections;
import java.util.List;

public class CategoryUrl {
    private String category;
    private List<GroupUrl> urls;

    public CategoryUrl(String category, List<GroupUrl> urls) {
        this.category = category;
        this.urls = urls;
    }

    public String getCategory() {
        return category;
    }

    public List<GroupUrl> getUrls() {
        return Collections.unmodifiableList(urls);
    }

    @Override
    public String toString() {
        return "CategoryUrl{" +
                "category='" + category + '\'' +
                ", urls=" + urls +
                '}';
    }
}
