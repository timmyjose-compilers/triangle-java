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
    return this.table.get(this.level).containsKey(id);
  }

  public void save(final String id, final Declaration decl) {
    this.table.get(this.level).put(id, decl);
  }

  public Declaration retrieve(final String id) {
    Declaration binding = null;
    int currLevel = this.level;

    while (currLevel >= 0) {
      binding = this.table.get(currLevel).get(id);
      if (this.table.get(currLevel).containsKey(id)) {
        binding = this.table.get(currLevel).get(id);
        break;
      }
      currLevel--;
    }

    return binding;
  }

  public void openScope() {
    this.level++;
  }

  public void closeScope() {
    this.table.remove(this.level);
    this.level--;
  }
}
