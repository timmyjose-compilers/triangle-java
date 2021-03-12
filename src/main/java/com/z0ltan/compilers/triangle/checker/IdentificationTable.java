package com.z0ltan.compilers.triangle.checker;

import java.util.Map;
import java.util.HashMap;

import com.z0ltan.compilers.triangle.ast.Declaration;

public class IdentificationTable {
  private Map<Integer, Map<String, Declaration>> table;
  private int level;

  public IdentificationTable() {
    this.table = new HashMap<>();
    this.level = 0;
  }

  public boolean isPresent(final String id) {
    return this.table.containsKey(id);
  }

  public void save(final String id, final Declaration decl) {
    if (!(this.table.containsKey(this.level))) {
      this.table.put(this.level, new HashMap<>());
    }
    this.table.get(this.level).put(id, decl);
  }

  public Declaration retrieve(final String id) {
    return this.table.get(this.level).get(id);
  }

  public void openScope() {
    this.level++;
  }

  public void closeScope() {
    this.table.remove(this.level);
    this.level--;
  }

  public void display() {
    for (Map.Entry<Integer, Map<String, Declaration>> entry : this.table.entrySet()) {
      System.out.println("Entries at level " + entry.getKey());
      for (Map.Entry<String, Declaration> e : entry.getValue().entrySet()) {
        System.out.printf("%s => %s\n", e.getKey(), e.getValue());
      }
    }
  }
}
