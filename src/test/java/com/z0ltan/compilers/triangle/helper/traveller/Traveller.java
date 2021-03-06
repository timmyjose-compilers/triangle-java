package com.z0ltan.compilers.triangle.helper.traveller;

import java.lang.reflect.Field;

public class Traveller {
  public static Object travel(final Object object, final String path) {
    try {
      final String[] fields = path.split("\\.");
      if (fields.length == 0) {
        return null;
      }
      return travel(object, fields, 0);
    } catch (Exception ex) {
      return null;
    }
  }

  private static Object travel(final Object object, final String[] fields, int index) throws Exception {
    Field field = object.getClass().getDeclaredField(fields[index]);
    if (index == fields.length - 1) {
      return field.get(object);
    }

    return travel(field.get(object), fields, index + 1);
  }
}
