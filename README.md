# address-formatter
This is simple address formater in the following formats :
- “Winterallee 3” -&gt; {“ Winterallee”, “3”}
- “Musterstrasse 45” -&gt; { “Musterstrasse”, “45”}
- “Blaufeldweg 123B” -&gt; {“Blaufeldweg”, “123B”}
- “Am Bächle 23” -&gt; {“Am Bächle”, “23”}
- “Auf der Vogelwiese 23 b” -&gt; {“Auf der Vogelwiese”, ”23 b”}
- “4, rue de la revolution” -&gt; {&quot;rue de la revolution&quot;, &quot;4&quot;}
- “200 Broadway Av” -&gt; {&quot;Broadway Av&quot;, &quot;200&quot;}
- “Calle Aduana, 29” -&gt; {“Calle Aduana”, “29”}
- “Calle 39 No 1540” -&gt; {“Calle 39”, “No 1540”}

# Tech stack

- Spring boot

For build

- Gradle

# To build

```
./gradlew clean build
```


# To run

```
./gradlew bootRun
```
