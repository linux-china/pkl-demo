Pkl demo
================

Pkl(pickle): a configuration as code language with rich validation and tooling.

# Features

* Generate any static configuration format
* Integrated application configuration
* Incredible IDE Integration
* Catch errors before deployment

# Constraints

For more, please refer: https://pkl-lang.org/package-docs/pkl/0.25.2/base/index.html

```
name: String(length.isOdd, chars.first == chars.last)
zipCode: String(matches(Regex("\\d{5}")))
serverPort: UInt16(isBetween(0, 1023))
port: Int(this > 1000) = 80
```

# Java code generate

Execute `mvn exec:java` or `mvn compile` to generate the Java code from pkl.

If you use [JBang](https://www.jbang.dev/) you can run the following command to generate the Java code from pkl.

```shell
jbang run --main org.pkl.codegen.java.Main "org.pkl-lang:pkl-tools:0.25.2" --help
```

or execute `jbang pkl-cli` or `jbang pkl-codegen` with `jbang-catalog.json`.

# References

* Pkl Home: https://pkl-lang.org/
* Pkl IntelliJ Plugin: https://github.com/apple/pkl-intellij/
* Pkl with Spring Boot: https://pkl-lang.org/spring/current/usage.html
* pkl-pantry： https://github.com/apple/pkl-pantry
* pkl-k8s: https://github.com/apple/pkl-k8s
* CUE: Validate, define, and use dynamic and text-based data https://cuelang.org/
