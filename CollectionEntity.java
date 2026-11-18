package com.artforyou.store.entity;
import jakarta.persistence.*;
@Entity @Table(name="collections") public class CollectionEntity { @Id private String slug; @Column(nullable=false) private String title; @Column(name="sort_order") private int sortOrder; public String getSlug(){return slug;} public String getTitle(){return title;} public int getSortOrder(){return sortOrder;} public void setSlug(String v){slug=v;} public void setTitle(String v){title=v;} public void setSortOrder(int v){sortOrder=v;} }
