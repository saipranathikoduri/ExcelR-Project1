package com.artforyou.store.config;

import com.artforyou.store.entity.CollectionEntity;
import com.artforyou.store.entity.Product;
import com.artforyou.store.repository.CollectionRepository;
import com.artforyou.store.repository.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Component
public class SeedDataLoader implements ApplicationRunner {
  private final CollectionRepository collections;
  private final ProductRepository products;
  private final ObjectMapper mapper;
  public SeedDataLoader(CollectionRepository c, ProductRepository p, ObjectMapper m) { collections=c; products=p; mapper=m; }
  @Override public void run(ApplicationArguments args) throws Exception {
    if (collections.count() > 0 || products.count() > 0) return;
    try (InputStream in = new ClassPathResource("seed-data.json").getInputStream()) {
      JsonNode root = mapper.readTree(in);
      int order=0;
      for (JsonNode node : root.path("collections")) {
        CollectionEntity c = new CollectionEntity(); c.setSlug(node.path("slug").asText()); c.setTitle(node.path("title").asText()); c.setSortOrder(order++); collections.save(c);
      }
      for (JsonNode node : root.path("products")) {
        Product p = new Product(); p.setCollection(node.path("collection").asText()); p.setCategory(node.path("category").asText()); p.setName(node.path("name").asText()); p.setPrice(node.path("price").asInt()); p.setImages(mapper.writeValueAsString(node.path("images"))); p.setSortOrder(node.path("sort_order").asInt()); p.setActive(true); products.save(p);
      }
    }
  }
}
