package com.thomsonreuters.pageobjects.common;

public class Link {

    private String href;
    private String title;

    public Link(String href, String linkText){
        this.href = href;
        this.title = linkText;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Link{" +
                "href='" + href + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!href.equals(link.href)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = href.hashCode();
        result = 31 * result;
        return result;
    }
}
