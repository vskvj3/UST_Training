## Styles
- Styles refers to different ways of writing your scala program.
- It is based on various programming paradigms supported by language.

1. Functional Style
2. Object Oriented style
3. Pattern Matching style
4. For-Comprehension:
    ```Scala
    val num = List(1, 2, 3, 4, 5)
    val result = for {
    n <- num
    squared = n * n
    } yield squared
    
    println(result)
    ```
5. If-Comprehension
6. Type class
7. Traits and Mixins
