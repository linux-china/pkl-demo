package org.mvnsearch.config;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.Objects;
import org.pkl.config.java.mapper.Named;
import org.pkl.config.java.mapper.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public final class AppConfig {
  private final @NonNull Object pigeon;

  public AppConfig(@Named("pigeon") @NonNull Object pigeon) {
    this.pigeon = pigeon;
  }

  public @NonNull Object getPigeon() {
    return pigeon;
  }

  public AppConfig withPigeon(@NonNull Object pigeon) {
    return new AppConfig(pigeon);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    AppConfig other = (AppConfig) obj;
    if (!Objects.equals(this.pigeon, other.pigeon)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + Objects.hashCode(this.pigeon);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(100);
    builder.append(AppConfig.class.getSimpleName()).append(" {");
    appendProperty(builder, "pigeon", this.pigeon);
    builder.append("\n}");
    return builder.toString();
  }

  private static void appendProperty(StringBuilder builder, String name, Object value) {
    builder.append("\n  ").append(name).append(" = ");
    String[] lines = Objects.toString(value).split("\n");
    builder.append(lines[0]);
    for (int i = 1; i < lines.length; i++) {
      builder.append("\n  ").append(lines[i]);
    }
  }

  @ConfigurationProperties
  public static final class Pigeon {
    private final @NonNull String name;

    private final int age;

    public Pigeon(@Named("name") @NonNull String name, @Named("age") int age) {
      this.name = name;
      this.age = age;
    }

    public @NonNull String getName() {
      return name;
    }

    public Pigeon withName(@NonNull String name) {
      return new Pigeon(name, age);
    }

    public int getAge() {
      return age;
    }

    public Pigeon withAge(int age) {
      return new Pigeon(name, age);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (this.getClass() != obj.getClass()) return false;
      Pigeon other = (Pigeon) obj;
      if (!Objects.equals(this.name, other.name)) return false;
      if (!Objects.equals(this.age, other.age)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + Objects.hashCode(this.name);
      result = 31 * result + Objects.hashCode(this.age);
      return result;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder(150);
      builder.append(Pigeon.class.getSimpleName()).append(" {");
      appendProperty(builder, "name", this.name);
      appendProperty(builder, "age", this.age);
      builder.append("\n}");
      return builder.toString();
    }
  }
}
