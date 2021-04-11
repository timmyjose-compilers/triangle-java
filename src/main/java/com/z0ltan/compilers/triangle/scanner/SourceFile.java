package com.z0ltan.compilers.triangle.scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.z0ltan.compilers.triangle.error.SyntaxError;

public class SourceFile implements Iterable<Char> {
  private List<Char> chars;
  public static final char EOL = '\n';
  public static final char EOT = '\u0000';

  public SourceFile(String filename) {
    this.chars = new ArrayList<>();
    this.loadFromFile(filename);
  }

  private void loadFromFile(String filename) {
    int line = 1, column = 1;
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String input = null;

      while ((input = reader.readLine()) != null) {
        for (char c : input.toCharArray()) {
          this.chars.add(new Char(c, line, column));
          column++;
        }
        this.chars.add(new Char(SourceFile.EOL, line, column));
        column = 1;
        line++;
      }
      this.chars.add(new Char(SourceFile.EOT, -1, -1));
    } catch (Exception ex) {
      throw new SyntaxError("error while reading source file " + filename + ": " + ex.getLocalizedMessage());
    }
  }

  public Iterator<Char> iterator() {
    return this.chars.iterator();
  }
}

