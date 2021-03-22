<img src="https://www.yegor256.com/images/books/elegant-objects/cactus.svg" height="100px" />

# EO to Java Transpiling Model

## Mapping EO Entities to Java Code Structures

| EO | Java |
|:---|:-----|
| Abstraction (introduction of a conceptually new object) | A plain old Java class extending [EOObject](https://github.com/HSE-Eolang/eo-to-java-model/blob/main/src/main/java/eo/org/eolang/core/EOObject.java) |
| Application (object copying) | Creating a new class instance |
| A free attribute | A class field that should be sat via the constructor |
| A bound attribute | A separate class with the following naming pattern `EObase$EOattr` |
| Duck typing | Everything is `EOObject`. Class fields are accessed via Java Reflection |
| Object dataization | Calling _getData() |

## Performance Metrics Comparison
The solution being proposed is ~17x faster and more efficient.
### The Current (CQFN) Implementation
30th Fibonacci number is 832040
real    1m8,963s<br>
user    1m9,882s<br>
sys     0m1,391s<br>

### The Approach being Proposed
50th Fibonacci number is 12586269025
real    0m0,430s
user    0m0,652s
sys     0m0,065s



