Pkl demo
================

Pkl(pickle): a configuration as code language with rich validation and tooling.

# Features

* Generate any static configuration format
* Integrated application configuration
* Incredible IDE Integration
* Catch errors before deployment

# How to write configuration item?

```
name: Type(constraint1, constraint2) = value | expression
```

* name：也就是配置项的名称，你使用标准的变量名即可，注意：Pkl不支持`server.port`这种方式，而是要调整为`server{port=8080}`这样
* Type: Pkl是强类型语言，所以你需要指定类型，如UInt16，Int16，UInt32，Int32, String等。如果你不指定类型，Pkl会自动推断类型，如String等。开始你可能不习惯一个Number怎么这么多类型，搞得和Rust一样，这个主要是起到类型约束作用，保证你不会写错配置项的值。
* constraint：配置项值的约束条件，即便有了类型，但是还是更进一步的值约束，如数值区间，字符串格式等，如`UInt16(isBetween(0, 1023))`，这个表示值必须在0-1023之间。 constraint主要为boolean类型函数和表达式，如`isBetween(0, 1023)`，`this > 1024`等，多个约束条件用逗号分隔。
* value: 配置项的值，可以是固定字面量，如`8080`,`"support@example.com"`，也可以是表达式，如缓存时间如果是秒的话，那么`24*60*60`可能要比`86400`更直观。

Example:

```
name: String(length.isOdd, chars.first == chars.last)
zipCode: String(matches(Regex("\\d{5}")))
serverPort: UInt16(isBetween(0, 1023))
port: Int(this > 1000) = 80
```

更多的constraint函数，请访问: https://pkl-lang.org/package-docs/pkl/0.25.2/base/index.html


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
